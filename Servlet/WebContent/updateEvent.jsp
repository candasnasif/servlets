<%@page import="java.util.Calendar"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "com.hacettepe.hello.Account" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>




<!DOCTYPE html >



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<FORM action="Servlet1" method="POST">
<%
Account acc =(Account) request.getSession().getAttribute("account");
out.println();
out.println("Username : "+acc.getUsername());
%>



	<p>
		Title: <input type="text" name="TitleU" size="50" />
	</p>

	<br>

	<p>
		Location: <input type="text" name="LocationU" size="50" height="150" />
	</p>

	<br>

	<p>
		Description: <input type="text" name="DescriptionU" size="50"
			height="150" />
	</p>

	<br>

	<p>

		Date: <input id="date" type="date" name="dateTypeU" size="50"
			height="150">

	</p>

	<br>

	<p>
		Beginning Hour: <input type="time" name="BeginHoursU" /> End Hour: <input
			type="time" name="EndHoursU" />
	</p>

	<input type="submit" name="Update Event" value="Update Event" />



</FORM>
	<br>






</body>
</html>