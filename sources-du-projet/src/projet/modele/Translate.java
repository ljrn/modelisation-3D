package projet.modele;

import java.util.ArrayList;
import java.util.List;
public class Translate {	
	/**
	 * Cette méthode applique la translation à tous les points du modèle
	 * @param f Ensemble des faces du modèle
	 * @param x Translation en x
	 * @param y Translation en y
	 * @param z Translation en z
	 */
	public void translate(Faces f, double x, double y, double z) {
		double[][] matrice= {{1,0,0,x},{0,1,0,y},{0,0,1,z},{0,0,0,1}};
		Matrice m=new Matrice();
		List<Point> modified=new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][]point=m.multiplicationTranslation(p.toMatrice(), matrice);
					p.assign(point);
					modified.add(p);
				}
			}
		}
		f.notifyObservers(f);
	}
}
