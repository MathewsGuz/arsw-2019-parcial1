/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto2;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
/**
 *
 * @author 2108263
 */
public class GlobalKeyListenerExample implements NativeKeyListener {
	public void nativeKeyPressed(NativeKeyEvent e) {
                long keyPressedMillis = System.currentTimeMillis();
                
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
                long keyReleasedMillis = System.currentTimeMillis();
                System.out.println("timeReleased:  "+keyReleasedMillis);
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
	}
        
}
