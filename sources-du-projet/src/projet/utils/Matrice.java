package projet.utils;

public class Matrice {
	public static final int TAILLE=4;
	private double [][] matrice=new double[4][4];
	public Matrice(double[][]matrice) {
		this.matrice=matrice;
	}
	public double[][] getMatrice(){
		return this.matrice;
	}
}
