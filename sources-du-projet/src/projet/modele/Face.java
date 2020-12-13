package projet.modele;
import java.util.ArrayList;
import java.util.List;
public class Face implements Comparable<Face>{
	private int nbPoint;
	private List<Point> points=new ArrayList<Point>();
	private Vecteur normal;
	private int red;
	private int green;
	private int blue;
	public Face(int nb, List<Point> p) {
		this.nbPoint = nb;
		this.points=p;
		this.normal=this.normale(p);
		this.red = 120;
		this.green = 0;
		this.blue = 120;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public void setColor(double coeff) {
		this.red *= coeff;
		red=red%256;
		this.green *= coeff;
		green=green%256;
		this.blue *= coeff;
		blue=blue%256;
	}
	public int getNbPoint() {
		return nbPoint;
	}

	public List<Point> getPoints() {
		return points;
	}

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

	public Vecteur normale(List<Point> p) {
		Vecteur ab=this.getAB(p);
		Vecteur ac=this.getAC(p);
		double x=ab.getY()*ac.getZ()-ab.getZ()*ac.getY();
		double y=ab.getZ()*ac.getX()-ab.getX()*ac.getZ();
		double z=ab.getX()*ac.getY()-ab.getY()*ac.getX();
		return new Vecteur(x,y,z);
	}
}