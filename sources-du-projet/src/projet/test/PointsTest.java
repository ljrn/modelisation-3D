package projet.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import projet.modele.Points;

class PointsTest {
	public List<String> listS;
	Points points;
	@Test
	void testDecompString() {
		listS = new ArrayList<>();
		String s1 = "10.123456 20.1234567 30.12345678";
		listS.add(s1);
		Points p = new Points();
		p.decompStringPoints(listS,0,0);
		assertEquals(10.123456,p.getPoints().get(0).getX() );
		assertEquals(20.1234567,p.getPoints().get(0).getY() );
		assertEquals(30.12345678,p.getPoints().get(0).getZ() );
	}
	@Test
	void testMinMaxXY() {
		listS = new ArrayList<>();
		String s1 = "50 20 30 40";
		String s2 = "50.1 42.5455 53.5353";
		String s3 = "50.123 30 42.3 23.4";
		String s4 = "65.2 30 42.3 23.4";
		listS.add(s1);
		listS.add(s2);
		listS.add(s3);
		listS.add(s4);
		points = new Points();
		points.decompStringPoints(listS,0,0);
		assertEquals(50, points.minX(),0.05);
		assertEquals(65.2, points.maxX(),0.05);
		assertEquals(20, points.minY(),0.05);
		assertEquals(42.5455, points.maxY(), 0.05);
	}
}
