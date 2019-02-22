package edu.eci.arsw.GuidFinderDesktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuidFinder {
	
	private static UUID[] guids; 
        public ArrayList<Mathread> hilos = new ArrayList<>();
//        public AtomicInteger cont =new AtomicInteger();
        public int count =0;
	
	
	public GuidFinder() throws Exception {
		getGuids();
	}
	
	public static UUID[] getGuids() throws Exception 
	{
	
		if(guids==null){
			System.out.println("es nulo");
		FileInputStream fi;
		
			fi = new FileInputStream(new File("guids.eci"));
		
		ObjectInputStream oi = new ObjectInputStream(fi);

		
		guids= (UUID[]) oi.readObject();
	
		oi.close();
		fi.close();
		}
//                for (int i =0;i<50;i++) {
//                    System.out.print(guids[i]);
//                }
                
		return guids;
		
	}
	
	public int countGuids(UUID guidToFind) 
	{
            //creacion(guids,range)
            //retornar variable count
            int ini=0;
            int rango=guids.length/4;
            System.out.print(rango);
            for(int i=0 ; i<4;i++){
                hilos.add(new Mathread(guids,ini+(rango*i),rango*(i+1),guidToFind));
//                System.out.print("inicio: "+ini+(rango*i));
//                System.out.print("fin: "+ rango*(i+1));
            }
            
            for(Mathread i:hilos){
                i.start();
            }
            
            for(Mathread i:hilos){
                try {
                    i.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuidFinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            for(Mathread i:hilos){
                count += i.getCont();
            }
            
//		int count=0;
//		for (UUID uuid : guids) {
//                    
//			if(uuid.equals(guidToFind))
//					{
//				count++;
//					}
//			
//		}
            return count;
	}
}
