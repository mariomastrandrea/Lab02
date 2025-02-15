package it.polito.tdp.alien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.polito.tdp.model.*;

public class EntryPoint extends Application 
{
    @Override
    public void start(Stage stage) throws Exception 
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene_Lab02.fxml"));
        Parent root = loader.load();
   
        FXMLController alienController = loader.getController();
        alienController.setModel(AlienWordsManager.instance());
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Lab02 - alien");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
