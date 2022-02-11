

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Q7
 */
@WebServlet("/Q7")
public class Q7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Q7() {
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
		String fac_id = request.getParameter("fac_id");
		String sub_id = request.getParameter("sub_id");
		String sub_name = request.getParameter("sub_name");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DBConnection.initialize("vsemdb");
			if (con == null) {
				System.exit(0);
			}
			String query = "UPDATE `subject` SET `sub_id`= ? ,`sub_name` = ? WHERE fac_id = ?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, sub_id);
			ps.setString(2, sub_name);
			ps.setString(3, fac_id);
			int n = ps.executeUpdate();
			out.println("<h2>"+n+" rows were updated....</h2><br/>");
			query = "SELECT * FROM `subject`;";
			ResultSet rs = ps.executeQuery(query);
			out.println("<table border='1'><tr><th>Subject ID</th><th>Subject Name</th><th>Faculty ID</th></tr>");
			while(rs.next()) {
				out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
			}
			out.println("</table>");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
