package projet.modele;

import java.util.ArrayList;
import java.util.List;

public class Rotate {
	public void rotateX(Faces f,double nb) {
		Translate t=new Translate();
		Point mid=f.midPoint();
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					p.setY(((p.getY()-mid.getY())*Math.cos(nb))-(p.getZ()*Math.sin(nb))+mid.getY());
					p.setZ(((p.getY()-mid.getY())*Math.sin(nb))+(p.getZ()*Math.cos(nb)));
					modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
	
	public void rotateY(Faces f,double nb) {
		Translate t=new Translate();
		Point mid=f.midPoint();
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					p.setX(((p.getX()-mid.getX())*Math.cos(nb))-(p.getZ()*Math.sin(nb))+mid.getX());				p.setZ(((p.getY()-mid.getY())*Math.sin(nb))+(p.getZ()*Math.cos(nb)));
					p.setZ(((p.getX()-mid.getX())*Math.sin(nb))+(p.getZ()*Math.cos(nb)));
					modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
	public void rotateZ(Faces f, double nb) {
		Translate t=new Translate();
		Point mid=f.midPoint();
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
				p.setX(((p.getX()-mid.getX())*Math.cos(nb))-((p.getY()-mid.getY())*Math.sin(nb))+mid.getX());
				p.setY(((p.getX()-mid.getX())*Math.sin(nb))+((p.getY()-mid.getY())*Math.cos(nb))+mid.getY());
				modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
}
