package projet.modele;

import java.io.File;
import java.io.IOException;

public class CreateEnvironment {
    public Points pts=new Points();
    public Faces fa=new Faces();
    
    /**
     * 
     * @param file Fichier dans lequel les faces doivent être lues
     * @param hauteur Hauteur du modèle
     * @param largeur Largeur du modèle
     * @return Un Faces dans lequel les faces sont triées
     */
    public Faces createFaces(File file, double hauteur,double largeur){
        String s=file.getAbsolutePath();
        ReadFile rf=new ReadFile(s);
        try {
			rf.readHeader();
			 pts.decompStringPoints(rf.getPoints(),hauteur,largeur);
		     fa.decompStringFaces(rf.getFaces(), pts);
		     fa.trierFaces();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return fa;
    }
}