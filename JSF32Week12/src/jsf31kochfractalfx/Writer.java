/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf31kochfractalfx;

import calculate.Edge;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jules
 */
public class Writer implements Observer {
    private int totalEdges;
    private List<Edge> edges;
    private int level; 
    
    public Writer(int level)
    {
        this.level = level;
        KochFractal koch = new KochFractal();
        koch.addObserver(this);
        koch.setLevel(level);
        
        //totalEdges = koch.getNrOfEdges();
        edges = new ArrayList<>();
        
        koch.generateBottomEdge();
        koch.generateLeftEdge();
        koch.generateRightEdge();
        
        totalEdges = koch.getNrOfEdges();
        if(totalEdges >= edges.size())
        {
        writeToFile();
        }
    }
            

    @Override
    public void update(Observable o, Object arg) {
        edges.add((Edge)arg);
        
    }
    
    private void writeToFile()
    {
        DataOutputStream dos = null;
        
        try{
            // Write text
            dos = new DataOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result.txt"));
            dos.writeInt(level);
            for(Edge e : edges)
            {
                dos.writeDouble(e.X1);
                dos.writeDouble(e.Y1);
                dos.writeDouble(e.X2);
                dos.writeDouble(e.Y2);
                dos.writeDouble(e.color.getRed());
                dos.writeDouble(e.color.getGreen());
                dos.writeDouble(e.color.getBlue());
            }
            dos.close();
            System.out.print("DataOutputStream to result.txt done!\n");
            
            
            // Write text with buffer
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result_buffer.txt")));
            dos.writeInt(level);
            for(Edge e : edges)
            {
                dos.writeDouble(e.X1);
                dos.writeDouble(e.Y1);
                dos.writeDouble(e.X2);
                dos.writeDouble(e.Y2);
                dos.writeDouble(e.color.getRed());
                dos.writeDouble(e.color.getGreen());
                dos.writeDouble(e.color.getBlue());
            }
            dos.close();
            System.out.print("DataOutputStream to result_buffer.txt done!\n");
            
            // Write to bin file
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result_buffer.bin")));
            dos.writeInt(level);
            for(Edge e : edges)
            {
                dos.writeDouble(e.X1);
                dos.writeDouble(e.Y1);
                dos.writeDouble(e.X2);
                dos.writeDouble(e.Y2);
                dos.writeDouble(e.color.getRed());
                dos.writeDouble(e.color.getGreen());
                dos.writeDouble(e.color.getBlue());
            }
            dos.close();
            System.out.print("DataOutputStream to result_buffer.bin done!\n");
            
            dos = new DataOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result.bin"));
            dos.writeInt(level);
            for(Edge e : edges)
            {
                dos.writeDouble(e.X1);
                dos.writeDouble(e.Y1);
                dos.writeDouble(e.X2);
                dos.writeDouble(e.Y2);
                dos.writeDouble(e.color.getRed());
                dos.writeDouble(e.color.getGreen());
                dos.writeDouble(e.color.getBlue());
            }
           dos.close();
           System.out.print("DataOutputStream to result.bin done!\n");
            
        } catch(FileNotFoundException ex)
        {
            System.out.print(ex.getMessage());
        }
        catch(IOException ex)
        {
            System.out.print(ex.getMessage());
        }
        
    }
    
}
