
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class tryquery {
	
	//method gets top 5 usernames based on score, returns ArrayList in order (first, second, etc.)
	//takes in the difficulty as the parameter
	//same structure as getScore
	public static ArrayList<String> getUsernames(String diff) {
		
		ArrayList<String> usernames = new ArrayList<String>();
		try {
			Connection conn = sqltest.getConnection();
			PreparedStatement ps = null;
			ResultSet rs;
			ps = conn.prepareStatement("SELECT Username FROM Leaderboard WHERE Difficulty = ? ORDER BY Score DESC LIMIT 5");
			ps.setString(1, diff);
			rs = ps.executeQuery();
			while (rs.next()) {
				usernames.add(rs.getString("Username"));
			}
			ps.close();
			conn.close();
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
		}
		
		return usernames;
		
	}
	
	public static ArrayList<String> getScores(String diff) {
		
		ArrayList<String> scores = new ArrayList<String>();
		try {
			Connection conn = sqltest.getConnection();
			PreparedStatement ps = null;
			ResultSet rs;
			ps = conn.prepareStatement("SELECT Score FROM Leaderboard WHERE Difficulty = ? ORDER BY Score DESC LIMIT 5");
			ps.setString(1, diff);
			rs = ps.executeQuery();
			while (rs.next()) {
				scores.add(rs.getString("Score"));
			}
			ps.close();
			conn.close();
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
		}
		
		return scores;
		
	}
	
	//method takes ArrayList of data output from game in form of (username, score, difficulty)
	public static void storeEntry(String name, String score) {
		
		try{
			Connection conn = sqltest.getConnection();
			PreparedStatement ps = null;			
			
				ps = conn.prepareStatement("INSERT INTO Leaderboard (Username , Score , Difficulty) VALUES (? , ? , ?)");
				ps.setString(1, name);
				ps.setString(2, score);
				ps.setString(3, Menu.difficulty);			

			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
		}
		
	}
	
}
