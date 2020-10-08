package projet.view;
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
import projet.reader.CreateEnvironment;
import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Points;


public class FormDisplay extends Application{
	double width;
	double height;
	ListView<File> listFiles;
	public void start(Stage primaryStage) {
		BorderPane root=new BorderPane();
		Canvas c=new Canvas(300,300);
		GraphicsContext gc= c.getGraphicsContext2D();
		gc.setFill(Color.DARKGREY);
	    gc.setStroke(Color.GREY);
		
		//INITIALISATION DE L'ECHELLE ET DES MESURES DU CANVAS
		width=c.getWidth();
		height=c.getHeight();
		
		//DEFINITION DE LA SCENE
		HBox hb=listFiles(c,gc);
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
	    //CREATION DE LA LISTVIEW
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
		
		//METHODE DE BUILD DU MODELE A CHAQUE CHANGEMENT DE FICHIER
	    public void onChanged(javafx.collections.ListChangeListener.Change<? extends File> ch){
	      CreateEnvironment ce=new CreateEnvironment();
	      try{
	    	  ce.createFaces("./ressources/"+ch.getList().toString().substring(14, ch.getList().toString().length()-1),width,height);
	      }catch(IOException e ) {
	    	  System.out.println(ch.getList().toString().substring(13));
	      }
	      Points ps=ce.ps;
	      Faces f=ce.fa;
	      gc.clearRect(0, 0, c.getWidth(), c.getHeight());
	      c.setScaleX(1);
	      c.setScaleY(1);
	      c.setWidth(ps.maxX());
	      c.setHeight(ps.maxY());
	      for(Face fa:f.getFaces()) {
	    	  gc.fillPolygon(fa.getPointsX(), fa.getPointsY(),fa.getNbPoint());
	    	  gc.strokePolygon(fa.getPointsX(), fa.getPointsY(), fa.getNbPoint());
	      }
	    }
	  }
}
