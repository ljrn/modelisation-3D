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
			System.out.println(""+rf.getVertex());
			System.out.println(""+rf.getFace());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
