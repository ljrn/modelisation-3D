package reader;

import java.util.ArrayList;
import java.util.List;

public class Faces {
	private List<Face> faces = new ArrayList<Face>();
	
	public void decompStringFaces(List<String> stringFaces) {
		for (String string : stringFaces) {
			String[] tab = string.split(" ");
			int nbFaces = Integer.parseInt(tab[0]);
			List<Integer> facesInt = new ArrayList<Integer>();
			for (int i = 1; i < tab.length; i++) {
				facesInt.add(Integer.parseInt(tab[i]));
			}
			this.faces.add(new Face(nbFaces, facesInt));
		}
	}
}
