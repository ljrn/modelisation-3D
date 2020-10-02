package projet.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Events {
	//Tracks drag starting point for x and y
	private double anchorX, anchorY;
	//Keep track of current angle for x and y
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	//We will update these after drag. Using JavaFX property to bind with object
	private final DoubleProperty angleX = new SimpleDoubleProperty(0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(0);

	public void rotation(Group group, Scene scene) {
		Rotate xRotate;
		Rotate yRotate;
		group.getTransforms().addAll(
				xRotate = new Rotate(0, Rotate.X_AXIS),
				yRotate = new Rotate(0, Rotate.Y_AXIS)
				);
		xRotate.angleProperty().bind(angleX);
		yRotate.angleProperty().bind(angleY);

		scene.setOnMousePressed(event -> {
			if(event.getButton().equals(MouseButton.MIDDLE)) {
			anchorX = event.getSceneX();
			anchorY = event.getSceneY();
			anchorAngleX = angleX.get();
			anchorAngleY = angleY.get();
			}
		});

		scene.setOnMouseDragged(event -> {
			if(event.getButton().equals(MouseButton.MIDDLE)) {
			angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
			angleY.set(anchorAngleY + anchorX - event.getSceneX());
			}
		});
	}
	public void zoom(Canvas c) {
        c.setOnScroll((ScrollEvent event) -> {
            double multiplicateur = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                multiplicateur = 2.0 - multiplicateur;
            }
            c.setScaleX(c.getScaleX() * multiplicateur);
            c.setScaleY(c.getScaleY() * multiplicateur);
        });
    }
	
	public void move(Group group, Scene scene) {
		Translate t;
		
		scene.setOnMousePressed(event -> {
			if(event.getButton().equals(MouseButton.PRIMARY)) {
			anchorX = event.getSceneX();
			anchorY = event.getSceneY();
			}
		});
		
		group.getTransforms().addAll(
				t = new Translate(anchorX,anchorY)
				);

		scene.setOnMouseDragged(event -> {
			if(event.getButton().equals(MouseButton.PRIMARY)) {
			System.out.println(event.getX());
			System.out.println(event.getY());

			t.setX(event.getX());
			t.setY(event.getY());
			}
		});
	}
}
