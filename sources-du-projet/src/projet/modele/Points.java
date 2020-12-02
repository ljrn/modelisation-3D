package projet.modele;

import java.util.ArrayList;
import java.util.List;

public class Points {
	private List<Point> points = new ArrayList<Point>();
	private Point pointCentral;
	
	public void decompStringPoints(List<String> stringPoints, double x, double y) {
		for (String string : stringPoints) {
			String[] tab = string.split(" ");
			points.add(new Point(Double.parseDouble(tab[0]), Double.parseDouble(tab[1]),Double.parseDouble(tab[2])));
		}
		double toZoom;
		if(x>=y) {
			toZoom=x/((maxX()+minX()/2));
		}else toZoom=y/((maxY()+minY())/2);
		if(toZoom>1) {
			for(Point p:points) {
				p.setX(p.getX()*toZoom);
				p.setY(p.getY()*toZoom);
				p.setZ(p.getZ()*toZoom);
			}
		}
		if(containsNegativeX()) {
			double minX=-minX();
			for(Point p:points) {
				p.setX(p.getX()+minX+100);
			}
		}
		if(containsNegativeY()) {
			double minY=-minY();
			for(Point p:points) {
				p.setY(p.getY()+minY+50);
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
	
	public double midY() {
		return (maxY()+minY())/2;
	}
	
	public double midX() {
		return (maxX()+minX())/2;
	}
	
	public double midZ() {
		return (maxZ()+minZ())/2;
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
	
	public double minZ() {
		if(points.isEmpty())return 0;
		double min = points.get(0).getZ();
		for (int i = 1; i < points.size(); i++) {
			min = Double.min(min,points.get(i).getZ());
		}
		return min;
	}
	
	public double maxZ() {
		if(points.isEmpty())return 0;
		double max = points.get(0).getZ();
		for (int i = 1; i < points.size(); i++) {
			max = Double.max(max,points.get(i).getZ());
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
	public void initCentre() {
		this.pointCentral.setX(this.midX());
		this.pointCentral.setY(this.midY());
		this.pointCentral.setZ(this.midZ());
	}
}
