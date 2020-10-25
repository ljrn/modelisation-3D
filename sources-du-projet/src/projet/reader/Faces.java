package projet.reader;

import java.math.BigDecimal;
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
	
	public void incrementFacesX(double nbPxl) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setX(p.getX() + nbPxl);
				modified.add(p);
				System.out.println(p.toString());
				}
			}
		}
		this.notifyObservers(this);
	}
	public void decrementFacesX(double nbPxl) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setX(p.getX()- nbPxl);
				modified.add(p);
				}
			}
		}
		this.notifyObservers(this);
	}
	
	public void incrementFacesY(double nbPxl) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setY(p.getY()+nbPxl);
				modified.add(p);
				}
			}
		}
		this.notifyObservers(this);
	}
	
	public void decrementFacesY(double nbPxl) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setY(p.getY()- nbPxl);
				modified.add(p);
				}
			}
		}
		this.notifyObservers(this);
	}
	
}
