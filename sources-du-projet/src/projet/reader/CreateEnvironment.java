package projet.reader;

import java.io.File;
import java.io.IOException;

public class CreateEnvironment {
    public Points ps=new Points();
    public Faces fa=new Faces();
    public Faces createFaces(File f, double x, double y) throws IOException{
        String s=f.getAbsolutePath();
        ReadFile rf=new ReadFile(s);
        rf.readHeader();
        ps.decompStringPoints(rf.getPoints(),x,y);
        fa.decompStringFaces(rf.getFaces(), ps);
        fa.trierFaces();
        return fa;
    }
}