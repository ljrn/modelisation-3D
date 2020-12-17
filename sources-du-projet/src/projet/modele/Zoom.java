package projet.modele;

import java.util.ArrayList;
import java.util.List;

public class Zoom {	
	public void zoom(Faces f,double factor) {
		Translate t=new Translate();
		Point mid=f.midPoint();
		t.translate(f, -mid.getX(),0,0);
		t.translate(f, 0,-mid.getY(),0);
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
				p.setY(p.getY()*factor);
				p.setX(p.getX()*factor);
				p.setZ(p.getZ()*factor);
				modified.add(p);
				}
			}
		}
		t.translate(f, mid.getX(),0,0);
		t.translate(f, 0,mid.getY(),0);
		f.notifyObservers(f);
	}
	
}
