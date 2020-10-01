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


public class ListViewer extends Application {
	  Label label;
	  ListView<String> list = new ListView<String>();
	  HBox root = new HBox();
	  String[] filelist;
	  File path;

	  class MonListChangeListener implements ListChangeListener<String> {
	    public void onChanged(ListChangeListener.Change<? extends String> c) {
	    	list.getItems().clear();
	    	path = new File("../ressources"+c.getList().toString().substring(1, c.getList().toString().length()-1));
	    	filelist = path.list();
	    	if(path.isDirectory()) {
	    		list.getItems().addAll(filelist);
	    		list.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());
	    	}else {
	    		label.setText("Selection de " + c.getList());
	    	}
	    }
	  }

	  public void start(Stage stage) {
	    ListView<String> listV = new ListView<String>();
  
	    File path = new File("../ressources");
	    for(String s:path.list()) {
	    	System.out.println(s);
	    }
	    String[] filelist = path.list();
	    
	    listV.getItems().addAll(filelist);
	    listV.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());

	    root.setAlignment(Pos.CENTER_LEFT);
	    root.getChildren().addAll(listV,list);

	    Scene scene = new Scene(root, 400, 150);
	    stage.setTitle("FileChooser");
	    stage.setScene(scene);
	    stage.show();
	  }

	  public static void main(String[] args) {
	    Application.launch(args);
	  }

}
