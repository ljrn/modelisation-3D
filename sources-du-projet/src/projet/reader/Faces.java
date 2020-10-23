package projet.reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projet.utils.Subject;

public class Faces extends Subject{
	private List<Face> faces = new ArrayList<Face>();
	
	public void decompStringFaces(List<String> stringFaces, Points ps) {
		for (String string : stringFaces) {
			String[] tab = string.split(" ");
			int nbFaces = Integer.parseInt(tab[0]);
			List<Integer> facesInt = new ArrayList<Integer>();
			for (int i = 1; i < tab.length; i++) {
				facesInt.add(Integer.parseInt(tab[i]));
			}
			List<Point> p=new ArrayList<Point>();
			for(int i=0; i<facesInt.size();i++){
				p.add(ps.getPoints().get(facesInt.get(i)));
			}
			this.faces.add(new Face(nbFaces,p));
		}
	}

	public List<Face> getFaces() {
		return faces;
	}

	public void trierFaces() {
		Collections.sort(faces);
	}
	
	public void incrementFacesX() {
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				p.setX(p.getX()+2.0);
			}
		}
		this.notifyObservers(this);
	}
	public void decrementFacesX() {
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				p.setX(p.getX()-2.0);
			}
		}
		this.notifyObservers(this);
	}
	public void incrementFacesY() {
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				p.setY(p.getY()+2.0);
			}
		}
		this.notifyObservers(this);
	}
	public void decrementFacesY() {
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				p.setY(p.getY()-2.0);
			}
		}
		this.notifyObservers(this);
	}
	
}
