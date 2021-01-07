package projet.modele;
import java.util.ArrayList;
import java.util.List;
public class Face implements Comparable<Face>{
	private int nbPoint;
	private List<Point> points=new ArrayList<Point>();
	private Vecteur normal;
	private final int baseRed;
	private final int baseGreen;
	private final int baseBlue;
	private int red;
	private int green;
	private int blue;
	/**
	 * Constructeur de la classe Face
	 * @param nb Nombre de points de la Face
	 * @param p Liste des points de la Face
	 */
	public Face(int nb, List<Point> p) {
		this.nbPoint = nb;
		this.points=p;
		this.normale(p);
		this.baseRed = this.red = 10;
		this.baseGreen = this.green = 10;
		this.baseBlue = this.blue = 255;
		
	}
	/**
	 * Donne la composante rouge de la face
	 * @return Un entier de 0 à 255 désignant la teinte de rouge de la face
	 */
	public int getRed() {
		return red;
	}
	/**
	 * Initialise la composante rouge de la face
	 * @param red Un entier de 0 à 255 désignant la teinte de rouge à donner à la face
	 */
	public void setRed(int red) {
		this.red = red;
	}
	/**
	 * Donne la composante verte de la face
	 * @return Un entier de 0 à 255 désignant la teinte de verte de la face
	 */
	public int getGreen() {
		return green;
	}
	/**
	 * Initialise la composante rouge de la face
	 * @param red Un entier de 0 à 255 désignant la teinte de vert à donner à la face
	 */
	public void setGreen(int green) {
		this.green = green;
	}
	/**
	 * Donne la composante bleue de la face
	 * @return Un entier de 0 à 255 désignant la teinte de bleue de la face
	 */
	public int getBlue() {
		return blue;
	}
	/**
	 * Initialise la composante rouge de la face
	 * @param red Un entier de 0 à 255 désignant la teinte de bleu à donner à la face
	 */
	public void setBlue(int blue) {
		this.blue = blue;
	}
	/**
	 * Initialise la couleur de la face en appliquant le coefficient
	 * @param coeff Coefficient d'éclairage de la face
	 */
	public void setColor(double coeff) {
		this.red = (int) (baseRed * coeff);
		red=red%256;
		this.green = (int) (baseGreen * coeff);
		green=green%256;
		this.blue = (int) (baseBlue * coeff);
		blue=blue%256;
	}
	/**
	 * @return Le nombre de points de la face
	 */
	public int getNbPoint() {
		return nbPoint;
	}
	/**
	 * @return La liste des points de la face
	 */
	public List<Point> getPoints() {
		return points;
	}
	/**
	 */
	@Override
	public String toString() {
		String res = "Nb Points: "+nbPoint+"\n";
		for(Point p: points) {
			res+=p.toString()+"\n";
		}
		return res;
	}
	
	public double[] getPointsX() {
		double[] pointsX=new double[nbPoint];
		for(int i=0; i<points.size();i++) {
			pointsX[i]=points.get(i).getX();
		}
		return pointsX;
	}
	
	public double[] getPointsY() {
		double[] pointsY=new double[nbPoint];
		for(int i=0; i<points.size();i++) {
			pointsY[i]=points.get(i).getY();
		}
		return pointsY;
	}
	
	public double[] getPointsZ() {
		double[] pointsZ=new double[nbPoint];
		for(int i=0; i<points.size();i++) {
			pointsZ[i]=points.get(i).getZ()-600;
		}
		return pointsZ;
	}

	@Override
	public int compareTo(Face face) {
		int sommeO=0;
		int sommeThis=0;
		for(Point point:face.getPoints()) {
			sommeO+=point.getZ();
		}
		for(Point point:this.getPoints()) {
			sommeThis+=point.getZ();
		}
		return sommeThis-sommeO;
	}
	
	public Vecteur getAB(List<Point> p) {
		double x=p.get(1).getX()-p.get(0).getX();
		double y=p.get(1).getY()-p.get(0).getY();
		double z=p.get(1).getZ()-p.get(0).getZ();
		return new Vecteur(x,y,z);
	}
	public Vecteur getAC(List<Point> p){
		double x=p.get(2).getX()-p.get(0).getX();
		double y=p.get(2).getY()-p.get(0).getY();
		double z=p.get(2).getZ()-p.get(0).getZ();
		return new Vecteur(x,y,z);
	}
	
	public Vecteur getNormal() {
		return normal;
	}

	public void normale(List<Point> p) {
		Vecteur ab=this.getAB(p);
		Vecteur ac=this.getAC(p);
		double x=ab.getY()*ac.getZ()-ab.getZ()*ac.getY();
		double y=ab.getZ()*ac.getX()-ab.getX()*ac.getZ();
		double z=ab.getX()*ac.getY()-ab.getY()*ac.getX();
		Vecteur n=new Vecteur(x,y,z);
		n.unitaire();
		normal=n;
	}
}