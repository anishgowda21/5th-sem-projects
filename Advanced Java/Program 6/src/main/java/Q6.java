

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Q6
 */
@WebServlet("/Q6")
public class Q6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Q6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emp_id = request.getParameter("emp_id");
		String emp_name = request.getParameter("emp_name");
		String emp_addr = request.getParameter("emp_addr");
		Date emp_dob = Date.valueOf(request.getParameter("emp_dob"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			Connection con = DBConnection.initialize("vsemdb");
			if (con == null) {
				System.exit(0);
			}
			String query = "INSERT INTO `employee` VALUES(?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, emp_id);
			ps.setString(2, emp_name);
			ps.setString(3, emp_addr);
			ps.setDate(4, emp_dob);
			ps.execute();
			System.out.println("Inserted into the database");
			
			query = "SELECT * FROM `employee`;";
			ResultSet rs = ps.executeQuery(query);
			out.println("<table border='1'><tr><th>Emp ID</th><th>Emp Name</th><th>Emp Address</th><th>Emp DOB</th></tr>");
			while(rs.next()) {
				out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
			}
			out.println("</table>");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
