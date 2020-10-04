package projet.reader;

import java.io.IOException;

public class CreateEnvironment {
	public Points ps=new Points();
	public Faces fa=new Faces();
	public Faces createFaces(String s, double x, double y) throws IOException{
		ReadFile rf=new ReadFile(s);
		rf.readHeader();
		ps.decompStringPoints(rf.getPoints(),x ,y);
		fa.decompStringFaces(rf.getFaces(), ps);
		return fa;
	}
}
