package projet.view;

import projet.reader.Faces;

public class Translate {
	final private double NBPXL = 0.5; 
	
	public void translateX(FormDisplay fd,Faces f,double nb) {
		f.translateFacesX(nb);
	}
	
	public void translateY(FormDisplay fd,Faces f,double nb) {
		f.translateFacesY(nb);
	}
}
