import java.sql.*;
import java.util.*;
public class Q3 {
	public static void main(String args[]) throws ClassNotFoundException{
		String dbName = "vsemdb";
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DBConnection.initialize(dbName);
			if (con == null)
			{
				System.exit(0);
			}
			con.setAutoCommit(false);
			Q3 obj = new Q3();
			
			while (true){
				System.out.println("1.Create Account\n2.Display Account Details\n3.Transact\nElse Exit\nEnter Your Choice");
				int opt = sc.nextInt();
				int f=0;
				switch(opt) {
				case 1: obj.createAccount(con);
						break;
				case 2:obj.displayDetails(con);
						break;
				case 3:
					System.out.println("Transact, Please Enter your ID:");
					int id = sc.nextInt();
					System.out.println("1.WithDraw\n2.Deposit\n3.Display Account Details\nElse Main Menu\nEnter Your Choice");
					int temp = sc.nextInt();
					switch(temp) {
					case 1:obj.withdrawMoney(con, id);
							break;
					case 2:obj.depositMoney(con, id);
							break;
					case 3:obj.accDetails(con, id);
							break;
					default: break;
					}
					break;
				default: f=1; 
					break;
				}
				if(f==1) {
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Function to create Account
	public void createAccount(Connection con) {
		Scanner scan = new Scanner(System.in);
		PreparedStatement ps;
		System.out.println("Enter id:");
		int id = scan.nextInt();
		System.out.println("Enter Name:");
		scan.nextLine();
		String name = scan.nextLine();
		System.out.println("Enter balance: ");
		int balance = scan.nextInt();
		String query = "INSERT INTO `Bank` (`id`,`name`,`balance`) VALUES (?,?,?);";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, balance);
			ps.execute();
			con.commit();
			//scan.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Function to display all account details
	public void displayDetails(Connection con) {
		Statement st;
		ResultSet rs;
		String header = String.format("%-21s%-21s%s", "ID","Name","Balence");
		System.out.println(header);
		System.out.println("----------------------------------------------");
		String query = "SELECT * FROM Bank;";
		try {
			st = con.createStatement();
			rs=st.executeQuery(query);
			String f1;
			while (rs.next()) {
				f1 = String.format("%-21s%-21s%s", rs.getString(1), rs.getString(2), rs.getString(3));
				System.out.println(f1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Function to withdraw money
	public void withdrawMoney(Connection con,int id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount to withdraw");
		int amt = sc.nextInt();
		String query = "SELECT `balance` FROM `Bank` WHERE `id` = " + id;
		try {
			Savepoint sp = con.setSavepoint();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			int bal = 0;
			while(rs.next()) {
				bal = rs.getInt(1);
			}
			bal -= amt;
			System.out.println("Balence After withdrawal "+bal);
			query = "UPDATE `Bank` SET `balance` = "+bal+" WHERE `id` = "+id;
			st.executeUpdate(query);
			if(bal < 0) {
				con.rollback(sp);
				System.out.println("Insufficient Balence");
				con.releaseSavepoint(sp);
				con.commit();
			}else {
				con.releaseSavepoint(sp);
				con.commit();
				System.out.println("Withdrawel of RS"+amt+" Successfull availabale balence RS"+bal);
			}
			//sc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Function to deposit the money
	public void depositMoney(Connection con,int id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount to be deposited:");
		int amt = sc.nextInt();
		int bal = 0;
		String query = "SELECT `balance` FROM `Bank` WHERE `id` = "+id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				bal = rs.getInt(1);
			}
			bal+=amt;
			query = "UPDATE `Bank` SET `balance` = "+bal+" WHERE `id` = "+id;
			st.executeUpdate(query);
			con.commit();
			System.out.println("Deposit SuccessFull Balence:"+bal);
			//sc.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Function to display individual account details
	public void accDetails(Connection con,int id) {
		String query = "SELECT * FROM `Bank` WHERE `id` = "+id;
		String header = String.format("%-21s%-21s%s", "ID","Name","Balence");
		System.out.println(header);
		System.out.println("----------------------------------------------");
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			String f1;
			while(rs.next()) {
				f1 = String.format("%-21s%-21s%s", rs.getString(1),rs.getString(2),rs.getString(3));
				System.out.println(f1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
