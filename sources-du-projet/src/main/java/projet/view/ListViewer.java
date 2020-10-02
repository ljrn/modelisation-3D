package projet.view;


import java.io.File;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class ListViewer{
	ListView<File> listFiles;
	public HBox ListFiles() {
	    File path = new File("./ressources");
	    listFiles=new ListView<>();
	    listFiles.getItems().addAll(path.listFiles());
	    HBox root = new HBox();
	    root.getChildren().addAll(listFiles);
	    return root;
	  }

}
