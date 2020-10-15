package projet.view;

import java.io.File;
import java.io.IOException;

import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import projet.reader.CreateEnvironment;
import projet.reader.Faces;
import projet.reader.Points;

class MonListChangeListener implements ListChangeListener<File> {
	Canvas c;
	GraphicsContext gc;
	FormDisplay fd;
	
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
      f.attach(fd);
      gc.clearRect(0, 0, c.getWidth(), c.getHeight());
      c.setWidth(ps.maxX());
      c.setHeight(ps.maxY());
      fd.dessinModele(f);
      Translate t=new Translate();
      t.translate(f, c);
    }
  }
