import java.io.*;
import java.util.*;

public class Data {
	
	private String inFile = "leaderBoard.txt";
	private String outFile = "leaderBoard.txt";
	
	private final static int count = 15;
	
	private String name;
	private String value;
	private int rank;
	
	static String[][] board = new String[3][count];
	
	static ArrayList<Data> b = new ArrayList<Data>();
	
	Data d;
	
	public Data(){
		//Placeholder
	}
	
	//Constructor for the leaderboard
	public Data(int rank, String value, String name){
		this.setRank(rank);
		this.setValue(value);
		this.setName(name);
	}
	
	//Reads the previous leaderboard from the running doc
	public void getFile() throws FileNotFoundException{
		Scanner read = new Scanner(new File("Resources/leaderBoard.txt"));
				
		while(read.hasNext()){
			d = new Data(read.nextInt(), read.next(), read.next());
			b.add(d);

		}		
		read.close();
	}
	
	//Writes the new leaderboard to the running doc
	public void writeFile() throws FileNotFoundException{
		PrintWriter write = new PrintWriter(new File("Resources/leaderBoard.txt"));
		for(Data data: b){
			write.println(data);
		}
		write.close();
	}
	
	//Method for pulling the leaderboard 
	public static void getBoard(){
		//Should have have a form like setup for the data to be sent to final screen for presentation
		for(Data data: b){
			System.out.println(data);
		}
		//Currently just a debug method to verify information
	}
	
	
	//sorts the array list and removed anything after position 10
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
			d = new Data(i+1, b.get(i).getValue(), b.get(i).getName());
			b.set(i,d);
		}
	}
	
	
	//The "main" method, should be called out of class and initiates all other methods
	public void compile(String name, long score) throws FileNotFoundException{
		getFile();
		
		d = new Data(b.size()+1, String.valueOf(score), name);
		b.add(d);
		
		sort();
		
		getBoard();
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
	
	
}
