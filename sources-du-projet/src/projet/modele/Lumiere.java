package projet.modele;

public class Lumiere {

	public void rotateHorizontal(Faces f, double nb) {
		Vecteur lum = f.getLumiere();
		if(lum.getZ() < 1 && lum.getZ() > 0 && lum.getX() < 1 && lum.getX() > 0) {
			lum.increaseX(nb);
			lum.decreaseZ(nb);
		}
		else if(lum.getZ() <= 0 && lum.getZ() > -1 && lum.getX() < 1 && lum.getX() > 0) {
			lum.decreaseZ(nb);
			lum.decreaseX(nb);
		}
		else if(lum.getZ() <= 0 && lum.getX() <= 0) {
			lum.increaseZ(nb);
			lum.decreaseX(nb);
		}
		else if(lum.getZ() < 1 && lum.getZ() > 0 && lum.getX() <= 0) {
			lum.increaseX(nb);
			lum.increaseZ(nb);
		}
		f.setLumiere(lum);
		f.notifyObservers(f);
	}
	
	public void rotateVertical(Faces f, double nb) {
		Vecteur lum = f.getLumiere();
		if(lum.getY() < 1 && lum.getY() > 0 && lum.getZ() < 1 && lum.getZ() > 0) {
			lum.increaseZ(nb);
			lum.decreaseY(nb);
		}
		else if(lum.getY() <= 0 && lum.getY() > -1 && lum.getZ() < 1 && lum.getZ() > 0) {
			lum.decreaseY(nb);
			lum.decreaseZ(nb);
		}
		else if(lum.getY() <= 0 && lum.getZ() <= 0) {
			lum.increaseY(nb);
			lum.decreaseZ(nb);
		}
		else if(lum.getY() < 1 && lum.getY() > 0 && lum.getZ() <= 0) {
			lum.increaseZ(nb);
			lum.increaseY(nb);
		}
		f.setLumiere(lum);
		f.notifyObservers(f);
	}
}
