package projet.view;

import projet.reader.Faces;

public class Zoom {

final private double NBPXL = 1.5000000; 
	
	public void Zoom(Faces f,double factor) {
		f.zoomfaces(factor);
	}
	
}
