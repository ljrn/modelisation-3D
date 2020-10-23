package projet.view;

import projet.reader.Faces;

public class Translate {
	public void plusX(FormDisplay fd,Faces f) {
		f.incrementFacesX();
	}
	public void moinsX(FormDisplay fd, Faces f) {
		f.decrementFacesX();
	}
	public void plusY(FormDisplay fd,Faces f) {
		f.incrementFacesY();
	}
	public void moinsY(FormDisplay fd, Faces f) {
		f.decrementFacesY();
	}
}
