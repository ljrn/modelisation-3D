package projet.modele;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class FileCreator {
	/**
	 * @param path Chemin du r�pertoire
	 * @param listString nom du fichier
	 * @return Le chemin absolu d'un fichier
	 */
	public File getFile(File path, String listString) {
		String patternFile = Pattern.quote(System.getProperty("file.separator"));
		return new File(path.getAbsolutePath()+File.separator+listString.split(patternFile)[listString.split(patternFile).length-1].replace("]", ""));
	}
	/**
	 * @param theFile Fichier pour lequel on veut conna�tre la date
	 * @return Date de derni�re modification du fichier
	 */
	public String getDate(File theFile) {
		try {
			BasicFileAttributes attributs=Files.readAttributes(theFile.toPath(), BasicFileAttributes.class);
			FileTime theDate=attributs.creationTime();
			String pattern = "yyyy-MM-dd HH:mm:ss";
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		    return simpleDateFormat.format(new Date(theDate.toMillis()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param theFile Fichier pour lequel on veut conna�tre l'auteur
	 * @return String contenant le nom de l'auteur du fichier
	 */
	public String getAuthor(File theFile) {
		try {
			return Files.getOwner(theFile.toPath()).getName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
