import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	public static Connection initialize(String dbName) throws ClassNotFoundException{
		String dbDriver = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/";
		String dbUser = "root";
		String dbPasswd = "mysql";
		
		Class.forName(dbDriver);
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl+dbName,dbUser,dbPasswd);
			System.out.println("Connected to db..");
		}catch (Exception e) {
			System.out.println("Cannot Connect to db...");
			e.printStackTrace();
		}
		return con;
	}
}
