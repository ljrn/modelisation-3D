package projet.reader;

public class Point{

    private double x;
    private double y;
    private double z;
    
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
}