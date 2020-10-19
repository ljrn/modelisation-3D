package projet.view;

import java.io.File;
import java.io.IOException;

import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import projet.reader.CreateEnvironment;
import projet.reader.Faces;
import projet.reader.Points;
import projet.reader.ReadFile;

class MonListChangeListener implements ListChangeListener<File> {
	Canvas c;
	GraphicsContext gc;
	FormDisplay fd;
	Label tf = new Label();
	Label tf2 = new Label();
	
	public MonListChangeListener(Canvas c, GraphicsContext gc, FormDisplay fd) {
		this.c=c;
		this.gc=gc;
		this.fd=fd;
	}  
	 
	//METHODE DE BUILD DU MODELE A CHAQUE CHANGEMENT DE FICHIER
    public void onChanged(javafx.collections.ListChangeListener.Change<? extends File> ch){
      CreateEnvironment ce=new CreateEnvironment();
      try{
    	  ce.createFaces("./ressources/"+ch.getList().toString().substring(14, ch.getList().toString().length()-1),fd.width,fd.height);
      }catch(IOException e ) {
    	  System.out.println(ch.getList().toString().substring(13));
      }
		
		
      Points ps=ce.ps;
      Faces f=ce.fa;
      
      tf.setText("Nombre de points : "+ps.getPoints().size());
      tf2.setText("Nombre de faces : "+f.getFaces().size());
      
      fd.vb.getChildren().remove(tf);
      fd.vb.getChildren().remove(tf2);
      fd.vb.getChildren().add(tf);
      fd.vb.getChildren().add(tf2);	
      
      f.attach(fd);
      gc.clearRect(0, 0, c.getWidth(), c.getHeight());
      c.setWidth(ps.maxX());
      c.setHeight(ps.maxY());
      fd.dessinModele(f);
      Translate t=new Translate();
      t.translate(f, c);
    }
  }
