package projet.view;

import projet.reader.Faces;

public class Translate {
	final private double NBPXL = 5.5000000; 
	
	public void plusX(FormDisplay fd,Faces f) {
		f.incrementFacesX(NBPXL);
	}
	public void moinsX(FormDisplay fd, Faces f) {
		f.decrementFacesX(NBPXL);
	}
	public void plusY(FormDisplay fd,Faces f) {
		f.incrementFacesY(NBPXL);
	}
	public void moinsY(FormDisplay fd, Faces f) {
		f.decrementFacesY(NBPXL);
	}
}
