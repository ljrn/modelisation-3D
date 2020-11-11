package projet.reader;

import java.util.ArrayList;
import java.util.List;

import projet.utils.Matrice;

public class Translate {	
	/*public void translateX(Faces f,double nb) {
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
	}*/
	public void translate(Faces f, double x, double y, double z) {
		double[][] matrice= {{1,0,0,x},{0,1,0,y},{0,0,1,z},{0,0,0,1}};
		Matrice m=new Matrice(matrice);
		List<Point> modified=new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					p.multiplyMatrice(m);
					modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
}
