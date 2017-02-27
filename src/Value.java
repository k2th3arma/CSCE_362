
public class Value { //holds values for the graphics based on missio.td
	
	//The basics blocks used for mobs and placing towers. Will be transparent
	public static int groundGrass = 0;
	public static int groundRoad = 1;
	
	
	//Transparent blocks used to hold towers and icons onto basic blocks.
	public static int airAir = -1;
	public static int mobAir = -1;
	public static int airCave = 0;
	public static int airTrashCan = 1;
	
	//Mob values
	public static int mobBlue = 0;
	
	//Tower Values
	public static int airTowerLaser = 2;
	public static int towerAtk = 5;
	
	//Rewards for killing mobs. This is based on mobID's
	public static int[] deathReward = {5};
	
	
	
}
