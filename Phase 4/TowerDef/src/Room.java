import java.awt.*;

public class Room {
	public int worldWidth = 20;			//Blocks in the x direction
	public int worldHeight = 12;		//Blocks in the y direction
	public int blockSize = 64;			//Block size 32x32

	public Block[][] block;

	public Room() {
		define();
	}
	
	//Produces the block pattern.
	public void define() { 
		block = new Block[worldHeight][worldWidth];
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x] = new Block((Screen.myWidth / 2) - ((worldWidth * blockSize) / 2) + (x * blockSize),
						y * blockSize, blockSize, blockSize, Value.groundGrass, Value.airAir);
			}
		}
	}

	public void physics() { 
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x].physics();
			}
		}
	}
	
	//Handles the drawing of the blocks onto the panel
	public void draw(Graphics g) {  
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x].draw(g);
			}
		}
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x].fight(g);
			}
		}
	}
}
