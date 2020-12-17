package projet.view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projet.modele.Faces;
import projet.modele.Zoom;
import projet.utils.Observer;
import projet.utils.Subject;

public class SecondView extends FormDisplay implements Observer{
	double width;
	double height;
	Canvas c = new Canvas(300, 300);
	GraphicsContext gc = c.getGraphicsContext2D();
	VBox vb = new VBox();
	Faces fa;
	public SecondView(Faces fa) {
		this.fa=fa;
	}
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		gc.setFill(Color.DARKGREY);
		gc.setStroke(Color.GREY);
		width = c.getWidth();
		height = c.getHeight();
		fa.attach(this);
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
		c.setWidth(fa.maxX());
		c.setHeight(fa.maxY());
		System.out.println(fa.maxX());
		this.dessinModele(fa);
		Zoom z = new Zoom();
		c.setOnScroll(e -> {
			if (e.getDeltaY() > 0)
				z.zoom(fa, 1.05);
			else
				z.zoom(fa, 0.95);
		});
		MouseControls mc = new MouseControls();
		mc.mouseDragged(c, fa);
		root.getChildren().add(c);
		Scene scene = new Scene(root, 1500, 1000);
		primaryStage.setScene(scene); 
		primaryStage.show();
	}
	public HBox listFiles(Canvas c, GraphicsContext gc, FormDisplay fd) {return null;}
	@Override
	public void update(Subject subj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Subject subj, Object data) {
		super.update(subj, data);
	}
}
