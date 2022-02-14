<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "q11.DBConnection" %>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Retrieve book</title>
</head>
<body>
	<form action="retrieveBook.jsp" method="POST">
		<h3>Enter Book title to retrieve its details</h3>
		<fieldset>
			Title : <input type="text" required name="title"><br/>
		</fieldset>
		<button type="submit" name="ret_btn">Retrieve</button>
	</form>
	
	<%
		if(request.getParameter("ret_btn") != null){
			String title = request.getParameter("title").trim();
			try{
				Connection con = DBConnection.initialize("vsemdb");
				String query = "SELECT * FROM `books` WHERE `title` = ? ";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1,title);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next() == false) 
				{ 
					out.println("<h2>No results found</h2>"); 
					
				} else 
					{ 
						do { 
							out.println("<h2>Book No: "+rs.getInt(1)+"<br/>");
							out.println("Title: "+rs.getString(2)+"<br/>");
							out.println("Author: "+rs.getString(3)+"<br/>");
							out.println("Publication: "+rs.getString(4)+"<br/>");
							out.println("Price: "+rs.getInt(5)+"</h2>");
							} while (rs.next()); 
					}

				/** This can also be used but in case of empty set no message will be printed
				while(rs.next()){
					out.println("<h2>Book No: "+rs.getInt(1)+"<br/>");
					out.println("Title: "+rs.getString(2)+"<br/>");
					out.println("Author: "+rs.getString(3)+"<br/>");
					out.println("Publication: "+rs.getString(4)+"<br/>");
					out.println("Price: "+rs.getInt(5)+"</h2>");
				}**/
				
				ps.close();
				con.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	%>
	
</body>
</html>