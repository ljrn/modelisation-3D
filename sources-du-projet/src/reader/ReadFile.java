 package reader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	private File f;
	private List<String> header = new ArrayList<String>();
	private List<String> points = new ArrayList<String>();
	private List<String> faces = new ArrayList<String>();
 	
	public ReadFile(String s) {
		this.f=new File(s);
	}
	
	public List<String> readHeader() throws IOException{
		Reader r=new FileReader(f);
		BufferedReader br=new BufferedReader(r);
		String toAdd=br.readLine();
		header.add(toAdd);
		while(!toAdd.equals("end_header")){
			toAdd=br.readLine();
			header.add(toAdd);
		}
		for(int cptPts = 0; cptPts<this.getVertex();cptPts++) {
			this.points.add(br.readLine());
		}
		for(int cptFace = 0; cptFace<this.getFace();cptFace++) {
			this.faces.add(br.readLine());
		}
		
		br.close();
		return this.header;
	}
	
	public int getVertex() {
		for(String s:header) {
			if(s.contains("element vertex")){
				int res=(Integer.parseInt(s.substring(15)));
				return res;
			}
		}
		return -1;
	}
	
	public int getFace() {
		for(String s:header) {
			if(s.contains("element face")){
				int res=(Integer.parseInt(s.substring(13)));
				return res;
			}
		}
		return -1;
	}

	public File getF() {
		return f;
	}

	public List<String> getHeader() {
		return header;
	}

	public List<String> getPoints() {
		return points;
	}

	public List<String> getFaces() {
		return faces;
	}
	
}
