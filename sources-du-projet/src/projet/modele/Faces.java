package projet.modele;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import projet.utils.Subject;

public class Faces extends Subject{
	private List<Face> faces = new ArrayList<>();
	private static Vecteur lumiere=new Vecteur(1,1,-1);
	private Timer timer;
	private boolean timerActive = false;
	 
	/**
	 *  
	 * @return true si le Timer est actif
	 */
	public boolean isTimerActive() {
		return timerActive;
	}
	/**
	 * Cette m�thode permet de donner la valeur de l'attribut boolean de classe timerActive
	 */
	public void setTimerActive(boolean timerActive) {
		this.timerActive = timerActive;
	}
	/**
	 * 
	 * @return Le vecteur lumi�re
	 */
	public Vecteur getLumiere() {
		return lumiere;
	}
	
	/**
	 * 
	 * @param lumiere Valeur qui va �tre donn�e au vecteur lumi�re
	 */
	public void setLumiere(Vecteur lumiere) {
		Faces.lumiere = lumiere;
	}	
	
	/**
	 * 
	 * @param stringFaces Liste de String contenant la description de chaque face
	 * @param ps Liste des points devant �tre reli�s pour former les faces
	 */
	public void decompStringFaces(List<String> stringFaces, Points ps) {
		for (String string : stringFaces) {
			String[] tab = string.split(" ");
			int nbFaces = Integer.parseInt(tab[0]);
			List<Integer> facesInt = new ArrayList<>();
			for (int i = 1; i < tab.length; i++) {
				facesInt.add(Integer.parseInt(tab[i]));
			}
			List<Point> p=new ArrayList<>();
			for(int i=0; i<facesInt.size();i++){
				p.add(ps.getPoints().get(facesInt.get(i)));
			}
			this.faces.add(new Face(nbFaces,p));
		}
	}
	
	/**
	 * Cette m�thode permet de calculer la couleur de chaque face en fonction du vecteur lumi�re
	 */
	public void colorDiffuseFace() {
		for (Face face : faces) {
			Vecteur normaleFace=face.getNormal();
			lumiere.unitaire();
			double scalaire=normaleFace.getX()*lumiere.getX()+normaleFace.getY()*lumiere.getY()+normaleFace.getZ()*lumiere.getZ();
			face.setColor(Math.abs(scalaire));
		}
	}
	
	/**
	 * @return La liste des faces
	 */
	public List<Face> getFaces() {
		return faces;
	}
	
	/*
	 * Cette m�thode permet de trier les faces gr�ce � l'algorithme du peintre 
	 */
	public void trierFaces() {
		Collections.sort(faces);
	}
	
	/**
	 * 
	 * @return Le centre de gravit� de la figure
	 */
	public Point midPoint() {
		return new Point(this.maxX()/2, this.maxY()/2,0);
	}

	/**
	 * 
	 * @param list Liste des faces devant �tre allou�es dans le param�tre de classe "faces"
	 */
	public void setFaces(List<Face> list) {
		this.faces = list;
	}
	
	/**
	 * 
	 * @return La coordonn�e maximale en x de tous les points de la figure
	 */
	public double maxX() {
		double max=faces.get(0).getPoints().get(0).getX();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(p.getX()>max)max=p.getX();
			}
		}return max;
	}
	
	/**
	 * 
	 * @return La coordonn�e maximale en y de tous les points de la figure
	 */
	public double maxY() {
		double max=faces.get(0).getPoints().get(0).getY();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(p.getY()>max)max=p.getY();
			}
		}return max;
	}
	
	/**
	 * Cette m�thode initialise le Timer pour la rotation
	 */
	public void timerRotation() {
    	Faces f = this;
	    TimerTask repeatedTask = new TimerTask() {
	        public void run() {
	        	Rotate r = new Rotate();
	        	r.rotateY(f, -0.009);
	        	cancel();
	        }
	    };
	    timer = new Timer("Timer");

	    long delay = 100L;
	    long period = 10000L;
	    timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
	
	/**
	 * Cette m�thode remet le timer � la valeur null
	 */
	public void cancelTimer() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}
	
}
