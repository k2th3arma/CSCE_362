import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Screen extends JPanel implements Runnable {
	public Thread thread = new Thread(this);

	public static Image[] back_ground = new Image[100];
	public static Image[] tileset_air = new Image[100];
	public static Image[] tileset_store = new Image[100];
	public static Image[] tileset_mob = new Image[100];
	
	BufferedImage img = null;

	public static int myWidth, myHeight;
	
	//Values for starting health and money
	public static long money = 2000, health = 10, score = 0;
	
	//Debug boolean
	public static boolean isFirst = true;
	
	//Change to true to see tower range
	public static boolean isDebug = false;
	
	//Controls whether game is looping or not
	public static boolean isPaused = true;

	public static Room room = new Room();
	public static Save save = new Save();
	public static Store store = new Store();
	public static Mob[] mobs = new Mob[100];

	//Handles cursor location
	public static Point mse = new Point(0, 0);

	//Sets listeners
	public Screen(Frame frame) {
		frame.addMouseListener(new KeyHandle());
		frame.addMouseMotionListener(new KeyHandle());
		thread.start();
	}
	
	//Test method, remove later or reuse
	public static void gameState(){
		isPaused = true;
	}
	
	//Component that calls drawing classes
	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();
				
		//Pulls tile blocks from resources folder
		for (int i = 0; i < back_ground.length; i++) { 
			back_ground[i] = new ImageIcon("Resources/Test.png").getImage();
			back_ground[i] = createImage(
					new FilteredImageSource(back_ground[i].getSource(), new CropImageFilter(0, 43 * i, 32, 32)));
		}
		
		//Pulls air blocks from resources folder
		for (int i = 0; i < tileset_air.length; i++) { 
			tileset_air[i] = new ImageIcon("Resources/Air.png").getImage();
			tileset_air[i] = createImage(
					new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 43 * i, 32, 32)));
		}
		
		//Store images, these contain any images that are under the game panel
		tileset_store[0] = new ImageIcon("Resources/Store.png").getImage(); //Pulls store place holder from resources folder
		tileset_store[1] = new ImageIcon("Resources/Heart.png").getImage(); //Pulls heart icon from resources folder
		tileset_store[2] = new ImageIcon("Resources/Coin.png").getImage(); //Pulls coin icon from resources folder
		tileset_store[3] = new ImageIcon("Resources/Point.png").getImage();
		tileset_store[4] = new ImageIcon("Resources/Wave.png").getImage();
		
		//Mob Images
		tileset_mob[0] = new ImageIcon("Resources/MobBlue.png").getImage(); 
		tileset_mob[1] = new ImageIcon("Resources/MobRed.png").getImage();
		tileset_mob[2] = new ImageIcon("Resources/MobGreen.png").getImage();
		
		//Loads the level layout
		save.loadSave(new File("save/mission.td")); 

		for (int i = 0; i < mobs.length; ++i) {
			mobs[i] = new Mob();
		}
	
		try {
			File f = new File("Resources/FB.jpg");
			img = ImageIO.read(f);
			//System.out.println("File " + f.toString());
		} catch (Exception e) {
			System.out.println("Cannot read file: " + e);
		}
	}
	
	//Draws the panel
	public void paintComponent(Graphics g) {
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			define();

			isFirst = false;
		}
		
		//Background
		g.drawImage(img, 0, 0, null);
		
		//Left and right border of the blocks
		g.setColor(new Color(0, 0, 0)); 
		g.drawLine(room.block[0][0].x - 1, 0, room.block[0][0].x - 1,
				room.block[room.worldHeight - 1][0].y + room.blockSize);
		g.drawLine(room.block[0][room.worldWidth - 1].x + room.blockSize, 0,
				room.block[0][room.worldWidth - 1].x + room.blockSize,
				room.block[room.worldHeight - 1][0].y + room.blockSize);

		room.draw(g); // Drawing rooms
		
		//Draws mobs
		for (int i = 0; i < mobs.length; ++i) {
			if (mobs[i].inGame) {
				mobs[i].draw(g);
			}
		}
		store.draw(g); // Drawing the store
		
		//Produces game over screen
		if (health < 1) {
			gameOver(g);
		}
	}
	
	//Handles the game over screen
	public static void gameOver(Graphics g){
		
		isPaused = false;
		Player player = new Player();
		
		g.setColor(new Color(240, 20, 20));
		g.fillRect(0, 0, myWidth, myHeight);
		g.setColor(new Color(255, 255, 255));
		g.setFont(new Font("Courier New", Font.BOLD, 80));
		g.drawString("Game Over", 400, 425);
		g.drawString("Score: " + Screen.score, 400, 500);
		
		FinalScreen fs = new FinalScreen();
	}
	
	// Game Loop
	public void run() { 
		while (isPaused) {
			if (!isFirst && health > 0) {
				room.physics();
				Wave.waveControl();
				for (int i = 0; i < mobs.length; ++i) {
					if (mobs[i].inGame) {
						mobs[i].physics();
					}
				}
			}
			repaint();
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
	}	
}
