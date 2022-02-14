<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Grade</title>
</head>
<body>
	<form action="grade.jsp" method = "POST">
		<h2>Enter Marks of 5 Subjects</h2>
		<fieldset>
			Student Name : <input type="text" required name="std_name"><br/><br/> 
			Subject 1 marks : <input type="number" required name="sub_1"><br/><br/>
			Subject 2 marks : <input type="number" required name="sub_2"><br/><br/>
			Subject 3 marks : <input type="number" required name="sub_3"><br/><br/>
			Subject 4 marks : <input type="number" required name="sub_4"><br/><br/>
			Subject 5 marks : <input type="number" required name="sub_5"><br/><br/>
		</fieldset>
		<button type="submit" name="grade_btn">Get Grades</button>
	</form>
	
	<%
		if(request.getParameter("grade_btn") != null){
			String name = request.getParameter("std_name");
			int sub_1 = Integer.parseInt(request.getParameter("sub_1"));
			int sub_2 = Integer.parseInt(request.getParameter("sub_2"));
			int sub_3 = Integer.parseInt(request.getParameter("sub_3"));
			int sub_4 = Integer.parseInt(request.getParameter("sub_4"));
			int sub_5 = Integer.parseInt(request.getParameter("sub_5"));
			
			int total = sub_1+sub_2+sub_3+sub_4+sub_5;
			
			double avg = total / 5.0;
			
			String grade = null;
			
			if(avg > 90){
				grade = "S";
			} else if( avg >= 80 ){
				grade = "A";
			} else if( avg >= 70 ){
				grade = "B";
			} else if( avg >= 60 ){
				grade = "C";
			} else if( avg >= 50 ){
				grade = "D";
			} else{
				grade = "Fail";
			}
			
			out.println("<h1>Student Grade Report</h1>");
			out.println("<h3>Name: " + name + "<br>");
			out.println("<h3>Total marks: " + total + "<br>");
			out.println("<h3>Grade: " + grade + "</h3>");
		}
	%>
</body>
</html>