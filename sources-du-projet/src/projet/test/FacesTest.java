package projet.test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Point;
import projet.reader.Points;
import projet.reader.ReadFile;

class FacesTest {
	private static final double DELTA = 0.1;
	Faces fs = new Faces();
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
	public void testDecompFaces() {
		ReadFile rf=new ReadFile("./ressources/airplane.ply");
		try{
			rf.readHeader();
			Points ps=new Points();
			ps.decompStringPoints(rf.getPoints(),0,0);
			Faces f=new Faces();
			f.decompStringFaces(rf.getFaces(), ps);
			Face fa=f.getFaces().get(0);
			Point p0=ps.getPoints().get(0);
			Point p1=ps.getPoints().get(1);
			Point p2=ps.getPoints().get(2);
			assertEquals(p0, fa.getPoints().get(0));
			assertEquals(p1, fa.getPoints().get(1));
			assertEquals(p2, fa.getPoints().get(2));
		}catch(Exception e) {}
	}
	@Test
	public void test_Translation() {
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
		fs.translateFacesX(1);
		assertEquals(11.1, fs.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		assertEquals(12.1, fs.getFaces().get(0).getPoints().get(1).getX(), DELTA);
		assertEquals(13.1, fs.getFaces().get(0).getPoints().get(2).getX(), DELTA);
		assertEquals(13.1, fs.getFaces().get(1).getPoints().get(0).getX(), DELTA);
		
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
		fs.decrementFacesX(2);
		assertEquals(9.1, fs.getFaces().get(0).getPoints().get(0).getX(), DELTA);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getY(), DELTA);
		assertEquals(10.1, fs.getFaces().get(0).getPoints().get(0).getZ(), DELTA);
		
	}

}
