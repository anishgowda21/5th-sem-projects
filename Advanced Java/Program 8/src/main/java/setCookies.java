

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class setCookies
 */
@WebServlet("/setCookies")
public class setCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setCookies() {
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
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		for (int i=1;i<=3;i++) {
			String nm = "TYPE1-CookieNo-"+i;
			String val = "TYPE1-CookieVal-"+i;
			Cookie ck = new Cookie(nm,val);
			response.addCookie(ck);
			nm = "TYPE2-CookieNo-"+i;
			val = "TYPE2-CookieVal-"+i;
			ck = new Cookie(nm,val);
			ck.setMaxAge(3600);
			response.addCookie(ck);
		}
		out.println("<h1> Cookies set</h1><br/><br/>");
		out.println("To view the Cookies <a href='viewCookies'>click here</a>");
		
	}

}
