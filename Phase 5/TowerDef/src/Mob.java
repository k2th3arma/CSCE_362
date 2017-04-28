import java.awt.*;

public class Mob extends Rectangle {

	public int mobSize = 64;
	public int mobID = Value.mobAir;
	public int xC, yC;
	public int mobWalk = 0;
	
	public int healthSpace = 3, healthHeight =6;
	public int upward = 0, downward = 1, right = 2, left = 3;
	public int direction = right;

	public double healthTemp;
	public double health;
	
	public boolean inGame = false;
	public boolean hasUpward = false;
	public boolean hasDownward = false;
	public boolean hasLeft = false;
	public boolean hasRight = false;
	
	public Block block;

	//Checks for initial spawning point
	public void spawnMob(int mobID) { 
		for (int y = 0; y < Screen.room.block.length; ++y) {
			if (Screen.room.block[y][0].groundID == Value.groundRoad) {
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, mobSize, mobSize);
				xC = 0;
				yC = y;
			}
		}
		this.mobID = mobID;
		this.healthTemp = width;
		this.health = Value.health;
		inGame = true;
	}
	
	//Removes mobs from the panel
	public void deleteMob() { 
		inGame = false;
		direction = right;
		mobWalk = 0;
		
		Screen.room.block[0][0].getMoney(mobID);
	}
	
	//Checks is mob has 0 HP
	public void checkDeath(){		
		if(healthTemp <= 0.0 ){
			deleteMob();
		}
	}
	
	//Reduces the players health by one when called
	public void looseHealth() { 
		Screen.health -= 1;
	}
	
	//Handles global walking speed
	public int walkFrame = 0, walkSpeed = 15;  
	
	//Controls mob movement
	public void physics() { 
		
		//Forwards progression
		if (walkFrame >= walkSpeed) { 
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
			
			//Translates movement onto the panel
			if (mobWalk == Screen.room.blockSize) { 
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
				
				//Determines direction
				if (!hasUpward) { 
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
					removeMob();
					looseHealth();
				}
				
				//Resets movement
				hasRight = false;    
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
	public void removeMob() { 
		inGame = false;
		direction = right;
		mobWalk = 0;
		
		}
	
	//Controls health lose for mobs
	public void loseHealth(double amount){
		healthTemp = (health - amount)*(healthTemp/health);	
		health -= amount;
		checkDeath();
	}
	
	
	//Ends the game if players reaches 0 HP
	public boolean isDead(){	
		if(inGame){
			return false;
		}
		else{
			return true;
		}
	}

	//Draws icons relative to the mob class
	public void draw(Graphics g) { 
		if (inGame) {
			g.drawImage(Screen.tileset_mob[mobID], x, y, width, height, null);
					
			//Controls image for the health bar
			g.setColor(new Color(180, 50,50)); //Red Background
			g.fillRect(x  , y - (healthSpace + healthHeight), width, healthHeight);
			
			g.setColor(new Color(50, 180,50)); //Green foreground
			g.fillRect(x  , y - (healthSpace + healthHeight), (int)healthTemp, healthHeight);
			
			g.setColor(new Color(0,0,0)); //Black border
			g.drawRect(x  , y - (healthSpace + healthHeight), (int)healthTemp-1, healthHeight-1);
		}
	}


}
