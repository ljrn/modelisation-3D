package projet.reader;

import java.io.File;
import java.io.IOException;

public class CreateEnvironment {
    public Points pts=new Points();
    public Faces fa=new Faces();
    public Faces createFaces(File f, double x, double y) throws IOException{
        String s=f.getAbsolutePath();
        ReadFile rf=new ReadFile(s);
        rf.readHeader();
        pts.decompStringPoints(rf.getPoints(),x,y);
        fa.decompStringFaces(rf.getFaces(), pts);
        fa.trierFaces();
        return fa;
    }
}