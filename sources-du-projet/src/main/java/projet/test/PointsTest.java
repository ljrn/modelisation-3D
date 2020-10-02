package projet.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import projet.reader.Points;

class PointsTest {
	public List<String> listS;
	Points ps;
	@Before
	void setUp() {
		
		
	}
	
	@Test
	void testDecompString() {
		listS = new ArrayList<String>();
		String s1 = "10.123456 20.1234567 30.12345678";
		listS.add(s1);
		Points p = new Points();
		p.decompStringPoints(listS);
		assertEquals(10.123456F,p.getPoints().get(0).getX() );
		assertEquals(20.1234567F,p.getPoints().get(0).getY() );
		assertEquals(30.12345678F,p.getPoints().get(0).getZ() );
	}
	@Test
	void testMidX() {
		listS = new ArrayList<String>();
		String s1 = "10 20 30";
		String s2 = "20 30 40";
		listS.add(s1);
		listS.add(s2);
		ps = new Points();
		ps.decompStringPoints(listS);
		assertEquals(15, ps.midX());
	}
	@Test
	void testMidY() {
		listS = new ArrayList<String>();
		String s1 = "10 20 30";
		String s2 = "20 30 40";
		listS.add(s1);
		listS.add(s2);
		ps = new Points();
		ps.decompStringPoints(listS);
		assertEquals(25, ps.midY());
	}
	@Test
	void testMinMaxXY() {
		listS = new ArrayList<String>();
		String s1 = "50 20 30 40";
		String s2 = "50.1 42.5455 53.5353";
		String s3 = "50.123 30 42.3 23.4";
		String s4 = "65.2 30 42.3 23.4";
		listS.add(s1);
		listS.add(s2);
		listS.add(s3);
		listS.add(s4);
		ps = new Points();
		ps.decompStringPoints(listS);
		assertEquals(50, ps.minX(),0.05);
		assertEquals(65.2, ps.maxX(),0.05);
		assertEquals(20, ps.minY(),0.05);
		assertEquals(42.5455, ps.maxY(), 0.05);

		
	}
}
