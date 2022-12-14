package projet.test;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.modele.Face;
import projet.modele.Faces;
import projet.modele.Point;
import projet.modele.Points;
import projet.modele.Rotation;

public class RotationTest {
	private static final double DELTA = 0.1;
	Faces faces = new Faces();
	Rotation r = Rotation.getInstance();
	@BeforeEach
	public void creation_Faces(){
		ArrayList<Point> listPs = new ArrayList<>();
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
		ArrayList<Face> listFs = new ArrayList<>();	
		ArrayList<Point> face1 = new ArrayList<>();
		face1.add(ps.getPoints().get(0));
		face1.add(ps.getPoints().get(1));
		face1.add(ps.getPoints().get(2));
		listFs.add(new Face(3,face1));
		ArrayList<Point> face2 = new ArrayList<>();
		face2.add(ps.getPoints().get(2));
		face2.add(ps.getPoints().get(3));
		face2.add(ps.getPoints().get(4));
		listFs.add(new Face(3,face2));
		ArrayList<Point> face3 = new ArrayList<>();
		face3.add(ps.getPoints().get(5));
		face3.add(ps.getPoints().get(6));
		face3.add(ps.getPoints().get(7));
		listFs.add(new Face(3,face3));
		faces.setFaces(listFs);
	}
	@Test
	public void test_RotationX() {
		r.rotateX(faces, 0.5);
		assertEquals(13.7, faces.getFaces().get(0).getPoints().get(0).getY(), 0.01);
		assertEquals(4.02, faces.getFaces().get(0).getPoints().get(0).getZ(), 0.01);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		r.rotateX(faces, -0.5);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getY(), 0.01);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getZ(), 0.01);
	}
	@Test
	public void test_RotationY() {
		r.rotateY(faces, 0.5);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(12.1, faces.getFaces().get(1).getPoints().get(0).getY(), DELTA);
		assertEquals(17.1, faces.getFaces().get(2).getPoints().get(2).getY(), DELTA);
		assertEquals(4.02, faces.getFaces().get(0).getPoints().get(0).getZ(), 0.01);
		assertEquals(13.70, faces.getFaces().get(0).getPoints().get(0).getX(), 0.01);
	}
	@Test
	public void test_RotationZ() {
		r.rotateZ(faces, 0.5);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
		assertEquals(13.70, faces.getFaces().get(0).getPoints().get(0).getX(), 0.01);
		assertEquals(4.02, faces.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(16.41, faces.getFaces().get(1).getPoints().get(0).getX(), 0.01);
	}
}
