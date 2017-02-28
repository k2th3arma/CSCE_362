import java.awt.*;

public class Block extends Rectangle {
	public int groundID;
	public int airID;
	public int shotMob = -1;
	public int loseTime = 100, loseFrame = 0; 	//Handles Hp loss rate for mobs, Global setting
	public int towerSquareSize = 250;			//Global tower range
	
	public boolean shooting = false;
	
	public Rectangle towerSquare;
	
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		towerSquare = new Rectangle(x - (towerSquareSize/2), y - (towerSquareSize/2), width + (towerSquareSize*2), height+ (towerSquareSize*2));
		this.groundID = groundID;
		this.airID = airID;
	}
	
	//Places air blocks
	public void draw(Graphics g) {
		g.drawImage(Screen.back_ground[groundID], x, y, width, height, null);
		if (airID != Value.airAir) {
			g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);	
		}	
	}
	
	//Controls the shooting aspect of the game
	public void physics(){  
		if(shotMob != -1 && towerSquare.intersects(Screen.mobs[shotMob])){
			shooting = true;
		}
		else{
			shooting = false;
		}
		if(!shooting){
			if(airID == Value.airTowerLaser){		
				for(int i =0; i<Screen.mobs.length;++i){
					if(Screen.mobs[i].inGame){
						if(towerSquare.intersects(Screen.mobs[i])){
							shooting = true;
							shotMob = i;
						}
					}
				}
			}
		}
		if(shooting){ 
			if(loseFrame >= loseTime){
				Screen.mobs[shotMob].loseHealth(damage(Value.towerAtk, Value.tMob[Screen.mobs[shotMob].mobID]));
				loseFrame =0;
			}
			else{
				loseFrame += 1;
			}
			if(Screen.mobs[shotMob].isDead()){ //Needs a checksum for kills. One kills is giving multiple awards
				getMoney(Screen.mobs[shotMob].mobID);
				shooting = false;
				shotMob = -1;
			}			
		}
	}
	
	//Handles damage against mobs health, always greater than 0
	public int damage(int x, int y){
		int z = x - y;
		if(z <= 0){
			return 1;
		}
		else{
			return z;			
		}		
	}
	
	//Handles money and score distribution after mob is removed
	public void getMoney(int mobID){ 
		Screen.money += Value.deathReward[mobID];
		Screen.score += Value.deathReward[mobID];
	}
	
	//Draws graphics related to shooting
	public void fight(Graphics g){ 
		if(Screen.isDebug){ //Debug mode to check tower range. Produces black box equal to global range
			if(airID == Value.airTowerLaser){
				g.drawRect(towerSquare.x,towerSquare.y,towerSquare.width,towerSquare.height);
			}
		}
		if(shooting){		//Controls the projectile for the tower, currently a global setting 
			g.setColor(new Color(250,250,250));
			g.drawLine(x +(width/2), y + (height/2), Screen.mobs[shotMob].x + (Screen.mobs[shotMob].width/2), Screen.mobs[shotMob].y + (Screen.mobs[shotMob].height/2));
		}		
	}
}
