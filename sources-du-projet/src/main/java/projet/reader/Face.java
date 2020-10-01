package projet.reader;

import java.util.ArrayList;
import java.util.List;

public class Face {
	private int nbPoint;
	private List<Point> ps=new ArrayList<Point>();
	
	public Face(int nb, List<Point> p) {
		this.nbPoint = nb;
		this.ps=p;
	}

	public int getNbPoint() {
		return nbPoint;
	}

	public List<Point> getPoints() {
		return ps;
	}

	@Override
	public String toString() {
		String res = "Nb Points: "+nbPoint+"\n";
		for(Point p: ps) {
			res+=p.toString()+"\n";
		}
		return res;
	}
	
	public double[] getPointsX() {
		double[] pointsX=new double[nbPoint];
		for(int i=0; i<ps.size();i++) {
			pointsX[i]=ps.get(i).getX();
		}
		return pointsX;
	}
	
	public double[] getPointsY() {
		double[] pointsY=new double[nbPoint];
		for(int i=0; i<ps.size();i++) {
			pointsY[i]=ps.get(i).getY();
		}
		return pointsY;
	}
	
	public double[] getPointsZ() {
		double[] pointsZ=new double[nbPoint];
		for(int i=0; i<ps.size();i++) {
			pointsZ[i]=ps.get(i).getZ()-600;
		}
		return pointsZ;
	}
	
}
