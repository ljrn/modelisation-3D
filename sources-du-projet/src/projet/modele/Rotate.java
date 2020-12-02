package projet.modele;

import java.util.ArrayList;
import java.util.List;

public class Rotate {
	public void rotateX(Faces f,double nb) {
		Point mid=f.midPoint();
		Matrice m=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOx= {{1,0,0,0},{0,Math.cos(nb),-Math.sin(nb),0},{0, Math.sin(nb), Math.cos(nb), 0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice=m.multiplication(matriceTranslationVers0, matriceRotationOx);
		double[][] matriceFin=m.multiplication(matrice, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][] point=m.multiplication(p.toMatrice(),matriceFin);
					p.assign(point);
					modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
	
	public void rotateY(Faces f,double nb) {
		Point mid=f.midPoint();
		Matrice m=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOy= {{Math.cos(nb),0,-Math.sin(nb),0},{0,1,0,0},{Math.sin(nb), 0, Math.cos(nb), 0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice=m.multiplication(matriceTranslationVers0, matriceRotationOy);
		double[][] matriceFin=m.multiplication(matrice, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][] point=m.multiplication(p.toMatrice(),matriceFin);
					p.assign(point);
					modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
	public void rotateZ(Faces f, double nb) {
		Point mid=f.midPoint();
		Matrice m=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOz= {{Math.cos(nb),-Math.sin(nb),0,0},{Math.sin(nb),Math.cos(nb), 0,0},{0,0,1,0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice=m.multiplication(matriceTranslationVers0, matriceRotationOz);
		double[][] matriceFin=m.multiplication(matrice, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][] point=m.multiplication(p.toMatrice(),matriceFin);
					p.assign(point);
					modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
}
