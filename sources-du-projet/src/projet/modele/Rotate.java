package projet.modele;

import java.util.ArrayList;
import java.util.List;

public class Rotate {
	/**
	 * Cette méthode éxecute la rotation autour de l'axe des X pour tous les points du modèle
	 * @param faces Ensemble des Faces du modèles
	 * @param coeffRotX Coefficient de rotation
	 */
	public void rotateX(Faces faces,double coeffRotX) {
		Point mid=faces.midPoint();
		Matrice matrice1=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOx= {{1,0,0,0},{0,Math.cos(coeffRotX),-Math.sin(coeffRotX),0},{0, Math.sin(coeffRotX), Math.cos(coeffRotX), 0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice2=matrice1.multiplicationRotation(matriceTranslationVers0, matriceRotationOx);
		double[][] matriceFin=matrice1.multiplicationRotation(matrice2, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face fa:faces.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][] point=matrice1.multiplicationRotation(p.toMatrice(),matriceFin);
					p.assign(point);
					modified.add(p);
				}
			}
			fa.normale(fa.getPoints());
		}
		faces.notifyObservers(faces);
	}
	/**
	 * Cette méthode éxecute la rotation autour de l'axe des Y pour tous les points du modèle
	 * @param faces Ensemble des Faces du modèles
	 * @param coeffRotY Coefficient de rotation
	 */
	public void rotateY(Faces faces,double coeffRotY) {
		Point mid=faces.midPoint();
		Matrice matrice1=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOy= {{Math.cos(coeffRotY),0,-Math.sin(coeffRotY),0},{0,1,0,0},{Math.sin(coeffRotY), 0, Math.cos(coeffRotY), 0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice2=matrice1.multiplicationRotation(matriceTranslationVers0, matriceRotationOy);
		double[][] matriceFin=matrice1.multiplicationRotation(matrice2, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face fa:faces.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][] point=matrice1.multiplicationRotation(p.toMatrice(),matriceFin);
					p.assign(point);
					modified.add(p);
				}
			}
			fa.normale(fa.getPoints());
		}
		faces.notifyObservers(faces);
	}
	/**
	 * Cette méthode éxecute la rotation autour de l'axe des Z pour tous les points du modèle
	 * @param faces Ensemble des Faces du modèles
	 * @param coeffRotZ Coefficient de rotation
	 */
	public void rotateZ(Faces faces, double coeffRotZ) {
		Point mid=faces.midPoint();
		Matrice matrice1=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOz= {{Math.cos(coeffRotZ),-Math.sin(coeffRotZ),0,0},{Math.sin(coeffRotZ),Math.cos(coeffRotZ), 0,0},{0,0,1,0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice2=matrice1.multiplicationRotation(matriceTranslationVers0, matriceRotationOz);
		double[][] matriceFin=matrice1.multiplicationRotation(matrice2, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face face:faces.getFaces()) {
			for(Point point:face.getPoints()) {
				if(!modified.contains(point)) {
					double[][] matPoint=matrice1.multiplicationRotation(point.toMatrice(),matriceFin);
					point.assign(matPoint);
					modified.add(point);
				}
			}
			face.normale(face.getPoints());
		}
		faces.notifyObservers(faces);
	}
}
