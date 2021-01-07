package projet.modele;

import java.util.ArrayList;
import java.util.List;

public class Points {
	private List<Point> points = new ArrayList<Point>();
	private Point pointCentral;
	
	/**
	 * Cette méthode permet d'interpréter les points lus dans le fichier et de les centrer par rapport à la fenêtre
	 * @param stringPoints List de String contenant lues dans le fichier .ply et décrivant les points
	 * @param hauteur Hauteur du modèle
	 * @param largeur Largeur du modèle
	 */
	public void decompStringPoints(List<String> stringPoints, double hauteur, double largeur) {
		for (String string : stringPoints) {
			String[] tab = string.split(" ");
			points.add(new Point(Double.parseDouble(tab[0]), Double.parseDouble(tab[1]),Double.parseDouble(tab[2])));
		}
		double toZoom;
		if(hauteur>=largeur) {
			toZoom=hauteur/((maxX()+minX()/2));
		}else toZoom=largeur/((maxY()+minY())/2);
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
	/**
	 * Cette méthode retourne la liste de points
	 * @return Liste des points lus dans le fichier .ply
	 */
	public List<Point> getPoints() {
		return points;
	}

	/**
	 * Met les valeurs de l'attribut de classe points à celles de la liste passée en paramètre 
	 * @param points Liste de points
	 */
	public void setPoints(List<Point> points) {
		this.points = points;
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		String res="";
		for(Point p: this.points) {
			System.out.println(p.toString()+"\n");
		}
		return res;
	}
	
	/**
	 * Cette méthode permet de trouver le point au centre du modèle en Y
	 * @return le point du centre du modèle en Y
	 */
	public double midY() {
		return (maxY()+minY())/2;
	}
	
	/**
	 * Cette méthode permet de trouver le point au centre du modèle en X
	 * @return le point du centre du modèle en X
	 */
	public double midX() {
		return (maxX()+minX())/2;
	}
	
	/**
	 * Cette méthode permet de trouver le point au centre du modèle en Z
	 * @return le point du centre du modèle en Z
	 */
	public double midZ() {
		return (maxZ()+minZ())/2;
	}
	
	/**
	 * Cette méthode permet de trouver le point à la plus petite coordonnée du modèle en X
	 * @return le point avec la plus petite coordonnée du modèle en X
	 */
	public double minX() {
		if(points.isEmpty())return 0;
		double min = points.get(0).getX();
		for (int i = 1; i < points.size(); i++) {
			min = Double.min(min,points.get(i).getX());
		}
		return min;
	}
	
	/**
	 * Cette méthode permet de trouver le point à la plus grande coordonnée du modèle en X
	 * @return le point avec la plus grande coordonnée du modèle en X
	 */
	public double maxX() {
		if(points.isEmpty())return 0;
		double max = points.get(0).getX();
		for (int i = 1; i < points.size(); i++) {
			max = Double.max(max,points.get(i).getX());
		}
		return max;
	}
	
	/**
	 * Cette méthode permet de trouver le point à la plus petite coordonnée du modèle en Y
	 * @return le point avec la plus petite coordonnée du modèle en Y
	 */
	public double minY() {
		if(points.isEmpty())return 0;
		double min = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) {
			min = Double.min(min,points.get(i).getY());
		}
		return min;
	}
	
	/**
	 * Cette méthode permet de trouver le point à la plus grande coordonnée du modèle en Y
	 * @return le point avec la plus grande coordonnée du modèle en y
	 */
	public double maxY() {
		if(points.isEmpty())return 0;
		double max = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) {
			max = Double.max(max,points.get(i).getY());
		}
		return max;
	}
	
	/**
	 * Cette méthode permet de trouver le point à la plus petite coordonnée du modèle en Z
	 * @return le point avec la plus petite coordonnée du modèle en Z
	 */
	public double minZ() {
		if(points.isEmpty())return 0;
		double min = points.get(0).getZ();
		for (int i = 1; i < points.size(); i++) {
			min = Double.min(min,points.get(i).getZ());
		}
		return min;
	}
	
	/**
	 * Cette méthode permet de trouver le point à la plus grande coordonnée du modèle en X
	 * @return le point avec la plus grande coordonnée du modèle en X
	 */
	public double maxZ() {
		if(points.isEmpty())return 0;
		double max = points.get(0).getZ();
		for (int i = 1; i < points.size(); i++) {
			max = Double.max(max,points.get(i).getZ());
		}
		return max;
	}
	
	/**
	 *Cette méthode permet de dire si un point contient une coordonnée négative en X 
	 * @return true si le point a un x négatif, false sinon
	 */
	public boolean containsNegativeX() {
		for(Point p: this.points) {
			if(p.containsNegativeX())return true;
		}
		return false;
	}
	/**
	 *Cette méthode permet de dire si un point contient une coordonnée négative en Xy
	 * @return true si le point a un y négatif, false sinon
	 */
	public boolean containsNegativeY() {
		for(Point p: this.points) {
			if(p.containsNegativeY())return true;
		}
		return false;
	}
	/**
	 * Cette méthode initialise l'attribut de classe pointCentral
	 */
	public void initCentre() {
		this.pointCentral.setX(this.midX());
		this.pointCentral.setY(this.midY());
		this.pointCentral.setZ(this.midZ());
	}
}
