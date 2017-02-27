import java.awt.*;

public class Mob extends Rectangle {

	public int mobSize = 64;
	public int mobID = Value.mobAir;
	public int xC, yC;
	public int mobWalk = 0;
	
	public int healthSpace = 3, healthHeight =6;
	public int upward = 0, downward = 1, right = 2, left = 3;
	public int direction = right;

	public int health;
	
	public boolean inGame = false;
	public boolean hasUpward = false;
	public boolean hasDownward = false;
	public boolean hasLeft = false;
	public boolean hasRight = false;
	
	public Block block;

	public Mob() {

	}

	public void spawnMob(int mobID) { //checks for initial spawning point
		for (int y = 0; y < Screen.room.block.length; ++y) {
			if (Screen.room.block[y][0].groundID == Value.groundRoad) {
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, mobSize, mobSize);
				xC = 0;
				yC = y;
			}

		}

		this.mobID = mobID;
		this.health = mobSize;

		inGame = true;
	}

	public void deleteMob() { //Removes mobs from the panel
	
		inGame = false;
		direction = right;
		mobWalk = 0;
		

	}

	public void looseHealth() { //Reduces the players health by one when called
		Screen.health -= 1;

	}

	public int walkFrame = 0, walkSpeed = 15;  //handles global walking speed

	public void physics() { // Controls mob movement
		
		if (walkFrame >= walkSpeed) { //Forwards progression
			if (direction == right) {
				x += 1;
			} else if (direction == upward) {
				y -= 1;
			} else if (direction == downward) {
				y += 1;
			} else if (direction == left) {
				x -= 1;
			}

			mobWalk += 1;

			if (mobWalk == Screen.room.blockSize) { //Translates movement onto the panel
				if (direction == right) {
					xC += 1;
					hasRight = true;
				} else if (direction == upward) {
					yC -= 1;
					hasUpward = true;
				} else if (direction == downward) {
					yC += 1;
					hasDownward = true;
				} else if (direction == left) {
					xC -= 1;
					hasLeft = true;
				}

				if (!hasUpward) { // Determines direction
					try {
						if (Screen.room.block[yC + 1][xC].groundID == Value.groundRoad) {
							direction = downward;
						}
					} catch (Exception e) {
					}
				}
				if (!hasDownward) {
					try {
						if (Screen.room.block[yC - 1][xC].groundID == Value.groundRoad) {
							direction = upward;
						}
					} catch (Exception e) {
					}
				}
				if (!hasLeft) {
					try {
						if (Screen.room.block[yC][xC + 1].groundID == Value.groundRoad) {
							direction = right;
						}
					} catch (Exception e) {
					}
				}
				if (!hasRight) {
					try {
						if (Screen.room.block[yC][xC - 1].groundID == Value.groundRoad) {
							direction = left;
						}
					} catch (Exception e) {
					}
				}

				if (Screen.room.block[yC][xC].airID == Value.airCave) {
					deleteMob();
					looseHealth();
				}

				hasRight = false;    //Resets movement
				hasLeft = false;
				hasUpward = false;
				hasDownward = false;
				mobWalk = 0;
			}

			walkFrame = 0;
		} else {
			walkFrame += 1;
		}

	}
	
	public void loseHealth(int amount){ //Controls health lose for mobs
		
		health -= amount;
		
		checkDeath();

	}
	
	public void checkDeath(){		//Checks is mob has 0 HP
		if(health <= 0 ){
			deleteMob();
		}
	}
	public boolean isDead(){	//Ends the game if players reaches 0 HP
		if(inGame){
			return false;
		}
		else{
			return true;
		}
	}

	public void draw(Graphics g) { //Draws icons relative to the mob class
		if (inGame) {
			
			g.drawImage(Screen.tileset_mob[mobID], x, y, width, height, null);
			
			
			
			g.setColor(new Color(180, 50,50));
			g.fillRect(x  , y - (healthSpace + healthHeight), width, healthHeight);
			
			g.setColor(new Color(50, 180,50));
			g.fillRect(x  , y - (healthSpace + healthHeight), health, healthHeight);
			
			g.setColor(new Color(0,0,0));
			g.drawRect(x  , y - (healthSpace + healthHeight), health-1, healthHeight-1);
		}
	}

}
