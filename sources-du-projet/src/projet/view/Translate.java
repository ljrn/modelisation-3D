package projet.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Point;

public class Translate {
	double translateX=0;
	double translateY=0;
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
