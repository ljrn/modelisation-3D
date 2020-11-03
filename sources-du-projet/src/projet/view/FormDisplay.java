package projet.view;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Points;
import projet.utils.Observer;
import projet.utils.Subject;

public class FormDisplay extends Application implements Observer {
	double width;
	double height;
	Canvas c = new Canvas(300, 300);
	GraphicsContext gc = c.getGraphicsContext2D();
	ListView<File> listFiles;
	Faces f;
	Points ps;
	VBox vb = new VBox();
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		gc.setFill(Color.DARKGREY);
		gc.setStroke(Color.GREY);
		width = c.getWidth();
		height = c.getHeight();
		HBox hb = listFiles(c, gc, this);
		VBox nbFaces = new VBox(hb, vb);
		root.getChildren().add(c);
		root.setRight(nbFaces);
		Scene scene = new Scene(root, 1500, 1000);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch();
	}
	public HBox listFiles(Canvas c, GraphicsContext gc, FormDisplay fd) {
		File path = new File("./ressources");
		listFiles = new ListView<>();
		listFiles.getItems().addAll(path.listFiles());
		listFiles.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener(c, gc, fd));
		HBox root = new HBox();
		root.getChildren().addAll(listFiles);
		return root;
	}
	public void dessinModele(Faces f) {
		gc.clearRect(0, 0, 10000, 10000);
		for (Face fa : f.getFaces()) {
			gc.fillPolygon(fa.getPointsX(), fa.getPointsY(), fa.getNbPoint());
			gc.strokePolygon(fa.getPointsX(), fa.getPointsY(), fa.getNbPoint());
		}
	}
	@Override
	public void update(Subject subj) {
	}
	@Override
	public void update(Subject subj, Object data) {
		f = (Faces) data;
		c.setWidth(f.maxX());
		c.setHeight(f.maxY());
		this.dessinModele(f);
	}
}
