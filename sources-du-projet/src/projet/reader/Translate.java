package projet.reader;

import java.util.ArrayList;
import java.util.List;

public class Translate {	
	public void translateX(Faces f,double nb) {
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
				p.setX(p.getX() + nb);
				modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
	
	public void translateY(Faces f,double nb) {
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
				p.setY(p.getY()+nb);
				modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
}
