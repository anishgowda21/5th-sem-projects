<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie ticket price</title>
</head>
<body>

	<form action="ticketPrice.jsp" method="POST">
		<h3>Enter Name and age</h3>
		<fieldset>
			Name : <input type="text" required name="name"><br/><br/>
			Age : <input type="number" required name = "age"><br/>
		</fieldset>
		<button type="submit" name="sub_btn">Submit</button>
	</form>
	
	<%
		if(request.getParameter("sub_btn") != null){
			int price;
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			if(age > 62){
				price = 50;
			} else if(age < 10){
				price = 30;
			} else {
				price = 80;
			}
			
			out.println("Name : "+name+", Age : "+age+", Ticket Price : "+price);
		}
	%>

</body>
</html>