import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.*;

public class Wave {
	
	public static int wave = 0;
	//private int mod = 0;
	public static int spawnTime = 2600, spawnFrame = 0; //Times for mob spawns
	
		
	public static void waveControl(){
		
		mobSpawner(wave);
	}
	
	//Needs a method for controlling waves IE: every ten waves something changes
	

	public static void mobSpawner(int wave) { // Spawns mobs
		if (spawnFrame >= spawnTime) {
			for (int i = 0; i < Screen.mobs.length; ++i) {
				if (!Screen.mobs[i].inGame) {
					Screen.mobs[i].spawnMob(Value.mobBlue);  //Spawns the only mob

					break;
				}
			}
			


			spawnFrame = 0;
		} else {
			spawnFrame += 1;
		}
	}


}
