package projet.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Points;
import projet.reader.ReadFile;


public class FormDisplay extends Application{
	public void start(Stage primaryStage) {
		ReadFile rf=new ReadFile("./ressources/airplane.ply");
		try {
			List<String> ls=rf.readHeader();
			for(String s:ls) {
				System.out.println(s);
			}
			Points ps=new Points();
			ps.decompStringPoints(rf.getPoints());
			Faces f=new Faces();
			f.decompStringFaces(rf.getFaces(), ps);
			Group root=new Group();
			Canvas c=new Canvas(3000,3000);
			GraphicsContext gc= c.getGraphicsContext2D();
			gc.setFill(Color.DARKGREY);
			gc.setStroke(Color.GREY);
			for(Face fa:f.getFaces()) {
				gc.fillPolygon(fa.getPointsX(), fa.getPointsY(),fa.getNbPoint());
				gc.strokePolygon(fa.getPointsX(), fa.getPointsY(), fa.getNbPoint());
			}
			//FAIRE EN SORTE QU'ON PUISSE ZOOM
			c.setOnMousePressed(e->{
				if(e.getButton().equals(MouseButton.SECONDARY)) {
					c.setScaleX(c.getScaleX()/2);
					c.setScaleY(c.getScaleY()/2);
					c.setTranslateX(0);
					c.setTranslateY(0);
				}else {
					c.setScaleX(c.getScaleX()*2);
					c.setScaleY(c.getScaleY()*2);
					c.setTranslateX(0);
					c.setTranslateY(0);
				}
			});
			
			//FIN ZOOM
			root.getChildren().add(c);
			Scene scene=new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Application.launch();
	}
}