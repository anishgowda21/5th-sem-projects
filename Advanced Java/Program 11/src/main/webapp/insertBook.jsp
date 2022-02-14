<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "java.sql.*" %>
 <%@ page import = "q11.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Book</title>
</head>
<body>
	<form action="insertBook.jsp" method="POST">
		<h3>Please insert the book details</h3>
		<fieldset>
			Book No : <input type="number" required name="book_no"><br/><br/>
			Title : <input type="text" required name = "title"><br/><br/>
			Author : <input type="text" required name = "author"><br/><br/>
			Publication : <input type="text" required name = "publication"><br/><br/>
			Price : <input type="number" required name="price"><br/><br/>
		</fieldset>
		<button type="submit" name="insert_btn">Insert</button>
	</form>
	<br/><br/>
	
	<%
		if(request.getParameter("insert_btn")!=null){
			int book_no = Integer.parseInt(request.getParameter("book_no").trim());
			String title = request.getParameter("title").trim();
			String author = request.getParameter("author").trim();
			String publication = request.getParameter("publication").trim();
			int price = Integer.parseInt(request.getParameter("price").trim());
			try{
				Connection con = DBConnection.initialize("vsemdb");
				if(con == null){
					System.exit(0);
				}
				String query = "INSERT INTO `books` VALUES (?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1,book_no);
				ps.setString(2,title);
				ps.setString(3,author);
				ps.setString(4,publication);
				ps.setInt(5,price);
				ps.execute();
				ps.close();
				out.println("<h2>Inserted Successfully...</h2>");
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	%>

</body>
</html>