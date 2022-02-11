<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
		String user = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		
		if(user.equals("admin") && passwd.equals("admin@123"))
		{
			System.out.println("Welcome "+user);
			session.setAttribute("user",user);
			request.getRequestDispatcher("hello.jsp").forward(request,response);
		}else{
			System.out.println("Not valid");
			response.sendRedirect("register.html");
		}
	%>
</body>
</html>