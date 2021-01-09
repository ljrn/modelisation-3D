package projet.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation de l'operation d'homothetie sur l'ensmeble des points du modele
 */
public class Zoom {	
	private static Zoom INSTANCE;
	private Zoom() {
	}
	
	public static Zoom getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Zoom();
        }

        return INSTANCE;
    }
	/**
	 * Cette m�thode appplique le zoom � tous les points du mod�le
	 * @param f Ensemble des faces du mod�le
	 * @param factor Facteur de zoom
	 */
	public void zoom(Faces f,double factor) {
		Translation t=Translation.getInstance();
		Point mid=f.midPoint();
		t.translate(f, -mid.getX(),0,0);
		t.translate(f, 0,-mid.getY(),0);
		List<Point> modified = new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
				p.setY(p.getY()*factor);
				p.setX(p.getX()*factor);
				p.setZ(p.getZ()*factor);
				modified.add(p);
				}
			}
		}
		t.translate(f, mid.getX(),0,0);
		t.translate(f, 0,mid.getY(),0);
		f.notifyObservers(f);
	}
	
}





