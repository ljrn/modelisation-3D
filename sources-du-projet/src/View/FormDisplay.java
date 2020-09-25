package View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import reader.Face;
import reader.Faces;
import reader.Point;
import reader.Points;
import reader.ReadFile;

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
			Canvas c=new Canvas(250,250);
			GraphicsContext gc= c.getGraphicsContext2D();
			gc.setFill(Color.BLUE);
			for(Face fa:f.getFaces()) {
				
				System.out.println(fa.getPointsX()[1]);
				gc.fillPolygon(fa.getPointsX(), fa.getPointsY(),fa.getNbPoint());
			}
			root.getChildren().add(c);
			root.getChildren().add(new TextField());
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
