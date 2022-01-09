import java.sql.*;
public class Q2 {
	public static void main(String args[]) throws ClassNotFoundException{
		String dbName = "vsemdb";
		String f1, f2, f3, f4;
		String header = String.format("%-21s%-21s%-21s%s", "USN", "Name", "Department", "CGPA");
		String sepLine = "--------------------------------------------------------------------------------------";
		try {
			Connection con = DBConnection.initialize(dbName);
			String query = "SELECT * from `student` where `cgpa`<9;";
			System.out.println("Displaying Records of students with CGPA < 9:\n");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println(header);
			System.out.println(sepLine);
			while (rs.next()) {
				f1 = String.format("%-21s", rs.getString(1));
				System.out.print(f1);
				f2 = String.format("%-21s", rs.getString(2));
				System.out.print(f2);
				f3 = String.format("%-21s", rs.getString(3));
				System.out.print(f3);
				f4 = String.format("%-21s", rs.getString(4));
				System.out.println(f4);
			} // end while
			query = "SELECT * from `student`;";
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(query);
			System.out.println("\n\nUpdated: Johns CGPA from 8.5 to 9.4\n\n");
			System.out.println(header);
			System.out.println(sepLine);
			while(rs.next()) {
				if(rs.getString(2).equals("John")){
					rs.updateDouble(4, 9.4);
					rs.updateRow();
				}
				f1 = String.format("%-21s", rs.getString(1));
				System.out.print(f1);
				f2 = String.format("%-21s", rs.getString(2));
				System.out.print(f2);
				f3 = String.format("%-21s", rs.getString(3));
				System.out.print(f3);
				f4 = String.format("%-21s", rs.getString(4));
				System.out.println(f4);
				
			}// end while
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
