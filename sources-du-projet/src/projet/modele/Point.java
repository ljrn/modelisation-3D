package projet.modele;

public class Point{

    double x;
    double y;
    double z;
    final double HOMOGENE=1;
    /**
     * @param x Coordonn�e x du point
     * @param y Coordonn�e y du point
     * @param z Coordonn�e z du point
     */
    public Point(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    /**
     * @return La valeur de l'attribut de classe x
     */
    public double getX() {
        return x;
    }
    /**
     * @return La valeur de l'attribut de classe y
     */
    public double getY() {
        return y;
    }
    /**
     * @return La valeur de l'attribut de classe z
     */
    public double getZ() {
        return z;
    }
    
    /**
     * @return Les valeurs d'un point sous forme de String
     */
    public String toString() {
    	return ""+this.x+";"+this.y+";"+this.z;
    }
    
    /**
     * @return true si x est n�gatif
     */
    public boolean containsNegativeX() {
    	return this.x<0;
    }
    /**
     * @return true si y est n�gatif
     */
    public boolean containsNegativeY() {
    	return this.y<0;
    }
    /**
     *Assigne la valeur du param�tre � x
     * @param x coordonn�e x
     */
	public void setX(double x) {
		this.x = x;
	}
	/**
     *Assigne la valeur du param�tre � y
     * @param y coordonn�e y
     */
	public void setY(double y) {
		this.y = y;
	}
	/**
     *Assigne la valeur du param�tre � z
     * @param z coordonn�e z
     */
	public void setZ(double z) {
		this.z = z;
	}
	/**
	 * Met un point sous forme d'une matrice
	 * @return double[1][4] avec les coordonn�es du point
	 */
	public double[][] toMatrice(){
		double[][] matrice= {{x,y,z,HOMOGENE}};
		return matrice;
	}
	/**
	 * Assigne les valeurs d'une Matrice repr�sentant un point au Point courant
	 * @param point Matrice repr�sentant un point
	 */
	public void assign(double[][] point) {
		x=point[0][0];
		y=point[0][1];
		z=point[0][2];
	}
}