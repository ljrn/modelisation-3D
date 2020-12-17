package projet.modele;

import java.util.ArrayList;
import java.util.List;
public class Translate {	
	public void translate(Faces f, double x, double y, double z) {
		
		double[][] matrice= {{1,0,0,x},{0,1,0,y},{0,0,1,z},{0,0,0,1}};
		Matrice m=new Matrice();
		System.out.println(m.toString(m.multiplicationTranslation(f.midPoint().toMatrice(),matrice)));
		List<Point> modified=new ArrayList<>();
		for(Face fa:f.getFaces()) {
			for(Point p:fa.getPoints()) {
				if(!modified.contains(p)) {
					double[][]point=m.multiplicationTranslation(p.toMatrice(), matrice);
					p.assign(point);
					modified.add(p);
				}
			}
		}
		System.out.println(f.midPoint());
		f.notifyObservers(f);
	}
}
