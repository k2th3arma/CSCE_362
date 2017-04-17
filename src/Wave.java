import java.util.concurrent.TimeUnit;

public class Wave {
	
	//Wave counter
	public static int wave = 0;
	public static int count = 0;
	
	//Controls the mob quantity per wave
	public static int mobBlueCount = 10;
	public static int mobRedCount = 7;
	public static int mobGreenCount = 5;
		
	//Timing for each mob
	public static int blue = 0;
	public static int red = 0;
	public static int green = 0;
	public static int spawnTime = 5, spawnFrame = 0;
	//!!!!!Adding a layer of time for each wave may be needed
			
	//Controls the functionality of each wave
	public static void waveControl(){		
		mobSpawner(wave);	
		//test();
			
		if(mobBlueCount <= 0 && mobRedCount <=0 && mobGreenCount <= 0){
			reset();
	
			if(wave == 10){
				waveMod();
				wave +=1;
			}
			else{
				wave +=1;
				count +=1;
				waveIncrement();
				//hold();
			}
		}	
	}
	
	//Resets all counters so each new wave remains consistent
	private static void reset(){
		mobBlueCount = 10;
		blue = 0;
		mobRedCount = 7;
		red = 0;
		mobGreenCount = 5;
		green = 0;
	}
	
	//Debug method
	private static void test(){	
		System.out.println("Wave" + wave);
			System.out.println("\t Blue " + mobBlueCount);
				System.out.println("\t\t BlueTime " + blue);
			System.out.println("\t Red " + mobRedCount);
				System.out.println("\t\t RedTime " + red);
			System.out.println("\t Green" + mobGreenCount);
				System.out.println("\t\t GreenTime " + green);
	}
	
	//Modification method, changes values for mobs
	private static void waveMod() {
		for(int i = 0; i < Value.tMob.length; ++i){
			Value.tMob[i] += 1;
		}
		Value.health = 100.0;
		count = 0;
	}
	
	//Wave increment method
	private static void waveIncrement(){
		mobBlueCount += 2*count;
		mobRedCount += 2*count;
		mobGreenCount += 2*count;
		Value.health += 20.0;
	}
		
	//Spawns mobs, private to avoid conflicts
	private static void mobSpawner(int wave) { 
		if (spawnFrame >= spawnTime) {
			timeIncrement();
			for (int i = 0; i < Screen.mobs.length; ++i) {
				if (!Screen.mobs[i].inGame) {
					if(blue >= 150 && mobBlueCount != 0){
						Screen.mobs[i].spawnMob(Value.mobBlue);
						blue = 0;
						mobBlueCount -=1;
					}
					if(red >= 250 && mobRedCount != 0){
						Screen.mobs[i].spawnMob(Value.mobRed);
						red = 0;
						mobRedCount -= 1;
					}
					if(green >= 350 && mobGreenCount != 0){
						Screen.mobs[i].spawnMob(Value.mobGreen);
						green = 0;
						mobGreenCount -= 1;
					}
					break;
				}
			}			
			spawnFrame = 0;
		} 
		else {
			spawnFrame += 1;

		}
	}
	
	//Increments the spawner times
	private static void timeIncrement(){
		blue += 1;
		red += 1;
		green += 1;
	}
	
	//Debug assistance, pauses the application for x seconds
	private static void hold(){
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
