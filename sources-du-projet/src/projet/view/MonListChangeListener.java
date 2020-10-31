package projet.view;

import java.io.File;
import java.io.IOException;

import org.junit.platform.engine.support.descriptor.FileSystemSource;

import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import projet.reader.CreateEnvironment;
import projet.reader.Faces;
import projet.reader.Points;

class MonListChangeListener implements ListChangeListener<File> {
	Canvas c;
	GraphicsContext gc;
	FormDisplay fd;
	Label nombreDePoints = new Label();
	Label nombreDeFaces = new Label();
	Button plusX=new Button("->");
	Button moinsX=new Button("<-");
	Button plusY=new Button("v");
	Button moinsY=new Button("^");
	Button rotateXplus=new Button("+");
	Button rotateXmoins=new Button("-");
	Button rotateYplus=new Button("+");
	Button rotateYmoins=new Button("-");
	Button rotateZplus=new Button("+");
	Button rotateZmoins=new Button("-");
	double previousX=0.0;
	double previousY=0.0;


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
		plusX.setOnAction(e->{
			t.translateX(fd,f,3.0);
		});
		moinsX.setOnAction(e->{
			t.translateX(fd,f,-3.0);
		});
		plusY.setOnAction(e->{
			t.translateY(fd,f,3.0);
		});
		moinsY.setOnAction(e->{
			t.translateY(fd,f,-3.0);
		});
		
		Zoom z = new Zoom();
		c.setOnScroll(e->{
			if(e.getDeltaY()>0)z.Zoom(f,1.5);
			else z.Zoom(f,0.5);
		});
		
		Rotate r = new Rotate();
		rotateXplus.setOnAction(e->{
			r.rotateX(fd, f,0.05);
		});
		rotateXmoins.setOnAction(e->{
			r.rotateX(fd, f,-0.05);
		});
		rotateZplus.setOnAction(e->{
			r.rotateZ(fd, f,0.05);
		});
		rotateZmoins.setOnAction(e->{
			r.rotateZ(fd, f,-0.05);
		});
		
		rotateYplus.setOnMousePressed(e->{
				r.rotateY(fd, f,0.05);
			
		});
		rotateYmoins.setOnMousePressed(e->{
			r.rotateY(fd, f,-0.05);
		});
		
		
	
		c.setOnMousePressed(e->{
			previousX=e.getSceneX();
			previousY=e.getSceneY();
		});
		
		c.setOnMouseDragged(e->{
			double x = e.getScreenX();
			double y = e.getScreenY();
			if(e.isPrimaryButtonDown()) {
				if(x!=previousX) {
					t.translateX(fd,f,x-previousX);
					previousX=x;
				}
				if(y!=previousY) {
					t.translateY(fd,f,y-previousY);
					previousY=y;
				}
				
			}
			if(e.isSecondaryButtonDown()) {
				if(x!=previousX) {
					r.rotateY(fd,f,(x-previousX)/1000);
					previousX=x;
				}
				if(y!=previousY) {
					r.rotateX(fd,f,(y-previousY)/1000);
					previousY=y;
				}
			}
		});
		fd.vb.getChildren().clear();

		nombreDePoints.setText("     Nombre de points : "+ps.getPoints().size());
		nombreDeFaces.setText("     Nombre de faces : "+f.getFaces().size());
		Label info = new Label("Informations : ");
		fd.vb.getChildren().add(info);
		fd.vb.getChildren().add(nombreDePoints);
		fd.vb.getChildren().add(nombreDeFaces);	
		fd.vb.getChildren().add(new Separator());
		fd.vb.getChildren().add(new Label("Translation :"));    
		fd.vb.getChildren().add(new HBox(new Label("     Incrémenter le X : "),plusX));
		fd.vb.getChildren().add(new HBox(new Label("     Décrémenter le X : "),moinsX)); 
		fd.vb.getChildren().add(new HBox(new Label("     Incrémenter le Y : "),moinsY));
		fd.vb.getChildren().add(new HBox(new Label("     Décrémenter le Y : "),plusY));
		fd.vb.getChildren().add(new Separator());
		fd.vb.getChildren().add(new Label("Rotation"));
		fd.vb.getChildren().add(new HBox(new Label("     Incrémenter en X : "),rotateXplus));
		fd.vb.getChildren().add(new HBox(new Label("     Décrémenter en X : "),rotateXmoins));
		fd.vb.getChildren().add(new HBox(new Label("     Incrémenter en Y : "),rotateYplus));
		fd.vb.getChildren().add(new HBox(new Label("     Décrémenter en Y : "),rotateYmoins));
		fd.vb.getChildren().add(new HBox(new Label("     Incrémenter en Z : "),rotateZplus));
		fd.vb.getChildren().add(new HBox(new Label("     Décrémenter en Z : "),rotateZmoins));


	}
}
