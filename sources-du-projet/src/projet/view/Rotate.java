package projet.view;

import projet.reader.Faces;

public class Rotate {
	final private double NBPXL = 0.1; 

	public void rotateX(FormDisplay fd,Faces f,double nb) {
		f.rotateFacesX(nb);
	}
	
	public void rotateY(FormDisplay fd,Faces f,double nb) {
		f.rotateFacesY(nb);
	}
	public void rotateZ(FormDisplay fd, Faces f,double nb) {
		f.rotateFacesZ(nb);

	}
}
