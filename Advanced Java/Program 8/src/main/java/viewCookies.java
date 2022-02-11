

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class viewCookies
 */
@WebServlet("/viewCookies")
public class viewCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewCookies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		Cookie[] ck = request.getCookies();
		
		if (ck == null) {
			out.println("No Cookies Found......");
		}else {
			out.println("<h3>"+ck.length+" Cookies found.......</h3><br><br>");
			out.println("<table border='1'><tr><th>Cookie name</th><th>Cookie Value</th><th>Cookie MaxAge</th></tr>");
			for(Cookie i:ck) {
				out.println("<tr><td>"+i.getName()+"</td><td>"+i.getValue()+"</td><td>"+i.getMaxAge()+"</td></tr>");
			}
			out.println("</table>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
