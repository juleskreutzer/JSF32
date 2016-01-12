/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
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
    
    private String ip;
    private int port;
    
    @Override
    public void start(Stage primaryStage) {
        
        Label resultLabel = new Label();
        
        
        resultLabel.setText("Hello, World!");
        
        resultLabel.setTranslateY(-80);
        
        StackPane root = new StackPane();
        
        root.getChildren().add(resultLabel);
       
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        setup();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void setup()
    {
        try{
            ip = InetAddress.getLocalHost().toString();
            ip = "10.0.1.41";
            System.out.print(ip);
            port = 1199;
        }
        catch(UnknownHostException ex)
        {
            System.out.print(ex.toString());
        }
    }
    
}
