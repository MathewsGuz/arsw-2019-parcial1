/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2;

import edu.eci.arsw.GuidFinderDesktop.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2108263
 */
public class Mathread extends Thread {
    
    public int inicio;
    public int fin;
    public int cont;
    public UUID[] guids; 
    public UUID target;
    public boolean isPause;
    public Object lock =new Object();

    Mathread(UUID[] guids, int ini, int rango,UUID guidToFind) {
       this.inicio=ini;
       this.fin=rango;
       this.guids=guids;
       this.target=guidToFind;
    }

    @Override
    public void run() {
        int cont=0;
        int i=inicio;
        while(i<fin) {
//            while(!isPause){
                if(!isPause){
                    
                    if(guids[i].equals(target)){
                        cont++;
                    }
                }else{
                    synchronized(lock){
                        try {
                            
                            wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Mathread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    }
//                }
            i++;
        }

            
        
    }

    public int getCont() {
        return cont;
    }
    
    public void play(){
        isPause=false;
        synchronized(lock){
            notifyAll();
        }
    }
    
    public void pause(){
        isPause=true;
    }
    
    
    
}
