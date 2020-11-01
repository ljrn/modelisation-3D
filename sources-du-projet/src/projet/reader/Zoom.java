package projet.reader;

import java.util.ArrayList;
import java.util.List;

public class Zoom {	
	public void zoom(Faces f,double factor) {
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
		f.notifyObservers(f);
	}
	
}
