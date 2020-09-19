package reader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class UseReadFile {
	public static void main(String[]args) {
		ReadFile rf=new ReadFile("./ressources/airplane.ply");
		try {
			List<String> ls=rf.readHeader();
			for(String s:ls) {
				System.out.println(s);
			}
			Points ps=new Points();
			ps.decompStringPoints(rf.getPoints());
			Faces f=new Faces();
			f.decompStringFaces(rf.getFaces(), ps);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
