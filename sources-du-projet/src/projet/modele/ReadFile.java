package projet.modele;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe permettant de lire un fichier .ply et de le parser
 */
public class ReadFile {
	/*
	 * Le fichier ply contient :
	 * 					- Un header décrivant le modèle
	 * 					- Une liste de points
	 * 					- Une liste de faces
	 * Le f est le fichier ply
	 */
	private File file;
	private List<String> header = new ArrayList<>();
	private List<String> points = new ArrayList<>();
	private List<String> faces = new ArrayList<>();
 	/**
 	 * Initialise un lecteur de fichier ply
 	 * @param s Le nom du fichier
 	 */
	public ReadFile(String s) {
		this.file=new File(s);
	}
	/**
	 * Lis le fichier pour remplir les trois listes de String
	 * @throws IOException
	 */
	public void readPly() throws IOException{
		Reader reader=new FileReader(file);
		BufferedReader buff=new BufferedReader(reader);
		String toAdd=buff.readLine();
		header.add(toAdd);
		while(!toAdd.equals("end_header")){
			toAdd=buff.readLine();
			header.add(toAdd);
		}
		for(int cptPts = 0; cptPts<this.getVertex();cptPts++) {
			this.points.add(buff.readLine());
		}
		for(int cptFace = 0; cptFace<this.getFace();cptFace++) {
			this.faces.add(buff.readLine());
		}		
		reader.close();
		buff.close();
	}
	/**
	 * @return Le nombre de points dans le modèle (et donc le nombre de lignes contenant des points à lire dans le fichier) 
	 */
	public int getVertex() {
		for(String string:header) { 
			if(string.contains("element vertex")){
				int res=(Integer.parseInt(string.substring(15)));
				return res;
			}
		}
		return -1;
	}
	/**
	 * @return Le nombre de faces dans le modèle (et donc le nombre de lignes contenant des faces à lire dans le fichier) 
	 */
	public int getFace() {
		for(String string:header) {
			if(string.contains("element face")){
				int res=(Integer.parseInt(string.substring(13)));
				return res;
			}
		}
		return -1;
	}
	/**
	 * 
	 * @return Le fichier ply
	 */
	public File getFile() {
		return file;
	}
	/**
	 * 
	 * @return Les lignes du header sous forme de liste de String
	 */
	public List<String> getHeader() {
		return header;
	}
	/**
	 * 
	 * @return La liste de tous les points du modèle
	 */
	public List<String> getPoints() {
		return points;
	} 
	/**
	 * 
	 * @return La liste de toutes les faces du modèle
	 */
	public List<String> getFaces() {
		return faces;
	}
	
}
