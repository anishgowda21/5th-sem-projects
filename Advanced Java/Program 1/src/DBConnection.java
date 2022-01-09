import java.sql.*;

public class DBConnection {
	protected static Connection initialize(String dbName) throws ClassNotFoundException{
		String dbDriver = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/";
		String dbUser = "root";
		String dbPassword = "mysql";
		
		Class.forName(dbDriver);
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(dbURL+dbName,dbUser,dbPassword);
			System.out.println("Connection to DB Successfull...");
		}catch(Exception e) {
			System.out.println("Couldn't Connect to DB");
			e.printStackTrace();
		}
		
		return con;
	}
}
