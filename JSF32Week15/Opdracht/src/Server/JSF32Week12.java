/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import calculate.Runner;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author juleskreutzer
 */
public class JSF32Week12 {

    private String ip;
    private static final Logger LOG = Logger.getLogger(JSF32Week12.class.getName());
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        
        ServerSocket server = new ServerSocket(9999);
        LOG.log(Level.INFO, "Server is running. Listening on port: {0}", server.getLocalPort());

        
        while(true)
        {
            Socket socket = server.accept();
            LOG.log(Level.INFO, "New Client Connected: {0}", socket.getInetAddress());
            // Get the level
            
//            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
//            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            
//            if(socket.isClosed())
//            {
//                socket = server.accept();
//                in = new ObjectInputStream(socket.getInputStream());
//                out = new ObjectOutputStream(socket.getOutputStream());
//            }
//            
            Thread thread = new Thread(new Runner(socket));
            thread.start();
            
        }
    }
    
}
