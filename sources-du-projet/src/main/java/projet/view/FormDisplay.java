package projet.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
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
			Canvas c=new Canvas(ps.maxX(),ps.maxY());

//			c.setTranslateX(ps.maxX());
//			c.setTranslateY(ps.maxY());
//			c.setScaleX(0.5);
//			c.setScaleY(0.5);

			GraphicsContext gc= c.getGraphicsContext2D();
			gc.setFill(Color.DARKGREY);
			gc.setStroke(Color.GREY);
			//	gc.fillRect(c.getWidth()/4,c.getHeight()/4, c.getWidth()-(c.getWidth()/4), c.getHeight()-(c.getHeight()/4));
			gc.moveTo(ps.midX(), ps.midY());
			for(Face fa:f.getFaces()) {
				gc.fillPolygon(fa.getPointsX(), fa.getPointsY(),fa.getNbPoint());
				gc.strokePolygon(fa.getPointsX(), fa.getPointsY(), fa.getNbPoint());
			}
			
			//FAIRE EN SORTE QU'ON PUISSE ZOOM
			Events events = new Events();
			events.zoom(c);
//			c.setOnScroll(e->{
//				if(e.) {
//					c.setScaleX(c.getScaleX()/2);
//					c.setScaleY(c.getScaleY()/2);
//					gc.moveTo(ps.midX(), ps.midY());
//
//					c.setTranslateX(0);
//					c.setTranslateY(0);
//				}else if(e.getCode().equals(KeyCode.Z)){
//					c.setScaleX(c.getScaleX()*2);
//					c.setScaleY(c.getScaleY()*2);
//					gc.moveTo(ps.midX(), ps.midY());
//
//				}
//			});
//			
			//FIN ZOOM
			ListViewer lv=new ListViewer();
			HBox hb=lv.ListFiles();
			root.getChildren().addAll(c, hb);
			Scene scene=new Scene(root, 1000, 1500);
			events.rotation(root, scene);
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
