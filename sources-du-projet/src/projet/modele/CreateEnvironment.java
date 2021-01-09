package projet.modele;

import java.io.File;
import java.io.IOException;
/**
 * Classe permettant de cr�er l'environnement du modele
 */
public class CreateEnvironment {
    public Points points=new Points();
    public Faces faces=new Faces();
    
    /**
     * Cree les faces
     * @param file Fichier dans lequel les faces doivent �tre lues
     * @param hauteur Hauteur du mod�le
     * @param largeur Largeur du mod�le
     * @return Un Faces dans lequel les faces sont tri�es
     */
    public Faces createFaces(File file, double hauteur,double largeur){
        String s=file.getAbsolutePath();
        ReadFile rf=new ReadFile(s);
        try {
			rf.readPly();
			 points.decompStringPoints(rf.getPoints(),hauteur,largeur);
		     faces.decompStringFaces(rf.getFaces(), points);
		     faces.trierFaces();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return faces;
    }
}