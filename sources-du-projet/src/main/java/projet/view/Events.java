package main.java.projet.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import main.java.projet.reader.Points;

public class Events {
	private double anchorX;
	private double anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(0);

	public void rotation(Canvas c) {
		Rotate xRotate;
		Rotate yRotate;
		c.getTransforms().addAll(
				xRotate = new Rotate(0, Rotate.X_AXIS),
				yRotate = new Rotate(0, Rotate.Y_AXIS)
				);
		xRotate.angleProperty().bind(angleX);
		yRotate.angleProperty().bind(angleY);

		c.setOnMousePressed(event -> {
			anchorX = event.getSceneX();
			anchorY = event.getSceneY();
			anchorAngleX = angleX.get();
			anchorAngleY = angleY.get();
		});

		c.setOnMouseDragged(event -> {
			angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
			angleY.set(anchorAngleY + anchorX - event.getSceneX());
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
	public void translate(Points ps, Canvas c, GraphicsContext gc) {
		
	}
}
