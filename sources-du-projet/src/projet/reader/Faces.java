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
	
	public void translateFacesX(double nbPxl) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setX(p.getX() + nbPxl);
				modified.add(p);
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
	
	public void translateFacesY(double nbPxl) {
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
	
	public void zoomfaces(double factor) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setY(p.getY()*factor);
				p.setX(p.getX()*factor);
				p.setZ(p.getZ()*factor);
				modified.add(p);
				}
			}
		}
		this.notifyObservers(this);
	}
	
	public void rotateFacesX(double theta) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setY((p.getY()*Math.cos(theta))-(p.getZ()*Math.sin(theta)));
				p.setZ((p.getY()*(Math.sin(theta))+(p.getZ()*Math.cos(theta))));
				modified.add(p);
				}
			}
		}
		this.notifyObservers(this);
	}
	
	public void rotateFacesY(double theta) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setX((p.getX()*Math.cos(theta))+(p.getZ()*Math.sin(theta)));
				p.setZ((-(p.getX()*(Math.sin(theta)))+(p.getZ()*Math.cos(theta))));
				modified.add(p);
				}
			}
		}
		this.notifyObservers(this);
	}
	
	public void rotateFacesZ(double theta) {
		List<Point> modified = new ArrayList<>();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(!modified.contains(p)) {
				p.setX((p.getX()*Math.cos(theta))-(p.getY()*Math.sin(theta)));
				p.setY((p.getX()*(Math.sin(theta))+(p.getY()*Math.cos(theta))));
				modified.add(p);
				}
			}
		}
		this.notifyObservers(this);
	}

	public void setFaces(ArrayList<Face> listFs) {
		this.faces = listFs;
	}
	
}
