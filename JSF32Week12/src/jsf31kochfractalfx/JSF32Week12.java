/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf31kochfractalfx;

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
        
        Writer writer = new Writer(level);
    }
    
}
