package projet.view;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	Canvas canvas = new Canvas(300, 300);
	GraphicsContext context = canvas.getGraphicsContext2D();
	ListView<File> listFiles;
	Faces faces;
	Points points;
	VBox vbox = new VBox();
	DirectoryChooser directoryChooser;
	File path;
	Stage stage;
	Button rechercher = new Button("Rechercher");
	TextField textField = new TextField("");
	
	public void start(Stage primaryStage) {
		directoryChooser=new DirectoryChooser();
		path = directoryChooser.showDialog(null);
		BorderPane root = new BorderPane();
		width = canvas.getWidth();
		height = canvas.getHeight();
		HBox hb = listFiles(canvas, context);
		HBox hb2 = new HBox(textField,rechercher);
		VBox nbFaces = new VBox(hb2,hb, vbox);
		root.getChildren().add(canvas);
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
	 * @param canvas correspond au Canvas dans lequel on va voir la figure
	 * @param context correspond au GraphicsContext lie au Canvas
	 * @return Une HBox contenant la liste des fichiers .ply dans le repertoire ouvert
	 */
	public HBox listFiles(Canvas canvas, GraphicsContext context) {
		listFiles = new ListView<>();
		for (File file : path.listFiles()) {
			if(file.toString().contains(".ply")) {
				listFiles.getItems().add(file);

			}
		}
		listFiles.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener(canvas, context, this));
		HBox root = new HBox();
		root.getChildren().addAll(listFiles);
		return root;
	}
	/**
	 * 
	 * @param faces correspond aux faces de la figure a dessiner
	 * @param fill boolean permettant de signifier si les faces doivent etre pleines
	 * @param stroke boolean permettant de signifier si les arretes doivent etre dessinnees
	 */
	public void dessinModele(Faces faces, boolean fill, boolean stroke) {
		context.clearRect(0, 0, 10000, 10000);
		faces.colorDiffuseFace();
		for (Face face : faces.getFaces()) {
			if(fill) {
				context.setFill(Color.rgb(face.getRed(), face.getGreen(), face.getBlue()));
				context.fillPolygon(face.getPointsX(), face.getPointsY(), face.getNbPoint());
			}
			if(stroke) {
				context.strokePolygon(face.getPointsX(), face.getPointsY(), face.getNbPoint());
			}
		}
		if(faces.isTimerActive())faces.timerRotation();
	}
	@Override
	public void update(Subject subj) {
	}
	@Override
	public void update(Subject subj, Object data) {
		faces = (Faces) data;
		faces.cancelTimer();
		canvas.setWidth(faces.maxX());
		canvas.setHeight(faces.maxY());
		this.dessinModele(faces, fill, stroke);
	}
}
