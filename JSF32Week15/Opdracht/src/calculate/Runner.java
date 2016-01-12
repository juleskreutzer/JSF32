/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import calculate.KochManager.GeneratePart;
import java.io.IOException;
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
    
    final KochFractal koch;
    private Socket s;
    private List<Edge> edges;
    private ObjectOutputStream out;
    
    public Runner(Socket s, int level) throws IOException
    {
        this.koch = new KochFractal();
        this.s = s;
        this.koch.setLevel(level);
        this.edges = new ArrayList<>();
        
        this.out = new ObjectOutputStream(s.getOutputStream());
        
    }

    @Override
    public void run() {
        
        
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
