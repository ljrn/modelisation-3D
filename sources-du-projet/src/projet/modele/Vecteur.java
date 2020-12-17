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
	public void unitaire() {
		double norme=Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
		x=x/norme;
		y=y/norme;
		z=z/norme;
	}
}
