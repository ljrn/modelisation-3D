package projet.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Translate;
import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Point;
import projet.reader.Points;


public class Events {
	private double anchorX;
	private double anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private double translateX=0;
	private double translateY=0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(0);

	/*public void rotation(Canvas c, Point3D x, Point3D y) {
		Rotate xRotate;
		Rotate yRotate;
		c.getTransforms().addAll(
			xRotate = new Rotate(0, x),
			yRotate = new Rotate(0, y)
		);
		xRotate.angleProperty().bind(angleX);
		yRotate.angleProperty().bind(angleY);
		c.setOnMousePressed(event -> {
			if(event.getButton().equals(MouseButton.SECONDARY)) {
				anchorX = event.getSceneX();
				anchorY = event.getSceneY();
				anchorAngleX = angleX.get();
				anchorAngleY = angleY.get();
			}
		});

		c.setOnMouseDragged(event -> {
			if(event.getButton().equals(MouseButton.SECONDARY)) {
				angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
				angleY.set(anchorAngleY + anchorX - event.getSceneX());
			}
		});
	}*/
	public void zoom(Canvas c) {
        c.setOnScroll((ScrollEvent event) -> {
            double multiplicateur = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                multiplicateur = 2.0 - multiplicateur;
            }
            c.setScaleX(c.getScaleX() * multiplicateur);
            c.setScaleY(c.getScaleY() * multiplicateur);
            System.out.println(c.getScaleX()+";"+c.getScaleY());
        });
    }
	
	public void move(Canvas c) {
		Translate t;
		c.setOnMousePressed(event -> {
			if(event.getButton().equals(MouseButton.PRIMARY)) {
				translateX = event.getSceneX();
				translateY = event.getSceneY();
			}
		});
		
		c.getTransforms().addAll(
			t = new Translate(translateX,translateY)
		);

		c.setOnMouseDragged(event -> {
			if(event.getButton().equals(MouseButton.PRIMARY)) {
				t.setX(event.getX());
				t.setY(event.getY());
			}
		});
	}

	public void translate(Faces fa, Canvas c, GraphicsContext gc) {
		c.setOnMousePressed(event -> {
			if(event.getButton().equals(MouseButton.PRIMARY)) {
				translateX = event.getSceneX()-1000;
				translateY = event.getSceneY()-1000;
			}
		});

		c.setOnMouseDragged(event -> {
			if(event.getButton().equals(MouseButton.PRIMARY)) {
				for(Face f:fa.getFaces()) {
					for(Point p:f.getPoints()) {
						p.setX(event.getX()+translateX);
						p.setY(event.getY()+translateY);
						System.out.println(p);
					}	
				}
			}
		});
		c.setOnMouseReleased(e->{
			gc.clearRect(0, 0, c.getWidth(), c.getHeight());
			for(Face f:fa.getFaces()) {
				gc.fillPolygon(f.getPointsX(), f.getPointsY(),f.getNbPoint());
				gc.strokePolygon(f.getPointsX(), f.getPointsY(), f.getNbPoint());
			}
		});
	}
}