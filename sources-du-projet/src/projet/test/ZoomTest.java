package projet.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Point;
import projet.reader.Points;
import projet.reader.Zoom;

public class ZoomTest {
	private static final double DELTA = 0.1;
	Faces fs = new Faces();
	Zoom z = new Zoom();
	@BeforeEach
	public void creation_Faces(){
		ArrayList<Point> listPs = new ArrayList<Point>();
		listPs.add(new Point(10.1, 10.1, 10.1));
		listPs.add(new Point(11.1, 11.1, 11.1));
		listPs.add(new Point(12.1, 12.1, 12.1));
		listPs.add(new Point(13.1, 13.1, 13.1));
		listPs.add(new Point(14.1, 14.1, 14.1));
		listPs.add(new Point(15.1, 15.1, 15.1));
		listPs.add(new Point(16.1, 16.1, 16.1));
		listPs.add(new Point(17.1, 17.1, 17.1));

		Points ps = new Points();
		ps.setPoints(listPs);
		ArrayList<Face> listFs = new ArrayList<Face>();	
		ArrayList<Point> face1 = new ArrayList<Point>();
		face1.add(ps.getPoints().get(0));
		face1.add(ps.getPoints().get(1));
		face1.add(ps.getPoints().get(2));
		listFs.add(new Face(3,face1));
		ArrayList<Point> face2 = new ArrayList<Point>();
		face2.add(ps.getPoints().get(2));
		face2.add(ps.getPoints().get(3));
		face2.add(ps.getPoints().get(4));
		listFs.add(new Face(3,face2));
		ArrayList<Point> face3 = new ArrayList<Point>();
		face3.add(ps.getPoints().get(5));
		face3.add(ps.getPoints().get(6));
		face3.add(ps.getPoints().get(7));
		listFs.add(new Face(3,face3));
		fs.setFaces(listFs);
	}
	
	@Test
    public void test_Zoom() {
        assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getX(), DELTA);
        assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getY(), DELTA);
        assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
        z.zoom(fs, 1.5);
        assertEquals(10.87, fs.getFaces().get(0).getPoints().get(0).getX(), DELTA);
        assertEquals(10.87, fs.getFaces().get(0).getPoints().get(0).getY(), DELTA);
        assertEquals(15.15, fs.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
        z.zoom(fs, 0.5);
        assertEquals(10.78, fs.getFaces().get(0).getPoints().get(0).getX(), DELTA);
        assertEquals(10.78, fs.getFaces().get(0).getPoints().get(0).getY(), DELTA);
        assertEquals(7.575, fs.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
    }
}
