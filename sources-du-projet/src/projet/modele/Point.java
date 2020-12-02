package projet.modele;

import projet.utils.Matrice;

public class Point{

    double x;
    double y;
    double z;
    final double HOMOGENE=1;
    
    public Point(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public String toString() {
    	return ""+this.x+";"+this.y+";"+this.z;
    }
    
    public boolean containsNegativeX() {
    	return this.x<0;
    }
    
    public boolean containsNegativeY() {
    	return this.y<0;
    }

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}
	public void multiplyMatrice(Matrice m) {
		double[][]matrice =m.getMatrice();
		x=(x*matrice[0][0])+(y*matrice[0][1])+(z*matrice[0][2])+matrice[0][3];
		y=(x*matrice[1][0])+(y*matrice[1][1])+(z*matrice[1][2])+matrice[1][3];
		z=(x*matrice[2][0])+(y*matrice[2][1])+(z*matrice[2][2])+matrice[2][3];
	}
}