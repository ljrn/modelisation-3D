package projet.view;

import java.io.File;
import java.io.IOException;

import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import projet.reader.CreateEnvironment;
import projet.reader.Faces;
import projet.reader.Points;

class MonListChangeListener implements ListChangeListener<File> {
	Canvas c;
	GraphicsContext gc;
	FormDisplay fd;
	Label nombreDePoints = new Label();
	Label nombreDeFaces = new Label();
	Button plusX=new Button("+");
	Button moinsX=new Button("-");
	Button plusY=new Button("+");
	Button moinsY=new Button("-");
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

		fd.vb.getChildren().clear();

		nombreDePoints.setText("Nombre de points : "+ps.getPoints().size());
		nombreDeFaces.setText("Nombre de faces : "+f.getFaces().size());
		//fd.vb.getChildren().remove(nombreDePoints);
		//fd.vb.getChildren().remove(nombreDeFaces);
		fd.vb.getChildren().add(nombreDePoints);
		fd.vb.getChildren().add(nombreDeFaces);	

		f.attach(fd);
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
		c.setWidth(ps.maxX());
		c.setHeight(ps.maxY());
		fd.dessinModele(f);
		Translate t=new Translate();
		plusX.setOnAction(e->{
			t.plusX(fd,f);
		});
		moinsX.setOnAction(e->{
			t.moinsX(fd, f);
		});
		plusY.setOnAction(e->{
			t.plusY(fd,f);
		});
		moinsY.setOnAction(e->{
			t.moinsY(fd, f);
		});


		fd.vb.getChildren().add(plusX);
		fd.vb.getChildren().add(moinsX);
		fd.vb.getChildren().add(plusY);
		fd.vb.getChildren().add(moinsY);

	}
}
