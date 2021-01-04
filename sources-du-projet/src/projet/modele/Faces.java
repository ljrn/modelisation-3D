package projet.modele;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import projet.utils.Subject;

public class Faces extends Subject{
	private List<Face> faces = new ArrayList<Face>();
	 private Timer timer;
	 private boolean timerActive = false;
	 
	 
	public boolean isTimerActive() {
		return timerActive;
	}

	public void setTimerActive(boolean timerActive) {
		this.timerActive = timerActive;
	}

	public Vecteur getLumiere() {
		return lumiere;
	}

	public  void setLumiere(Vecteur lumiere) {
		Faces.lumiere = lumiere;
	}

	private static Vecteur lumiere=new Vecteur(1,1,-1);
	
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
	
	public void colorDiffuseFace() {
		System.out.println(faces.get(0).getNormal());
		for (Face face : faces) {
			Vecteur normaleFace=face.getNormal();
			lumiere.unitaire();
			double scalaire=normaleFace.getX()*lumiere.getX()+normaleFace.getY()*lumiere.getY()+normaleFace.getZ()*lumiere.getZ();
			face.setColor(Math.abs(scalaire));
		}
	}

	public List<Face> getFaces() {
		return faces;
	}

	public void trierFaces() {
		Collections.sort(faces);
	}
	
	public Point midPoint() {
		return new Point(this.maxX()/2, this.maxY()/2,0);
	}

	public void setFaces(List<Face> list) {
		this.faces = list;
	}
	
	public double maxX() {
		double max=faces.get(0).getPoints().get(0).getX();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(p.getX()>max)max=p.getX();
			}
		}return max;
	}
	
	public double maxY() {
		double max=faces.get(0).getPoints().get(0).getY();
		for(Face f:faces) {
			for(Point p:f.getPoints()) {
				if(p.getY()>max)max=p.getY();
			}
		}return max;
	}
	
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
	
	public void cancelTimer() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}
	
}
