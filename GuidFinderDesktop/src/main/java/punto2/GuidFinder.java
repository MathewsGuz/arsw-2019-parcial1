package punto2;

import edu.eci.arsw.GuidFinderDesktop.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

public class GuidFinder  {
	
	private static UUID[] guids; 
        public ArrayList<Mathread> hilos = new ArrayList<>();
//        public AtomicInteger cont =new AtomicInteger();
        public int count =0;
	public boolean flag;
        public long keyPressedMillis;
        public long keyReleasedMillis;
        public long time;
        public Timer timer;
	
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
                System.out.print(guids.length);
                
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
            
            int m=0;
            flag = hilos.get(m).isAlive();
            for (int i =1; i<4;i++) {
                flag=flag || hilos.get(i).isAlive();
            }
            
            while(flag){
                //if teclado quieto play
                if(System.currentTimeMillis() - time <1000){
                    System.out.println("esoty trabajando");
                    for(Mathread i:hilos){
                        i.play();
                    }
                }
                //else pause
                else{
                    System.out.println("esoty pausado");
                    for(Mathread i:hilos){
                        i.pause();
                    }
                }
                flag = hilos.get(m).isAlive();
                for (int i =1; i<4;i++) {
                    flag=flag || hilos.get(i).isAlive();
                }
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
        
        public void nativeKeyPressed(NativeKeyEvent e) {
                keyPressedMillis = System.currentTimeMillis();
                
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                System.out.println("timeTpied:  "+keyPressedMillis);
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
				try {
					GlobalScreen.unregisterNativeHook();
				} catch (NativeHookException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                keyReleasedMillis = System.currentTimeMillis();
                System.out.println("timeReleased:  "+keyReleasedMillis);
                timer = new Timer();
                
	}
        
        public float clock(){
            time =keyReleasedMillis;
            return time;
        }
}
