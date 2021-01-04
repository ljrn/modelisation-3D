package projet.view;

import java.io.File;


import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import projet.modele.CreateEnvironment;
import projet.modele.FileCreator;
import projet.modele.Lumiere;
import projet.modele.Points;
import projet.modele.Rotate;
import projet.modele.Translate;
import projet.modele.Zoom;


class MonListChangeListener implements ListChangeListener<File> {
	Canvas c;
	GraphicsContext gc;
	FormDisplay fd;
	Label nombreDePoints = new Label();
	Label nombreDeFaces = new Label();
	Label date = new Label("     Date de cr�ation : ");
	Label auteur = new Label("     Nom de l'auteur : ");
	Button plusX = new Button("->");
	Button moinsX = new Button("<-");
	Button plusY = new Button("v");
	Button moinsY = new Button("^");
	Button rotateXplus = new Button("+");
	Button rotateXmoins = new Button("-");
	Button rotateYplus = new Button("+");
	Button rotateYmoins = new Button("-");
	Button rotateZplus = new Button("+");
	Button rotateZmoins = new Button("-");
	Button rotateLumiereDroite = new Button("->");
	Button rotateLumiereGauche = new Button("<-");
	Button rotateLumiereBas = new Button("v");
	Button rotateLumiereHaut = new Button("^");
	Button creerVue = new Button("Nouvelle vue");
	Button changeRep = new Button("Change Folder");
	Button startTimer = new Button("Start Timer");
	Button stopTimer = new Button("Stop Timer");
	Button onlyFace=new Button("Only Faces");
	Button onlyStroke= new Button("Only Strokes");
	
	public MonListChangeListener(Canvas c, GraphicsContext gc, FormDisplay fd) {
		this.c = c;
		this.gc = gc;
		this.fd = fd;
	}

