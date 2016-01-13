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
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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
            
//            File file = new File("/media/jules/secondDisk/JSF32/week13/MappedResult.txt");
//            File file2 = new File("/media/jules/secondDisk/JSF32/week13/MappedResultWritten.txt");
            File file = new File("/MappedResult.txt");
            File file2 = new File("/MappedResultWritten.txt");
            
            //Delete the file; we will create a new file
            file.delete();

            // Get file channel in readonly mode
            FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();

            // Get direct byte buffer access using channel.map() operation
            // 7 values * 8 bytes(double = 8byte) 
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, (7*8)*edges.size());
            //long length = (7*8)*edges.size();
            // fileChannel.map(Mode, position, size)
            // http://docs.oracle.com/javase/7/docs/api/java/nio/channels/FileChannel.html#map(java.nio.channels.FileChannel.MapMode,%20long,%20long)

            //Write the content using put methods
            for(Edge e : edges){
                buffer.putDouble(e.X1);
                buffer.putDouble(e.Y1);
                buffer.putDouble(e.X2);
                buffer.putDouble(e.Y2);
                buffer.putDouble(e.color.getRed());
                buffer.putDouble(e.color.getGreen());
                buffer.putDouble(e.color.getBlue());
            }
            
            if(file.exists())
            {
                file.renameTo(file2);
            }
            
            System.out.print("Result to MappedResult.txt done!");
            
            // Write text
            //dos = new DataOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result.txt"));
            dos = new DataOutputStream(new FileOutputStream("/result.txt"));
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
//            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result_buffer.txt")));
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/result_buffer.txt")));
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
            //dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result_buffer.bin")));
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/result_buffer.bin")));
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
            
            //dos = new DataOutputStream(new FileOutputStream("/media/jules/secondDisk/JSF32/week12/result.bin"));
            dos = new DataOutputStream(new FileOutputStream("/result.bin"));

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
