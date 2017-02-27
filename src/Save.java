import java.io.*;
import java.util.*;
	
	/*
	 * This class handles the level progression. The file being loaded is the map for each level.
	 * From the file being loaded, the data produces the map for the player.
	 * 
	 */

public class Save {
	public void loadSave(File loadPath) {
		try {
			Scanner loadScanner = new Scanner(loadPath);

			while (loadScanner.hasNext()) {
				for (int y = 0; y < Screen.room.block.length; ++y) {
					for (int x = 0; x < Screen.room.block[0].length; ++x) {
						Screen.room.block[y][x].groundID = loadScanner.nextInt();
					}
				}
				for (int y = 0; y < Screen.room.block.length; ++y) {
					for (int x = 0; x < Screen.room.block[0].length; ++x) {
						Screen.room.block[y][x].airID = loadScanner.nextInt();
					}
				}
				loadScanner.close();
			}
		} catch (Exception e) {
		}
	}
}
