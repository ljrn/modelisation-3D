package projet.modele;
/**
 *Classe permettant d'initialiser et de gerer la lumiere et l'eclairage du modele
 */
public class Lumiere {
	/**
	 * Methodes assignées aux boutons rotateLumiereDroite et Gauche dans la classe MonListChangeListener
	 * @param faces Faces à éclairer
	 * @param coeffLumiere coefficient de lumiere
	 */
	public void rotateHorizontal(Faces faces, double coeffLumiere) {
		Vecteur lum = faces.getLumiere();
		if(lum.getZ() < 1 && lum.getZ() > 0 && lum.getX() < 1 && lum.getX() > 0) {
			lum.increaseX(coeffLumiere);
			lum.decreaseZ(coeffLumiere);
		}
		else if(lum.getZ() <= 0 && lum.getZ() > -1 && lum.getX() < 1 && lum.getX() > 0) {
			lum.decreaseZ(coeffLumiere);
			lum.decreaseX(coeffLumiere);
		}
		else if(lum.getZ() <= 0 && lum.getX() <= 0) {
			lum.increaseZ(coeffLumiere);
			lum.decreaseX(coeffLumiere);
		}
		else if(lum.getZ() < 1 && lum.getZ() > 0 && lum.getX() <= 0) {
			lum.increaseX(coeffLumiere);
			lum.increaseZ(coeffLumiere);
		}
		faces.setLumiere(lum);
		faces.notifyObservers(faces);
	}
	/**
	 * Methodes assignées aux boutons rotateLumiereHaut et bas dans la classe MonListChangeListener
	 *@param faces Faces à éclairer
	 *@param coeffLumiere coefficient de lumiere
	 */
	public void rotateVertical(Faces faces, double coeffLumiere) {
		Vecteur lum = faces.getLumiere();
		if(lum.getY() < 1 && lum.getY() > 0 && lum.getZ() < 1 && lum.getZ() > 0) {
			lum.increaseZ(coeffLumiere);
			lum.decreaseY(coeffLumiere);
		}
		else if(lum.getY() <= 0 && lum.getY() > -1 && lum.getZ() < 1 && lum.getZ() > 0) {
			lum.decreaseY(coeffLumiere);
			lum.decreaseZ(coeffLumiere);
		}
		else if(lum.getY() <= 0 && lum.getZ() <= 0) {
			lum.increaseY(coeffLumiere);
			lum.decreaseZ(coeffLumiere);
		}
		else if(lum.getY() < 1 && lum.getY() > 0 && lum.getZ() <= 0) {
			lum.increaseZ(coeffLumiere);
			lum.increaseY(coeffLumiere);
		}
		faces.setLumiere(lum);
		faces.notifyObservers(faces);
	}
}
