package main.java.projet.reader;

public class Point {

    private float x;
    private float y;
    private float z;
    
    public Point(float x, float y, float z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
    
    public String toString() {
    	return ""+this.x+";"+this.y+";"+this.z;
    }
    
    public void center() {
    	this.x=this.x-x+600;
    	this.y=this.y-y+600;
    }
    
}