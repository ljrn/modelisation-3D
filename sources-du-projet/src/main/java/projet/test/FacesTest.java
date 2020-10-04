<<<<<<< HEAD
package main.java.projet.test;
=======
package projet.test;

>>>>>>> branch 'master' of https://gitlab.univ-lille.fr/2020-s3-projet-mode/projetmode-j6.git
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import projet.reader.Face;
import projet.reader.Faces;
import projet.reader.Point;
import projet.reader.Points;
import projet.reader.ReadFile;

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
