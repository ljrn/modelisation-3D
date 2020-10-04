package main.java.projet.view;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.projet.reader.CreateEnvironment;
import main.java.projet.reader.Face;
import main.java.projet.reader.Faces;
import main.java.projet.reader.Points;


public class FormDisplay extends Application{
	ListView<File> listFiles;
	public void start(Stage primaryStage) {
		BorderPane root=new BorderPane();
		Canvas c=new Canvas(300,300);
		GraphicsContext gc= c.getGraphicsContext2D();
		HBox hb=listFiles(c,gc);
		Events events = new Events();
	    events.zoom(c);
	    events.rotation(c);
	    events.move(c);
		root.getChildren().add(c);
		root.setRight(hb);
		Scene scene=new Scene(root, 1000, 1500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		Application.launch();
	}
	
	public HBox listFiles(Canvas c, GraphicsContext gc) {
	    File path = new File("./ressources");
	    listFiles=new ListView<>();
	    listFiles.getItems().addAll(path.listFiles());
	    listFiles.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener(c,gc));
	    HBox root = new HBox();
	    root.getChildren().addAll(listFiles);
	    return root;
	  }
	class MonListChangeListener implements ListChangeListener<File> {
		Canvas c;
		GraphicsContext gc;
		
		public MonListChangeListener(Canvas c, GraphicsContext gc) {
			this.c=c;
			this.gc=gc;
		}
	    public void onChanged(javafx.collections.ListChangeListener.Change<? extends File> ch){
	      CreateEnvironment ce=new CreateEnvironment();
	      try{
				ce.createFaces("./ressources/"+ch.getList().toString().substring(14, ch.getList().toString().length()-1),c.getWidth()/2,c.getHeight()/2);
			}catch(IOException e ) {
				System.out.println(ch.getList().toString().substring(13));
			}
	      Points ps=ce.ps;
	      Faces f=ce.fa;
	      gc.clearRect(0, 0, c.getWidth(), c.getHeight());
	      c.setWidth(ps.maxX());
	      c.setHeight(ps.maxY());
	      gc= c.getGraphicsContext2D();
	      gc.setFill(Color.DARKGREY);
	      gc.setStroke(Color.GREY);
	      for(Face fa:f.getFaces()) {
	    	  gc.fillPolygon(fa.getPointsX(), fa.getPointsY(),fa.getNbPoint());
	    	  gc.strokePolygon(fa.getPointsX(), fa.getPointsY(), fa.getNbPoint());
	      }
	      Events events = new Events();
		  events.zoom(c);
		  events.rotation(c);
		  events.move(c);
	    }
	  }
}
