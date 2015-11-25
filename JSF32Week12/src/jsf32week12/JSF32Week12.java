/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf32week12;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *
 * @author juleskreutzer
 */
public class JSF32Week12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("For what level would you like to calculate the edges?");
        
        int level = 1;
        try{
            level = scanner.nextInt();
        }
        catch(Exception ex)
        {
            System.out.print(ex.toString());
        }
        
        KochFractal koch = new KochFractal();
        koch.setLevel(level);
        
        int edges = koch.getNrOfEdges();
        
        PrintWriter pr = null;
        
        try{
            pr = new PrintWriter("result.txt", "UTF-8");
            pr.append("Level: " + level + "\n");
            //pr.write("Level: " + level);
            pr.append("Number of edges: " + edges + "\n\n");
            System.out.print("Done!");
        }
        catch(FileNotFoundException | UnsupportedEncodingException ex){
            System.out.print(ex.toString());
        }
        finally{
            pr.close();
        }
    }
    
}
