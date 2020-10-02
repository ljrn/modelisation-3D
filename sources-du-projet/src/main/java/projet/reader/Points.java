package projet.reader;

import java.util.ArrayList;
	
import java.util.List;

public class Points {
	private List<Point> points = new ArrayList<Point>();
	
	public void decompStringPoints(List<String> stringPoints) {
		for (String string : stringPoints) {
			String[] tab = string.split(" ");
			points.add(new Point(Float.parseFloat(tab[0]+"F"), Float.parseFloat(tab[1]+"F"),Float.parseFloat(tab[2]+"F")));
		}
	}
 
	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	@Override
	public String toString() {
		String res="";
		for(Point p: this.points) {
			System.out.println(p.toString()+"\n");
		}
		return res;
	}public double midX() {
		int res = 0;
		for (Point point : points) {
			res += (int) point.getX();
		}
		return res /= points.size();
	}
	public double midY() {
		int res = 0;
		for (Point point : points) {
			res += (int) point.getY();
		}
		return res /= points.size();
	}
	
	public double minX() {
		if(points.isEmpty())return 0;
		double min = points.get(0).getX();
		for (int i = 1; i < points.size(); i++) {
			min = Double.min(min,points.get(i).getX());
		}
		return min;
	}
	public double maxX() {
		if(points.isEmpty())return 0;
		double max = points.get(0).getX();
		for (int i = 1; i < points.size(); i++) {
			max = Double.max(max,points.get(i).getX());
		}
		return max;
	}
	public double minY() {
		if(points.isEmpty())return 0;
		double min = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) {
			min = Double.min(min,points.get(i).getY());
		}
		return min;
	}
	public double maxY() {
		if(points.isEmpty())return 0;
		double max = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) {
			max = Double.max(max,points.get(i).getY());
		}
		return max;
	}
	
	public void center() {
		for(Point p:this.points) {
			p.center();
		}
	}
	
	
	
}
