

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class Q4
 */
@WebServlet("/Q4")
public class Q4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Q4() {
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
		int n = Integer.parseInt(request.getParameter("n"));
		String s = request.getParameter("ap");
		PrintWriter out = response.getWriter();
		Connection con = null;
		try {
			con = DBConnection.initialize("vsemdb");
			if (con==null) {
				System.exit(0);
			}
			Statement st = con.createStatement();
			ResultSet rs;
			if(n==1) {//Area
				String query = "SELECT `phone_no` FROM `police_station` WHERE `area` = '"+s+"'";
				rs = st.executeQuery(query);
				out.println("<table border='1'><tr><th>Area</th><th>Phone No</th></tr>");
				while(rs.next()) {
					out.print("<tr><td>"+s+"</td><td>"+rs.getInt(1)+"</td></tr>");
				}
				out.println("</table>");
			}else {// Phone
				String query = "SELECT `area` FROM `police_station` WHERE `phone_no` = "+s;
				rs = st.executeQuery(query);
				out.println("<table border='1'><tr><th>Area</th><th>Phone No</th></tr>");
				while(rs.next()) {
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+s+"</td></tr>");
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
