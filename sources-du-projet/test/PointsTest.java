package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import reader.Points;

class PointsTest {

	//@Test
	/*void test() {
		fail("Not yet implemented");
	}*/

	@Test
	void testDecompString() {
		ArrayList<String> listS = new ArrayList<String>();
		String s1 = "10.123456 20.1234567 30.12345678";
		listS.add(s1);
		Points p = new Points();
		p.decompStringPoints(listS);
		assertEquals(10.123456F,p.getPoints().get(0).getX() );
		assertEquals(20.1234567F,p.getPoints().get(0).getY() );
		assertEquals(30.12345678F,p.getPoints().get(0).getZ() );
	}
}
