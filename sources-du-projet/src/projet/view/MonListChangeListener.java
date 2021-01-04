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
	Canvas canvas;
	GraphicsContext graphicsContext;
	FormDisplay formDisplay;
	Label nombreDePoints = new Label();
	Label nombreDeFaces = new Label();
	Label date = new Label("     Date de creation : ");
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
		this.canvas = c;
		this.graphicsContext = gc;
		this.formDisplay = fd;
	}

	public void onChanged(javafx.collections.ListChangeListener.Change<? extends File> ch) {
		CreateEnvironment ce = new CreateEnvironment();
		auteur.setText("     Nom de l'auteur : ");
		date.setText("     Date de creation : ");
		FileCreator creator = new FileCreator();
		File theFile = creator.getFile(formDisplay.path, ch.getList().toString());
		ce.createFaces(theFile, formDisplay.width, formDisplay.height);
		auteur.setText(auteur.getText() + creator.getAuthor(theFile));
		date.setText(date.getText() + creator.getDate(theFile));
		formDisplay.stroke=true;
		formDisplay.fill=true;
		Points ps = ce.pts;
		ce.fa.attach(formDisplay);
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.setWidth(ps.maxX());
		canvas.setHeight(ps.maxY());
		formDisplay.dessinModele(ce.fa, true, true);
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
		canvas.setOnScroll(e -> {
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
            formDisplay.path=directoryChooser.showDialog(null);
            System.out.println(formDisplay.path);
			formDisplay.listFiles.getItems().clear();
            for (File file : formDisplay.path.listFiles()) {
                if(file.toString().contains(".ply")) {
                    formDisplay.listFiles.getItems().add(file);
                }
            }
        });
		startTimer.setOnMousePressed(e -> {
			formDisplay.f.timerRotation();
			formDisplay.f.setTimerActive(true);
		});
		stopTimer.setOnMousePressed(e -> {
			formDisplay.f.cancelTimer();
			formDisplay.f.setTimerActive(false);
		});
		onlyFace.setOnAction(e->{
			formDisplay.fill=true;
			formDisplay.stroke=false;
			formDisplay.dessinModele(ce.fa, true, false);
		});
		onlyStroke.setOnAction(e->{
			formDisplay.stroke=true;
			formDisplay.fill=false;
			formDisplay.dessinModele(ce.fa, false, true);
		});
		
		MouseControls mc = new MouseControls();
		mc.mouseDragged(canvas, ce.fa);
		formDisplay.vb.getChildren().clear();
		nombreDePoints.setText("     Nombre de points : " + ps.getPoints().size());
		nombreDeFaces.setText("     Nombre de faces : " + ce.fa.getFaces().size());
		Label info = new Label("Informations : ");
		formDisplay.vb.getChildren().addAll(info,nombreDePoints,nombreDeFaces, date, auteur);
		formDisplay.vb.getChildren().addAll(new Separator(),new Label("Translation :"),new HBox(new Label("     Incrementer le X : "), plusX));
		formDisplay.vb.getChildren().addAll(new HBox(new Label("     Decrementer le X : "), moinsX),new HBox(new Label("     Incrementer le Y : "), moinsY));
		formDisplay.vb.getChildren().addAll(new HBox(new Label("     Decrementer le Y : "), plusY),new Separator(),new Label("Rotation"));
		formDisplay.vb.getChildren().addAll(new HBox(new Label("     Incrementer en X : "), rotateXplus),new HBox(new Label("     Decrementer en X : "), rotateXmoins));
		formDisplay.vb.getChildren().addAll(new HBox(new Label("     Incrementer en Y : "), rotateYplus),new HBox(new Label("     Decrementer en Y : "), rotateYmoins));
		formDisplay.vb.getChildren().addAll(new HBox(new Label("     Incrementer en Z : "), rotateZplus),new HBox(new Label("     Decrementer en Z : "), rotateZmoins));
		formDisplay.vb.getChildren().addAll(new Separator(),creerVue,new Separator(),new Label("Deplacer la lumiere : "));
		formDisplay.vb.getChildren().addAll(new HBox(new Label("     Droite : "), rotateLumiereDroite),new HBox(new Label("     Gauche : "), rotateLumiereGauche));
		formDisplay.vb.getChildren().addAll(new HBox(new Label("     Haut : "), rotateLumiereHaut),new HBox(new Label("     Bas : "), rotateLumiereBas));
		formDisplay.vb.getChildren().addAll(new Separator(),new HBox(changeRep),new Separator(),new HBox(startTimer),new HBox(stopTimer),onlyFace,onlyStroke);
		if(formDisplay.f !=null)formDisplay.f.cancelTimer();
	}
}