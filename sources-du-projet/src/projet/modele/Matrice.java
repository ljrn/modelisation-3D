package projet.modele;

public class Matrice {
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
