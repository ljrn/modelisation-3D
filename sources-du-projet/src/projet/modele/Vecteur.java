package projet.modele;

public class Vecteur {
	private double x;
	private double y;
	private double z;
	private static final double HOMOGENE=1;
	public Vecteur(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public void increaseX(double value) {
		this.x += value;
	}
	public void increaseY(double value) {
		this.y += value;
	}
	public void increaseZ(double value) {
		this.z += value;
	}
	
	public void decreaseX(double value) {
		this.x -= value;
	}
	public void decreaseY(double value) {
		this.y -= value;
	}
	public void decreaseZ(double value) {
		this.z -= value;
	}
	public void exterieur() {
		this.x=-x;
		this.y=-y;
		this.z=-z;
	}
	public void unitaire() {
		double norme=Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
		x=x/norme;
		y=y/norme;
		z=z/norme;
	}
	public String toString() {
		return "x ="+this.x+" y="+this.y+" z="+this.z;
	}
}
