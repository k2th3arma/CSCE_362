import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqltest {
	
	protected static final String url = "jdbc:mysql://cse.unl.edu/dvnguyen";
	protected static final String username = "dvnguyen";
	protected static final String password = "H_L9up";
	
	static public Connection getConnection(){

		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url , username , password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			
		}
		
		return conn;
	}
}



