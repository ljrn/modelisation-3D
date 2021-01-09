package projet.modele;

/**
 * Implementation d'une Matrice et des operations pouvant etre realisees
 */
public class Matrice {
	/**
	 * Cette méthode multiplie deux matrice pour une translation
	 * @param m1 Matrice 1
	 * @param m2 Matrice 2
	 * @return double[][] étant l'équivalent d'une matrice
	 */
	public double[][] multiplicationTranslation(double[][] m1, double [][] m2){
		double [][] m = new double [m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				double x=0;
				for (int k = 0; k < m1[0].length; k++) {
					x+=m1[i][k]*m2[j][k];
				}
				m[i][j]=x;
			}
		}
		return m;
	}
	/**
	 * Cette méthode multiplie deux matrice pour une rotation
	 * @param m1 Matrice 1
	 * @param m2 Matrice 2
	 * @return double[][] étant l'équivalent d'une matrice
	 */
	public double[][] multiplicationRotation(double[][] m1, double [][] m2){
		double [][] m = new double [m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				double x=0;
				for (int k = 0; k < m1[0].length; k++) {
					x+=m1[i][k]*m2[k][j];
				}
				m[i][j]=x;
			}
		}
		return m;
	}

	/**
	 * @param matriceFin Matrice à afficher
	 * @return String contenant les valeurs de la matrice
	 */
	public String toString(double[][] matriceFin) {
		String res="";
		for (int i = 0; i < matriceFin.length; i++) {
			res+="{";
			for (int j = 0; j < matriceFin[0].length; j++) {
				res+=matriceFin[i][j];
				if(j!=matriceFin[0].length-1)res+=",";
			}
			res+="}\n";
		}
		return res;
	}
}
