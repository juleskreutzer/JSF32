/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf32week12;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juleskreutzer
 */
public class JSF32Week12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("For what level would you like to calculate the edges?");
        
        int level = 1;
        try{
            level = scanner.nextInt();
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        
        KochFractal koch = new KochFractal();
        koch.setLevel(level);
        
        int edges = koch.getNrOfEdges();
        
        PrintWriter pr = null;
        FileOutputStream fos = null;
        File file = null;
        BufferedOutputStream bos = null;
        String binContent = String.format("%d,%d", level, edges);
        
        try{
            pr = new PrintWriter("/media/jules/secondDisk/JSF32/week12/result.txt", "UTF-8");
            pr.append(level + "," + edges);
            System.out.print("Done! (written as text file)\n");
            
            file = new File("/media/jules/secondDisk/JSF32/week12/binContents.bin");
            byte[] byteArray = binContent.getBytes();
            fos = new FileOutputStream(file);
            fos.write(byteArray);
            System.out.print("Done! (written as binary file)\n");
            
            bos = new BufferedOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result_buffered.txt"));
            bos.write(byteArray);
            System.out.print("Done! (written as buffered text file)\n");
            
        }
        catch(FileNotFoundException | UnsupportedEncodingException ex){
            System.out.print(ex.toString());
        }
        finally{
            pr.close();
            fos.close();
            bos.close();
        }
    }
    
}
