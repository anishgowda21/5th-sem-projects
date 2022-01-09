import java.sql.*;
public class Q1 {
	public static void main(String args[]) throws ClassNotFoundException{
		String dbname = "vsemdb";
		try {
			Connection con = DBConnection.initialize(dbname);
			String query = "SELECT `no_of_emp` from `department` where `dept_name`= 'cse';";
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(query);
			System.out.print("No of employees in CSE department is : ");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\n\n");
			}
			query = "SELECT `dept_name`,`dept_id` from `department` where `year_established`=2010;";
			rs = smt.executeQuery(query);
			System.out.println("\nInformation of departments establised after 2010\n");
			String header = String.format("%-21s%s", "Name","Dept_ID");
			System.out.println(header);
			System.out.println("----------------------------");
			String f1;
			while(rs.next())
			{
				f1 = String.format("%-21s%s",rs.getString(1),rs.getString(2));
				System.out.println(f1);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
