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
	public void translate(Faces fa, Canvas c) {
		c.setOnMousePressed(event -> {
			if(event.getButton().equals(MouseButton.PRIMARY)) {
				translateX = event.getSceneX()-750;
				translateY = event.getSceneY()-750;
			}
		});

		c.setOnMouseDragged(event -> {
			if(event.getButton().equals(MouseButton.PRIMARY)) {
				for(Face f:fa.getFaces()) {
					for(Point p:f.getPoints()) {
						p.setX(event.getX()+translateX);
						p.setY(event.getY()+translateY);
						System.out.println("Translation: "+p.getX()+"       "+p.getY());
					}	
				}
			}
		});
		c.setOnMouseReleased(e->{
			fa.notifyObservers();
		});
	}
}
