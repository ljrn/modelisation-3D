package projet.modele;

/**
 * Implementation d'un vecteur pour les faces et la lumiere
 */
public class Vecteur {
	private double x;
	private double y;
	private double z;
	private static final double HOMOGENE=1;
	/**
	 * 
	 * @param x La coordonnée en x du vecteur
	 * @param y La coordonnée en y du vecteur
	 * @param z La coordonnée en z du vecteur
	 */
	public Vecteur(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * 
	 * @return la coordonnée en x du vecteur
	 */
	public double getX() {
		return x;
	}
	/**
	 * 
	 * @return la coordonnée en y du vecteur
	 */
	public double getY() {
		return y;
	}
	/**
	 * 
	 * @return la coordonnée en z du vecteur
	 */
	public double getZ() {
		return z;
	}
	/**
	 * @param x la valeur du x que l'on veut attribuer au vecteur
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @param x la valeur du y que l'on veut attribuer au vecteur
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * @param x la valeur du z que l'on veut attribuer au vecteur
	 */
	public void setZ(double z) {
		this.z = z;
	}
	/**
	 * Ajoute value a la valeur de x
	 * @param value la valeur que l'on veut ajouter au x du vecteur
	 */
	public void increaseX(double value) {
		this.x += value;
	}
	/**
	 * Ajoute value a la valeur de y
	 * @param value la valeur que l'on veut ajouter au y du vecteur
	 */
	public void increaseY(double value) {
		this.y += value;
	}
	/**
	 * Ajoute value a la valeur de z
	 * @param value la valeur que l'on veut ajouter au z du vecteur
	 */
	public void increaseZ(double value) {
		this.z += value;
	}
	/**
	 * Enleve value a la valeur de x
	 * @param value la valeur que l'on veut enlever au x du vecteur
	 */
	public void decreaseX(double value) {
		this.x -= value;
	}
	/**
	 * Enleve value a la valeur de y
	 * @param value la valeur que l'on veut enlever au y du vecteur
	 */
	public void decreaseY(double value) {
		this.y -= value;
	}
	/**
	 * Enleve value a la valeur de z
	 * @param value la valeur que l'on veut enlever au z du vecteur
	 */
	public void decreaseZ(double value) {
		this.z -= value;
	}
	/**
	 * Methode permettant de mettre le vecteur à la norme 1
	 */
	public void unitaire() {
		double norme=Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
		x=x/norme;
		y=y/norme;
		z=z/norme;
	}
	/**
	 * Retourne le vecteur sous forme de chaine de caracteres
	 */
	public String toString() {
		return "x ="+this.x+" y="+this.y+" z="+this.z;
	}
}
