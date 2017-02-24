import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.*;

public class Screen extends JPanel implements Runnable {
	public Thread thread = new Thread(this);

	public static Image[] back_ground = new Image[100];
	public static Image[] tileset_air = new Image[100];
	public static Image[] tileset_store = new Image[100];
	public static Image[] tileset_mob = new Image[100];

	public static int myWidth, myHeight;
	public static int money = 100, health = 100;

	public static boolean isFirst = true;
	// public static boolean isDead = false;
	public static boolean isDebug = false;

	public static Room room = new Room();
	public static Save save = new Save();
	public static Store store = new Store();
	public static Mob[] mobs = new Mob[100];

	public static Point mse = new Point(0, 0);

	public Screen(Frame frame) {

		frame.addMouseListener(new KeyHandle());
		frame.addMouseMotionListener(new KeyHandle());

		thread.start();

	}

	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();

		for (int i = 0; i < back_ground.length; i++) {
			back_ground[i] = new ImageIcon("Resources/Test.png").getImage();
			back_ground[i] = createImage(
					new FilteredImageSource(back_ground[i].getSource(), new CropImageFilter(0, 43 * i, 32, 32)));
		}
		for (int i = 0; i < tileset_air.length; i++) {
			tileset_air[i] = new ImageIcon("Resources/Air.png").getImage();
			tileset_air[i] = createImage(
					new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 43 * i, 32, 32)));
		}

		tileset_store[0] = new ImageIcon("Resources/Store.png").getImage();
		tileset_store[1] = new ImageIcon("Resources/Heart.png").getImage();
		tileset_store[2] = new ImageIcon("Resources/Coin.png").getImage();

		tileset_mob[0] = new ImageIcon("Resources/Mob.png").getImage();

		save.loadSave(new File("save/mission.td"));

		for (int i = 0; i < mobs.length; ++i) {
			mobs[i] = new Mob();
		}

	}

	public void paintComponent(Graphics g) {
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			define();

			isFirst = false;
		}

		g.setColor(new Color(50, 50, 50));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0, 0, 0));
		g.drawLine(room.block[0][0].x - 1, 0, room.block[0][0].x - 1,
				room.block[room.worldHeight - 1][0].y + room.blockSize);
		g.drawLine(room.block[0][room.worldWidth - 1].x + room.blockSize, 0,
				room.block[0][room.worldWidth - 1].x + room.blockSize,
				room.block[room.worldHeight - 1][0].y + room.blockSize);

		room.draw(g); // Drawing rooms

		for (int i = 0; i < mobs.length; ++i) {
			if (mobs[i].inGame) {
				mobs[i].draw(g);
			}
		}

		store.draw(g); // Drawing the store

		if (health < 1) {
			g.setColor(new Color(240, 20, 20));
			g.fillRect(0, 0, myWidth, myHeight);
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Courier New", Font.BOLD, 14));
			g.drawString("Game Over", 10, 10);
		}
	}

	public int spawnTime = 2600, spawnFrame = 0;

	public void mobSpanwer() { // Spawns mobs
		if (spawnFrame >= spawnTime) {
			for (int i = 0; i < mobs.length; ++i) {
				if (!mobs[i].inGame) {
					mobs[i].spawnMob(Value.mobBlue);
					break;
				}
			}

			spawnFrame = 0;
		} else {
			spawnFrame += 1;
		}
	}

	public void run() { // Game Loop
		while (true) {
			if (!isFirst && health > 0) {
				room.physics();
				mobSpanwer();
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
