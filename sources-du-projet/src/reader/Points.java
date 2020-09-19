package reader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Points {
	private List<Point> points = new ArrayList<Point>();
	
	public void decompStringPoints(ArrayList<String> stringPoints) {
		for (String string : stringPoints) {
			String[] tab = string.split(" ");
			points.add(new Point(Float.parseFloat(tab[0]+"F"), Float.parseFloat(tab[1]+"F"),Float.parseFloat(tab[2]+"F")));
		}
	}

	public List<Point> getPoints() {
		return points;
	}
	
}
