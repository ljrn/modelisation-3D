package projet.reader;

import java.util.ArrayList;
import java.util.List;

public class Points {
	private List<Point> points = new ArrayList<Point>();
	
	public void decompStringPoints(List<String> stringPoints, double x, double y) {
		for (String string : stringPoints) {
			String[] tab = string.split(" ");
			points.add(new Point(Double.parseDouble(tab[0]), Double.parseDouble(tab[1]),Double.parseDouble(tab[2])));
		}
		Double xToZoom=x-midX();
		if(xToZoom>1) {
			for(Point p:points) {
				p.setX(p.getX()*xToZoom);
				p.setY(p.getY()*xToZoom);
				p.setZ(p.getZ()*xToZoom);
			}
		}
		if(containsNegativeX()) {
			double minX=-minX();
			for(Point p:points) {
				p.setX(p.getX()+minX);
			}
		}
		if(containsNegativeY()) {
			double minY=-minY();
			for(Point p:points) {
				p.setY(p.getY()+minY);
			}
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
	}
	
	public double midX() {
		return (maxX()+minX())/2;
	}
	
	
	public double midY() {
		return (maxY()+minY())/2;
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
	
	public boolean containsNegativeX() {
		for(Point p: this.points) {
			if(p.containsNegativeX())return true;
		}
		return false;
	}
	
	public boolean containsNegativeY() {
		for(Point p: this.points) {
			if(p.containsNegativeY())return true;
		}
		return false;
	}
}
