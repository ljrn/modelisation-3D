package projet.test;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.modele.Face;
import projet.modele.Faces;
import projet.modele.Point;
import projet.modele.Points;
import projet.modele.Translation;

public class TranslateTest {
	private static final double DELTA = 0.1;
	Faces faces = new Faces();
	Translation t =Translation.getInstance();
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
	public void test_TranslationX() {
		t.translate(faces, 1,0,0);
		assertEquals(11.1, faces.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		assertEquals(12.1, faces.getFaces().get(0).getPoints().get(1).getX(), DELTA);
		assertEquals(13.1, faces.getFaces().get(0).getPoints().get(2).getX(), DELTA);
		assertEquals(13.1, faces.getFaces().get(1).getPoints().get(0).getX(), DELTA);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
		t.translate(faces, -2,0,0);
		assertEquals(9.1, faces.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
	}
	@Test
	public void test_TranslationY() {
		t.translate(faces, 0,2.4,0);
		assertEquals(12.5, faces.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(14.5, faces.getFaces().get(1).getPoints().get(0).getY(), DELTA);
		assertEquals(19.5, faces.getFaces().get(2).getPoints().get(2).getY(), DELTA);
		t.translate(faces, 0,-2.4,0);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(12.1, faces.getFaces().get(1).getPoints().get(0).getY(), DELTA);
		assertEquals(17.1, faces.getFaces().get(2).getPoints().get(2).getY(), DELTA);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		assertEquals(10.1, faces.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
	}
}
