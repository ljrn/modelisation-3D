package reader;

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
	
	
}
