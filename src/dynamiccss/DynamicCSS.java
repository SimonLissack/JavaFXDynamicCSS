/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamiccss;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Simon
 */
public class DynamicCSS extends Application {
    Scene scene;
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        scene = new Scene(root, 300, 250);
        
        /**
         * Load CSS from compiled jar
         */
        Button jar = new Button("Load From Jar");
        jar.setOnAction(e ->{
            // Note - CSS file has to be in src dir
            String css = DynamicCSS.class.getResource("/jarcss.css").toExternalForm();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);
        });
        
        /**
         * Load CSS from a local file
         */
        Button file = new Button("Load From File");
        file.setOnAction(e -> {
            File f = new File("filecss.css");
            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        });
        
        /**
         * Load CSS from URL
         */
        Button url = new Button("Load From URL");
        url.setOnAction(e -> {
            scene.getStylesheets().clear();
            scene.getStylesheets().add("http://www.jpedal.org/simon/dynamiccss/webcss.css");
        });
        
        /**
         * Remove all CSS
         */
        Button clear = new Button();
        clear.setText("Clear");
        clear.setOnAction(e -> {scene.getStylesheets().clear();});
        
        root.getChildren().addAll(jar, file, url, clear);
        
        root.setAlignment(Pos.CENTER);
        root.getChildren().stream().forEach(btn->{((Button)btn).setPrefWidth(100);});
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
