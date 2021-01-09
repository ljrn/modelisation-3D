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
import javafx.stage.Modality;
import javafx.stage.Stage;
import projet.modele.CreateEnvironment;
import projet.modele.FileCreator;
import projet.modele.Lumiere;
import projet.modele.Points;
import projet.modele.Rotate;
import projet.modele.Translate;
import projet.modele.Zoom;

/**
 * Classe permettant d'initialiser tous les boutons de la vue et de charger les modèles lors d'un changement de choix
 */
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
	
	/**
	 * 
	 * @param canvas Canvas dans lequel est dessinee la forme
	 * @param context GraphicsContext lie au Canvas
	 * @param formDisplay Fenetre dans laquelle est le Canvas
	 */
	public MonListChangeListener(Canvas canvas, GraphicsContext context, FormDisplay formDisplay) {
		this.canvas = canvas;
		this.graphicsContext = context;
		this.formDisplay = formDisplay;
	}
	/**
	 * Cette methode permet d'initialiser et de dessiner la figure lorsqu'un nouveau modele est selectionne dans la liste et aussi
	 * d'initialiser les boutons de la fenetre.
	 */
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends File> ch) {
		CreateEnvironment env = new CreateEnvironment();
		auteur.setText("     Nom de l'auteur : ");
		date.setText("     Date de creation : ");
		FileCreator creator = new FileCreator();
		File theFile = creator.getFile(formDisplay.path, ch.getList().toString());
		env.createFaces(theFile, formDisplay.width, formDisplay.height);
		auteur.setText(auteur.getText() + creator.getAuthor(theFile));
		date.setText(date.getText() + creator.getDate(theFile));
		formDisplay.stroke=true;
		formDisplay.fill=true;
		Points points = env.points;
		env.faces.attach(formDisplay);
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.setWidth(points.maxX());
		canvas.setHeight(points.maxY());
		formDisplay.dessinModele(env.faces, true, true);
		Translate translate = new Translate();
		plusX.setOnAction(e -> {
			translate.translate(env.faces, 3.0, 0, 0);
		});
		moinsX.setOnAction(e -> {
			translate.translate(env.faces, -3.0, 0, 0);
		});
		plusY.setOnAction(e -> {
			translate.translate(env.faces, 0, 3.0, 0);
		});
		moinsY.setOnAction(e -> {
			translate.translate(env.faces, 0, -3.0, 0);
		});
		Zoom zoom = new Zoom();
		canvas.setOnScroll(e -> {
			if (e.getDeltaY() > 0)
				zoom.zoom(env.faces, 1.05);
			else
				zoom.zoom(env.faces, 0.95);
		});
		Rotate rotate = new Rotate();
		rotateXplus.setOnAction(e -> {
			rotate.rotateX(env.faces, 0.05);
		});
		rotateXmoins.setOnAction(e -> {
			rotate.rotateX(env.faces, -0.05);
		});
		rotateZplus.setOnAction(e -> {
			rotate.rotateZ(env.faces, 0.05);
		});
		rotateZmoins.setOnAction(e -> {
			rotate.rotateZ(env.faces, -0.05);
		});
		rotateYplus.setOnMousePressed(e -> {
			rotate.rotateY(env.faces, 0.05);
		});
		rotateYmoins.setOnMousePressed(e -> {
			rotate.rotateY(env.faces, -0.05);
		});
		Lumiere lum = new Lumiere();
		rotateLumiereDroite.setOnMousePressed(e -> {
			lum.rotateHorizontal(env.faces, 0.1);
		});
		rotateLumiereGauche.setOnMousePressed(e -> {
			lum.rotateHorizontal(env.faces, -0.1);
		});
		rotateLumiereHaut.setOnMousePressed(e -> {
			lum.rotateVertical(env.faces, 0.1);
		});
		rotateLumiereBas.setOnMousePressed(e -> {
			lum.rotateVertical(env.faces, -0.1);
		});
		creerVue.setOnAction(e->{
			FormDisplay secondFormDisplay =new FormDisplay();
			Stage stage=new Stage();
			stage.initOwner(formDisplay.stage);
			secondFormDisplay.start(stage);
		});
		changeRep.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File tmpFile = formDisplay.path;
            formDisplay.path=directoryChooser.showDialog(null);
            if(formDisplay.path!=null) {
            	System.out.println(formDisplay.path);
    			formDisplay.listFiles.getItems().clear();
                for (File file : formDisplay.path.listFiles()) {
                    if(file.toString().contains(".ply")) {
                        formDisplay.listFiles.getItems().add(file);
                    }
                }
            }else {
            	formDisplay.path=tmpFile;
            }
            
        });
		startTimer.setOnMousePressed(e -> {
			formDisplay.faces.timerRotation();
			formDisplay.faces.setTimerActive(true);
		});
		stopTimer.setOnMousePressed(e -> {
			formDisplay.faces.cancelTimer();
			formDisplay.faces.setTimerActive(false);
		});
		onlyFace.setOnAction(e->{
			formDisplay.fill=true;
			formDisplay.stroke=false;
			formDisplay.dessinModele(env.faces, true, false);
		});
		onlyStroke.setOnAction(e->{
			formDisplay.stroke=true;
			formDisplay.fill=false;
			formDisplay.dessinModele(env.faces, false, true);
		});
		
		formDisplay.rechercher.setOnMousePressed(e -> {
            formDisplay.listFiles.getItems().clear();
            for (File file : formDisplay.path.listFiles()) {
                if(file.toString().contains(".ply") && file.toString().contains(formDisplay.textField.getText())) {
                    formDisplay.listFiles.getItems().add(file);
                }else if (formDisplay.textField.getText().equals("")){
                    formDisplay.listFiles.getItems().add(file);

                }
            }
        });
		
		MouseControls controls = new MouseControls();
		controls.mouseDragged(canvas, env.faces);
		formDisplay.vbox.getChildren().clear();
		nombreDePoints.setText("     Nombre de points : " + points.getPoints().size());
		nombreDeFaces.setText("     Nombre de faces : " + env.faces.getFaces().size());
		Label info = new Label("Informations : ");
		formDisplay.vbox.getChildren().addAll(info,nombreDePoints,nombreDeFaces, date, auteur);
		formDisplay.vbox.getChildren().addAll(new Separator(),new Label("Translation :"),new HBox(new Label("     Incrementer le X : "), plusX));
		formDisplay.vbox.getChildren().addAll(new HBox(new Label("     Decrementer le X : "), moinsX),new HBox(new Label("     Incrementer le Y : "), moinsY));
		formDisplay.vbox.getChildren().addAll(new HBox(new Label("     Decrementer le Y : "), plusY),new Separator(),new Label("Rotation"));
		formDisplay.vbox.getChildren().addAll(new HBox(new Label("     Incrementer en X : "), rotateXplus),new HBox(new Label("     Decrementer en X : "), rotateXmoins));
		formDisplay.vbox.getChildren().addAll(new HBox(new Label("     Incrementer en Y : "), rotateYplus),new HBox(new Label("     Decrementer en Y : "), rotateYmoins));
		formDisplay.vbox.getChildren().addAll(new HBox(new Label("     Incrementer en Z : "), rotateZplus),new HBox(new Label("     Decrementer en Z : "), rotateZmoins));
		formDisplay.vbox.getChildren().addAll(new Separator(),creerVue,new Separator(),new Label("Deplacer la lumiere : "));
		formDisplay.vbox.getChildren().addAll(new HBox(new Label("     Droite : "), rotateLumiereDroite),new HBox(new Label("     Gauche : "), rotateLumiereGauche));
		formDisplay.vbox.getChildren().addAll(new HBox(new Label("     Haut : "), rotateLumiereHaut),new HBox(new Label("     Bas : "), rotateLumiereBas));
		formDisplay.vbox.getChildren().addAll(new Separator(),new HBox(changeRep),new Separator(),new HBox(startTimer),new HBox(stopTimer),onlyFace,onlyStroke);
		if(formDisplay.faces !=null)formDisplay.faces.cancelTimer();
	}
}