	public void onChanged(javafx.collections.ListChangeListener.Change<? extends File> ch) {
		CreateEnvironment ce = new CreateEnvironment();
		auteur.setText("     Nom de l'auteur : ");
		date.setText("     Date de création : ");
		FileCreator creator = new FileCreator();
		File theFile = creator.getFile(fd.path, ch.getList().toString());
		ce.createFaces(theFile, fd.width, fd.height);
		auteur.setText(auteur.getText() + creator.getAuthor(theFile));
		date.setText(date.getText() + creator.getDate(theFile));
		Points ps = ce.pts;
		ce.fa.attach(fd);
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
		c.setWidth(ps.maxX());
		c.setHeight(ps.maxY());
		fd.dessinModele(ce.fa, true, true);
		Translate t = new Translate();
		plusX.setOnAction(e -> {
			t.translate(ce.fa, 3.0, 0, 0);
		});
		moinsX.setOnAction(e -> {
			t.translate(ce.fa, -3.0, 0, 0);
		});
		plusY.setOnAction(e -> {
			t.translate(ce.fa, 0, 3.0, 0);
		});
		moinsY.setOnAction(e -> {
			t.translate(ce.fa, 0, -3.0, 0);
		});
		Zoom z = new Zoom();
		c.setOnScroll(e -> {
			if (e.getDeltaY() > 0)
				z.zoom(ce.fa, 1.05);
			else
				z.zoom(ce.fa, 0.95);
		});
		Rotate r = new Rotate();
		rotateXplus.setOnAction(e -> {
			r.rotateX(ce.fa, 0.05);
		});
		rotateXmoins.setOnAction(e -> {
			r.rotateX(ce.fa, -0.05);
		});
		rotateZplus.setOnAction(e -> {
			r.rotateZ(ce.fa, 0.05);
		});
		rotateZmoins.setOnAction(e -> {
			r.rotateZ(ce.fa, -0.05);
		});
		rotateYplus.setOnMousePressed(e -> {
			r.rotateY(ce.fa, 0.05);
		});
		rotateYmoins.setOnMousePressed(e -> {
			r.rotateY(ce.fa, -0.05);
		});
		Lumiere lum = new Lumiere();
		rotateLumiereDroite.setOnMousePressed(e -> {
			lum.rotateHorizontal(ce.fa, 0.1);
		});
		rotateLumiereGauche.setOnMousePressed(e -> {
			lum.rotateHorizontal(ce.fa, -0.1);
		});
		rotateLumiereHaut.setOnMousePressed(e -> {
			lum.rotateVertical(ce.fa, 0.1);
		});
		rotateLumiereBas.setOnMousePressed(e -> {
			lum.rotateVertical(ce.fa, -0.1);
		});
		creerVue.setOnAction(e->{
			new SecondView(ce.fa).start(new Stage());
		});
		changeRep.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            fd.path=directoryChooser.showDialog(null);
            System.out.println(fd.path);
			fd.listFiles.getItems().clear();
            for (File file : fd.path.listFiles()) {
                if(file.toString().contains(".ply")) {
                    fd.listFiles.getItems().add(file);
                }
            }
        });
		startTimer.setOnMousePressed(e -> {
			fd.f.timerRotation();
			fd.f.setTimerActive(true);
		});
		stopTimer.setOnMousePressed(e -> {
			fd.f.cancelTimer();
			fd.f.setTimerActive(false);
		});
		onlyFace.setOnAction(e->{
			fd.dessinModele(ce.fa, true, false);
		});
		onlyStroke.setOnAction(e->{
			fd.dessinModele(ce.fa, false, true);
		});
		
		MouseControls mc = new MouseControls();
		mc.mouseDragged(c, ce.fa);
		fd.vb.getChildren().clear();
		nombreDePoints.setText("     Nombre de points : " + ps.getPoints().size());
		nombreDeFaces.setText("     Nombre de faces : " + ce.fa.getFaces().size());
		Label info = new Label("Informations : ");
		fd.vb.getChildren().add(info);
		fd.vb.getChildren().add(nombreDePoints);
		fd.vb.getChildren().add(nombreDeFaces);
		fd.vb.getChildren().add(date);
		fd.vb.getChildren().add(auteur);
		fd.vb.getChildren().add(new Separator());
		fd.vb.getChildren().add(new Label("Translation :"));
		fd.vb.getChildren().add(new HBox(new Label("     Incr�menter le X : "), plusX));
		fd.vb.getChildren().add(new HBox(new Label("     D�cr�menter le X : "), moinsX));
		fd.vb.getChildren().add(new HBox(new Label("     Incr�menter le Y : "), moinsY));
		fd.vb.getChildren().add(new HBox(new Label("     D�cr�menter le Y : "), plusY));
		fd.vb.getChildren().add(new Separator());
		fd.vb.getChildren().add(new Label("Rotation"));
		fd.vb.getChildren().add(new HBox(new Label("     Incr�menter en X : "), rotateXplus));
		fd.vb.getChildren().add(new HBox(new Label("     D�cr�menter en X : "), rotateXmoins));
		fd.vb.getChildren().add(new HBox(new Label("     Incr�menter en Y : "), rotateYplus));
		fd.vb.getChildren().add(new HBox(new Label("     D�cr�menter en Y : "), rotateYmoins));
		fd.vb.getChildren().add(new HBox(new Label("     Incr�menter en Z : "), rotateZplus));
		fd.vb.getChildren().add(new HBox(new Label("     D�cr�menter en Z : "), rotateZmoins));
		fd.vb.getChildren().add(new Separator());
		fd.vb.getChildren().add(creerVue);
		fd.vb.getChildren().add(new Separator());
		fd.vb.getChildren().add(new Label("Deplacer la lumiere : "));
		fd.vb.getChildren().add(new HBox(new Label("     Droite : "), rotateLumiereDroite));
		fd.vb.getChildren().add(new HBox(new Label("     Gauche : "), rotateLumiereGauche));
		fd.vb.getChildren().add(new HBox(new Label("     Haut : "), rotateLumiereHaut));
		fd.vb.getChildren().add(new HBox(new Label("     Bas : "), rotateLumiereBas));
		fd.vb.getChildren().add(new Separator());
		fd.vb.getChildren().add(new HBox(changeRep));
		fd.vb.getChildren().add(new Separator());
		fd.vb.getChildren().add(new HBox(startTimer));
		fd.vb.getChildren().add(new HBox(stopTimer));
		fd.vb.getChildren().add(onlyFace);
		fd.vb.getChildren().add(onlyStroke);
		if(fd.f !=null)fd.f.cancelTimer();
	}
}