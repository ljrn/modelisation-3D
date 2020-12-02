package projet.controls;

import javafx.scene.canvas.Canvas;
import projet.modele.Faces;
import projet.modele.Rotate;
import projet.modele.Translate;

public class MouseControls {
	double previousX=0.0;
	double previousY=0.0;
	public void mouseDragged(Canvas c, Faces f) {
		Translate t=new Translate();
		Rotate r=new Rotate();
		c.setOnMousePressed(e->{
			previousX=e.getSceneX();
			previousY=e.getSceneY();
		});
		c.setOnMouseDragged(e->{
			double x = e.getScreenX();
			double y = e.getScreenY();
			if(e.isPrimaryButtonDown()) {
				if(x!=previousX) {
					t.translate(f,(x-previousX),0,0);
					previousX=x;
				}
				if(y!=previousY) {
					t.translate(f,0,(y-previousY),0);
					previousY=y;
				}
				
			}
			if(e.isSecondaryButtonDown()) {
				if(x!=previousX) {
					r.rotateY(f,(x-previousX)/1000);
					previousX=x;
				}
				if(y!=previousY) {
					r.rotateX(f,(y-previousY)/1000);
					previousY=y;
				}
			}
		});
	}
}
