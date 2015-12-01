/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf32week12;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jules
 */
public class GUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Label errorLabel = new Label();
        Label resultLabel = new Label();
        Button btnText = new Button();
        Button btnBin = new Button();
        Button btnBuffer = new Button();
        
        resultLabel.setText("");
        errorLabel.setText("");
        btnText.setText("Read from text file");
        btnBin.setText("Read from bin file");
        btnBuffer.setText("Read from text file (buffer)");
        
        resultLabel.setTranslateY(-80);
        errorLabel.setTranslateY(-40);
        btnText.setTranslateY(0);
        btnBin.setTranslateY(40);
        btnBuffer.setTranslateY(80);
        
       btnText.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                File file = new File("/media/jules/secondDisk/JSF32/week12/result.txt");
                BufferedInputStream in = null;
                try {
                    in = new BufferedInputStream(new FileInputStream(file));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] contents = new byte[1024];

                int bytesRead=0;
                String strFileContents = "";
                try {
                    while( (bytesRead = in.read(contents)) != -1){               
                        strFileContents = new String(contents, 0, bytesRead);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultLabel.setText(String.format("Result (level, number of edges): %s", strFileContents));
            }
           
       });
       
       btnBin.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                File file = new File("/media/jules/secondDisk/JSF32/week12/binContents.bin");
                BufferedInputStream in = null;
                try {
                    in = new BufferedInputStream(new FileInputStream(file));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] contents = new byte[1024];

                int bytesRead=0;
                String strFileContents = "";
                try {
                    while( (bytesRead = in.read(contents)) != -1){               
                        strFileContents = new String(contents, 0, bytesRead);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultLabel.setText(String.format("Result (level, number of edges): %s", strFileContents));
            }
           
       });
       
       btnBuffer.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event event) {
                File file = new File("/media/jules/secondDisk/JSF32/week12/result_buffered.txt");
                BufferedInputStream in = null;
                try {
                    in = new BufferedInputStream(new FileInputStream(file));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] contents = new byte[1024];

                int bytesRead=0;
                String strFileContents = "";
                try {
                    while( (bytesRead = in.read(contents)) != -1){               
                        strFileContents = new String(contents, 0, bytesRead);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultLabel.setText(String.format("Result (level, number of edges): %s", strFileContents));
            }
       });
    
        
        StackPane root = new StackPane();
        root.getChildren().add(errorLabel);
        root.getChildren().add(btnText);
        root.getChildren().add(btnBin);
        root.getChildren().add(btnBuffer);
        root.getChildren().add(resultLabel);
       
        
        Scene scene = new Scene(root, 300, 250);
        
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
