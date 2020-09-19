package reader;

import java.util.List;

public class Face {
	private int nbPoint;
	private List<Integer> points;
	
	public Face(int nb, List<Integer> points) {
		this.nbPoint = nb;
		this.points = points;
	}

	public int getNbPoint() {
		return nbPoint;
	}

	public List<Integer> getPoints() {
		return points;
	}
	 
	
}
