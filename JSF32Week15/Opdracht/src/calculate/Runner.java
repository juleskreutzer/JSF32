/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import calculate.KochManager.GeneratePart;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author juleskreutzer
 */

//Not used anymore, has been replaced by calcTask
public class Runner implements Runnable, Observer {
    
    private KochFractal koch;
    private Socket s;
    private List<Edge> edges;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private Object incomingObject = null;
    private boolean finished = false;
    
    public Runner(Socket s) throws IOException
    {
        this.s = s;
    }

    @Override
    public void run() {
        try{
            while(!finished)
            {
                try{
                    out = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
                    in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
                    
                    incomingObject = in.readObject();
                    if(incomingObject instanceof Integer)
                    {
                        int level = (int)incomingObject;
                        this.koch = new KochFractal();
                        this.koch.setLevel(level);
                        this.koch.generateBottomEdge();
                        this.koch.generateLeftEdge();
                        this.koch.generateRightEdge();
                        this.edges = this.koch.getEdges();
                        out.writeObject(this.edges);
                        out.flush();
                        System.out.print("test");
                    } 
                }
                catch(IOException | ClassNotFoundException ex)
                {
                    System.out.print(ex.toString());
                }
                finally{
                    s.close();
                }
            }
        }
        catch(IOException ex)
        {
            System.out.print(ex.toString());
        }
        
    }

    @Override
    public void update(Observable o, Object arg) {
        synchronized(this)
        {
            edges.add((Edge)arg);
            try {
                out.writeObject((Edge)arg);
            } catch (IOException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
