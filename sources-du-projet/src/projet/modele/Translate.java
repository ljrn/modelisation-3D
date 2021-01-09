package projet.modele;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementation de l'operation de translation sur l'ensmeble des points du modele
 */
public class Translate {	
	/**
	 * Cette m�thode applique la translation � tous les points du mod�le
	 * @param faces Ensemble des faces du mod�le
	 * @param x Translation en x
	 * @param y Translation en y
	 * @param z Translation en z
	 */
	public void translate(Faces faces, double x, double y, double z) {
		double[][] matriceTab= {{1,0,0,x},{0,1,0,y},{0,0,1,z},{0,0,0,1}};
		Matrice myMatrice=new Matrice();
		List<Point> modified=new ArrayList<>();
		for(Face face:faces.getFaces()) {
			for(Point point:face.getPoints()) {
				if(!modified.contains(point)) {
					double[][] matPoint=myMatrice.multiplicationTranslation(point.toMatrice(), matriceTab);
					point.assign(matPoint);
					modified.add(point);
				}
			}
		}
		faces.notifyObservers(faces);
	}
}
