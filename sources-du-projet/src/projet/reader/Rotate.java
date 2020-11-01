package projet.reader;

import java.util.ArrayList;
import java.util.List;

public class Rotate {
	public void rotateX(Faces f,double nb) {
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
				p.setY((p.getY()*Math.cos(nb))-(p.getZ()*Math.sin(nb)));
				p.setZ((p.getY()*(Math.sin(nb))+(p.getZ()*Math.cos(nb))));
				modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
	
	public void rotateY(Faces f,double nb) {
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
				p.setX((p.getX()*Math.cos(nb))+(p.getZ()*Math.sin(nb)));
				p.setZ((-(p.getX()*(Math.sin(nb)))+(p.getZ()*Math.cos(nb))));
				modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
	public void rotateZ(Faces f, double nb) {
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
				p.setX((p.getX()*Math.cos(nb))-(p.getY()*Math.sin(nb)));
				p.setY((p.getX()*(Math.sin(nb))+(p.getY()*Math.cos(nb))));
				modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
}
