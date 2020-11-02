package projet.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Point;
import projet.reader.Points;
import projet.reader.Rotate;

public class RotationTest {
	private static final double DELTA = 0.1;
	Faces fs = new Faces();
	Rotate r =new Rotate();
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
	public void test_RotationX() {
		r.rotateX(fs, 0.5);
		assertEquals(5.06, fs.getFaces().get(0).getPoints().get(0).getY(), 0.01);
		assertEquals(7.19, fs.getFaces().get(0).getPoints().get(0).getZ(), 0.01);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		r.rotateX(fs, -0.5);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		assertEquals(8.37, fs.getFaces().get(0).getPoints().get(0).getY(), 0.01);
		assertEquals(4.18, fs.getFaces().get(0).getPoints().get(0).getZ(), 0.01);
	}
	@Test
	public void test_RotationY() {
		r.rotateY(fs, 0.5);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(12.1, fs.getFaces().get(1).getPoints().get(0).getY(), DELTA);
		assertEquals(17.1, fs.getFaces().get(2).getPoints().get(2).getY(), DELTA);
		assertEquals(5.88, fs.getFaces().get(0).getPoints().get(0).getZ(), 0.01);
		assertEquals(14.75, fs.getFaces().get(0).getPoints().get(0).getX(), 0.01);
	}
	@Test
	public void test_RotationZ() {
		r.rotateZ(fs, 0.5);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
		assertEquals(9.16, fs.getFaces().get(0).getPoints().get(0).getX(), 0.01);
		assertEquals(10.2, fs.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(9.96, fs.getFaces().get(1).getPoints().get(0).getX(), 0.01);
	}
}
