package projet.modele;

import java.util.ArrayList;
import java.util.List;

public class Zoom {	
	/**
	 * Cette méthode appplique le zoom à tous les points du modèle
	 * @param faces Ensemble des faces du modèle
	 * @param factor Facteur de zoom
	 */
	public void zoom(Faces faces,double factor) {
		Translate translate=new Translate();
		Point mid=faces.midPoint();
		translate.translate(faces, -mid.getX(),0,0);
		translate.translate(faces, 0,-mid.getY(),0);
		List<Point> modified = new ArrayList<>();
		for(Face face:faces.getFaces()) {
			for(Point point:face.getPoints()) {
				if(!modified.contains(point)) {
				point.setY(point.getY()*factor);
				point.setX(point.getX()*factor);
				point.setZ(point.getZ()*factor);
				modified.add(point);
				}
			}
		}
		translate.translate(faces, mid.getX(),0,0);
		translate.translate(faces, 0,mid.getY(),0);
		faces.notifyObservers(faces);
	}
	
}
