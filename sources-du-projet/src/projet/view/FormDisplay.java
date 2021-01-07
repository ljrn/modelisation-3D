package projet.view;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import projet.modele.Face;
import projet.modele.Faces;
import projet.modele.Points;
import projet.utils.Observer;
import projet.utils.Subject;

public class FormDisplay extends Application implements Observer {
	double width;
	double height;
	boolean stroke;
	boolean fill;
	Canvas c = new Canvas(300, 300);
	GraphicsContext gc = c.getGraphicsContext2D();
	ListView<File> listFiles;
	Faces f;
	Points ps;
	VBox vb = new VBox();
	DirectoryChooser directoryChooser;
	File path;
	Stage stage;
	
	public void start(Stage primaryStage) {
		directoryChooser=new DirectoryChooser();
		path = directoryChooser.showDialog(null);
		BorderPane root = new BorderPane();
		width = c.getWidth();
		height = c.getHeight();
		HBox hb = listFiles(c, gc);
		VBox nbFaces = new VBox(hb, vb);
		root.getChildren().add(c);
		root.setRight(nbFaces);
		Scene scene = new Scene(root, 1500, 1000);
		primaryStage.setScene(scene);
		stage=primaryStage;
		primaryStage.show();	
		/*primaryStage.setOnCloseRequest(e -> {
	        Platform.exit();
	        System.exit(0);
	    });*/

	}
	public static void main(String[] args) {
		Application.launch();
	}
	/**
	 * 
	 * @param c correspond au Canvas dans lequel on va voir la figure
	 * @param gc correspond au GraphicsContext lié au Canvas
	 * @return Une HBox contenant la liste des fichiers .ply dans le répertoire ouvert
	 */
	public HBox listFiles(Canvas c, GraphicsContext gc) {
		listFiles = new ListView<>();
		for (File file : path.listFiles()) {
			if(file.toString().contains(".ply")) {
				listFiles.getItems().add(file);

			}
		}
		listFiles.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener(c, gc, this));
		HBox root = new HBox();
		
		root.getChildren().addAll(listFiles);
		return root;
	}
	/**
	 * 
	 * @param f correspond aux faces de la figure à dessiner
	 * @param fill boolean permettant de signifier si les faces doivent être pleines
	 * @param stroke boolean permettant de signifier si les arrêtes doivent être dessinnées
	 */
	public void dessinModele(Faces f, boolean fill, boolean stroke) {
		gc.clearRect(0, 0, 10000, 10000);
		f.colorDiffuseFace();
		for (Face fa : f.getFaces()) {
			if(fill) {
				gc.setFill(Color.rgb(fa.getRed(), fa.getGreen(), fa.getBlue()));
				gc.fillPolygon(fa.getPointsX(), fa.getPointsY(), fa.getNbPoint());
			}
			if(stroke) {
				gc.strokePolygon(fa.getPointsX(), fa.getPointsY(), fa.getNbPoint());
			}
		}
		if(f.isTimerActive())f.timerRotation();
	}
	@Override
	public void update(Subject subj) {
	}
	@Override
	public void update(Subject subj, Object data) {
		f = (Faces) data;
		f.cancelTimer();
		c.setWidth(f.maxX());
		c.setHeight(f.maxY());
		this.dessinModele(f, fill, stroke);
	}
}
