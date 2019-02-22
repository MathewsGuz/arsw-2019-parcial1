/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.GuidFinderDesktop;

import java.util.UUID;

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

    Mathread(UUID[] guids, int ini, int rango,UUID guidToFind) {
       this.inicio=ini;
       this.fin=rango;
       this.guids=guids;
       this.target=guidToFind;
    }

    @Override
    public void run() {
        int cont=0;
        for (int i =inicio; i<fin;i++) {
            if(guids[i].equals(target))
                {
                  cont++;
                }
        }
    }

    public int getCont() {
        return cont;
    }
    
    
    
}
