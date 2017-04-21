import java.io.*;
import java.util.*;

public class Data {
	
	private String name;
	private String value;
	private int rank;
	private String diff;
	
	static ArrayList<Data> b = new ArrayList<Data>();
	
	static ArrayList<String> scores = tryquery.getScores(Menu.difficulty);
	static ArrayList<String> names = tryquery.getUsernames(Menu.difficulty);
		
	Data d;
	
	public Data(){
		//Placeholder
	}
	
	//Constructor for the leaderboard
	public Data(int rank, String value, String name, String diff){
		this.setRank(rank);
		this.setValue(value);
		this.setName(name);
		this.setDiff(diff);
	}
	
	//Reads the previous leaderboard from the running doc
	public void getFile() throws FileNotFoundException{
		for(int i = 0; i < scores.size();++i){
			d = new Data(i+1, scores.get(i), names.get(i), Menu.difficulty);
			b.add(d);
		}	

	}
	
	//Writes the new leaderboard to the running doc
	public void writeFile() throws FileNotFoundException{

		//tryquery.storeEntry(b);	
		
	}
	
	//Method for pulling the leaderboard 
	public static void getBoard(boolean y){
		if(y){
			for(Data data: b){
				System.out.println(data);
			}
		}
		//Currently just a debug method to verify information
	}
	
	
	//sorts the array list and removed anything after position 5
	public void sort(){
		Data temp = null;
		for(int i = 0; i < b.size(); ++i){
			for(int z = 0; z < b.size(); ++z){
				if(Long.parseLong(b.get(i).getValue()) > Long.parseLong(b.get(z).getValue())){
					temp = b.get(i);
					b.set(i, b.get(z));
					b.set(z, temp);
				}
			}
		}
		for(int i = 0; i < b.size(); ++i){
			d = new Data(i+1, b.get(i).getValue(), b.get(i).getName(), b.get(i).getDiff());
			b.set(i,d);
		}
	}
	
	public void remove(){
		if(b.size() > 4){
			for(int i = b.size()-1; i > 4; --i){
				b.remove(i);
			}
		}
	}
	
	//The "main" method, should be called out of class and initiates all other methods
	public void compile(String name, long score) throws FileNotFoundException{
		getFile();
		
		d = new Data(b.size()+1, String.valueOf(score), name, Menu.difficulty);
		b.add(d);
		
		tryquery.storeEntry(name, String.valueOf(score));
		
		sort();
		remove();
		
		getBoard(false);//debug method
		writeFile();
		
	}
		
	//Needs changed to make the output user friendly
	@Override
	public String toString() {
	    return "  " + this.getRank() 	+ "	" +
	    				this.getValue() + "			" +
	    				this.getName();
	}
	
	
	
	//get methods for the list
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getDiff(){
		return diff;
	}
	public void setDiff(String diff){
		this.diff = diff;
	}	
}
