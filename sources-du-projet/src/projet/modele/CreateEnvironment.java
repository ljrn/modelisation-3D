package projet.modele;

import java.io.File;
import java.io.IOException;

public class CreateEnvironment {
    public Points pts=new Points();
    public Faces fa=new Faces();
    public Faces createFaces(File f, double x, double y){
        String s=f.getAbsolutePath();
        ReadFile rf=new ReadFile(s);
        try {
			rf.readHeader();
			 pts.decompStringPoints(rf.getPoints(),x,y);
		     fa.decompStringFaces(rf.getFaces(), pts);
		     fa.trierFaces();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return fa;
    }
}