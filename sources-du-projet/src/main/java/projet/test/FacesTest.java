package main.java.projet.test;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import main.java.projet.reader.Face;
import main.java.projet.reader.Faces;
import main.java.projet.reader.Point;
import main.java.projet.reader.Points;
import main.java.projet.reader.ReadFile;

class FacesTest {
	@Test
	public void test() {
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
	 
}
