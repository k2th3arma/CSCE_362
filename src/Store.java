import java.awt.*;

public class Store {
	public static int shopWidth = 8;
	public static int buttonSize = 64;
	public static int cellSpace = 2;
	public static int awayFromRoom = 10;
	public static int iconSize = 20;
	public static int iconSpace = 3;
	public static int iconTextY = 15;

	public static int itemIn = 4;
	public static int heldID = -1;
	public static int realID = -1;
	
	//Finds the values for the icons being placed in the store bar
	public static int[] buttonID = { Value.airTowerLaser, Value.airAir, Value.airAir, Value.airAir, Value.airAir,
			Value.airAir, Value.airAir, Value.airTrashCan }; 
	
	// The cost of each of the boxes on the store bar
	public static int[] buttonPrice = { 10, 0, 0, 0, 0, 0, 0, 0 }; 
	public static int[] buttonId = new int[shopWidth];
	
	//Creates rectangles for the values
	public Rectangle buttonHealth;
	public Rectangle buttonCoins;
	public Rectangle buttonScore; 	
	public Rectangle buttonWave;
	
	public Rectangle[] button = new Rectangle[shopWidth];
	
	public boolean holdsItem = false;

	public Store() {
		define();
	}
	
	//Handles whether a tower is being selected and controls purchasing
	public void click(int mouseButton) {		 
		if (mouseButton == 1) {
			for (int i = 0; i < button.length; ++i) {
				if (button[i].contains(Screen.mse)) {
					if (buttonID[i] != Value.airAir) {
						if (buttonID[i] == Value.airTrashCan) {
							holdsItem = false;

						} else {
							heldID = buttonID[i];
							realID = i;
							holdsItem = true;
						}
					}
				}
			}
			if(holdsItem){
				if(Screen.money >= buttonPrice[realID]){
					for (int y = 0;y < Screen.room.block.length; ++y) {
						for (int x = 0;x < Screen.room.block[0].length; ++x) {
							if(Screen.room.block[y][x].contains(Screen.mse)){
								if(Screen.room.block[y][x].groundID != Value.groundRoad && Screen.room.block[y][x].airID == Value.airAir){
									Screen.room.block[y][x].airID = heldID;
									Screen.money -= buttonPrice[realID];
								}
							}
							
						}
					}
				}
			}
		}
		else if (mouseButton == 3) {
			for (int i = 0; i < button.length; ++i) {
				if (button[i].contains(Screen.mse)) {
					if (buttonID[i] != Value.airAir) {
						if (buttonID[i] == Value.airTrashCan) {
							holdsItem = false;

						} else {
							Upgrade upgrade = new Upgrade();

						}
					}
				}
			}
		}	
	}
	
	//Produces the store bar
	public void define() {			
		for (int i = 0; i < button.length; i++) {
			button[i] = new Rectangle(
					Screen.myWidth / 2 - ((shopWidth * (buttonSize + cellSpace)) / 2) + (buttonSize + cellSpace) * i,
					(Screen.room.block[Screen.room.worldHeight - 1][0].y) + Screen.room.blockSize + cellSpace
							+ awayFromRoom,
					buttonSize, buttonSize);
		}
		
		//Controls placement for the values, such as health and money.
		buttonHealth = new Rectangle(Screen.room.block[0][0].x - 1, button[0].y, iconSize, iconSize);
		buttonCoins  = new Rectangle(Screen.room.block[0][0].x - 1, button[0].y + button[0].height - iconSize, iconSize, iconSize);
		buttonScore   = new Rectangle(Screen.room.block[0][0].x - 1, (button[0].y + button[0].height + iconSize), iconSize, iconSize);		
		buttonWave   = new Rectangle(Screen.room.block[1][1].x - 1, button[0].y, iconSize, iconSize);
	}
	
	//Draws images for the store class
	public void draw(Graphics g) { 
		for (int i = 0; i < button.length; i++) {
			if (button[i].contains(Screen.mse)) {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
			g.drawImage(Screen.tileset_store[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
			if (buttonID[i] != Value.airAir) {
				g.drawImage(Screen.tileset_air[buttonID[i]], button[i].x, button[i].y, button[i].width - (itemIn * 2),
						button[i].height - (itemIn), null);
			}
			if (buttonPrice[i] > 0) {
				g.setColor(new Color(255, 255, 255));
				g.setFont(new Font("Courier New", Font.BOLD, 14));
				g.drawString("$" + buttonPrice[i], button[i].x + itemIn, button[i].y + itemIn + 10);
				g.drawString("Lvl: " + Value.towerLevel, button[i].x + itemIn, button[i].y + itemIn + 50);
			}
		}
		
		//Draws the images associated with the appropriate text
		g.drawImage(Screen.tileset_store[1], buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
		g.drawImage(Screen.tileset_store[2], buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height, null);
		g.drawImage(Screen.tileset_store[3], buttonScore.x, buttonScore.y, buttonScore.width, buttonScore.height, null);
		g.drawImage(Screen.tileset_store[4], buttonWave.x, buttonWave.y, buttonWave.width, buttonWave.height, null);
		
		//sets color and font for the text
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.setColor(new Color(255, 255, 255));
		
		//Draws values for the rectangles
		g.drawString("" + Screen.health, buttonHealth.x + buttonHealth.width + iconSpace, buttonHealth.y + iconTextY);
		g.drawString("" + Screen.money, buttonCoins.x + buttonCoins.width + iconSpace, buttonCoins.y + iconTextY);
		g.drawString("" + Screen.score, buttonScore.x + buttonScore.width + iconSpace, buttonScore.y + iconTextY);
		g.drawString("" + Wave.wave, buttonWave.x + buttonWave.width + iconSpace, buttonWave.y + iconTextY);

		if (holdsItem) {
			g.drawImage(Screen.tileset_air[heldID], Screen.mse.x - ((button[0].width - (itemIn * 2)) / 2) + itemIn,
					Screen.mse.y - ((button[0].width - (itemIn * 2)) / 2) + itemIn, button[0].width - (itemIn * 2),
					button[0].height - (itemIn), null);
		}

	}
}
