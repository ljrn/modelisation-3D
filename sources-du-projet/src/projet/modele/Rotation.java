package projet.modele;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementation de l'operation de rotation sur l'ensmeble des points du modele
 */
public class Rotation {
	private static Rotation INSTANCE;
	private Rotation() {
	}
	
	public static Rotation getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Rotation();
        }

        return INSTANCE;
    }
	/**
	 * Cette méthode éxecute la rotation autour de l'axe des X pour tous les points du modèle
	 * @param f Ensemble des Faces du modèles
	 * @param nb Coefficient de rotation
	 */
	public void rotateX(Faces f,double nb) {
		Point mid=f.midPoint();
		Matrice m=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOx= {{1,0,0,0},{0,Math.cos(nb),-Math.sin(nb),0},{0, Math.sin(nb), Math.cos(nb), 0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice=m.multiplicationRotation(matriceTranslationVers0, matriceRotationOx);
		double[][] matriceFin=m.multiplicationRotation(matrice, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][] point=m.multiplicationRotation(p.toMatrice(),matriceFin);
					p.assign(point);
					modified.add(p);
				}
			}
			fa.normale(fa.getPoints());
		}
		f.notifyObservers(f);
	}
	/**
	 * Cette méthode éxecute la rotation autour de l'axe des Y pour tous les points du modèle
	 * @param f Ensemble des Faces du modèles
	 * @param nb Coefficient de rotation
	 */
	public void rotateY(Faces f,double nb) {
		Point mid=f.midPoint();
		Matrice m=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOy= {{Math.cos(nb),0,-Math.sin(nb),0},{0,1,0,0},{Math.sin(nb), 0, Math.cos(nb), 0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice=m.multiplicationRotation(matriceTranslationVers0, matriceRotationOy);
		double[][] matriceFin=m.multiplicationRotation(matrice, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][] point=m.multiplicationRotation(p.toMatrice(),matriceFin);
					p.assign(point);
					modified.add(p);
				}
			}
			fa.normale(fa.getPoints());
		}
		f.notifyObservers(f);
	}
	/**
	 * Cette méthode éxecute la rotation autour de l'axe des Z pour tous les points du modèle
	 * @param f Ensemble des Faces du modèles
	 * @param nb Coefficient de rotation
	 */
	public void rotateZ(Faces f, double nb) {
		Point mid=f.midPoint();
		Matrice m=new Matrice();
		double[][] matriceTranslationVers0= {{1,0,0,-mid.getX()},{0,1,0,-mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matriceRotationOz= {{Math.cos(nb),-Math.sin(nb),0,0},{Math.sin(nb),Math.cos(nb), 0,0},{0,0,1,0},{0,0,0,1}};
		double[][] matriceTranslationCentre={{1,0,0,mid.getX()},{0,1,0,mid.getY()},{0,0,1,0},{0,0,0,1}};
		double[][] matrice=m.multiplicationRotation(matriceTranslationVers0, matriceRotationOz);
		double[][] matriceFin=m.multiplicationRotation(matrice, matriceTranslationCentre);
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][] point=m.multiplicationRotation(p.toMatrice(),matriceFin);
					p.assign(point);
					modified.add(p);
				}
			}
			fa.normale(fa.getPoints());
		}
		f.notifyObservers(f);
	}
}

