<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "com.hacettepe.hello.Account" %>
<%@ page import = "com.hacettepe.hello.Event" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<form action="Servlet1"
		method="post">

	<pre>
	<%
	Account acc =(Account) request.getSession().getAttribute("account");
	out.println();
	out.println("Username : "+acc.getUsername());
	out.println();
	out.println("===================================================");
	out.println("Own Events");
	out.println("===================================================");
	
	out.println(acc.toString());
	out.println();
	out.println("===================================================");
	out.println("Shared Events");
	out.println("===================================================");
	HashMap<String,Event> sharedEvents = (HashMap<String,Event>)this.getServletContext().getAttribute("sharedEvents");
	if(sharedEvents != null && sharedEvents.size() > 0)
	for (String key :sharedEvents.keySet()) {
		String parts[] = key.split(" ");
		out.println(parts[0]);
		
		out.println(sharedEvents.get(key).toStringShare());
	}
	
	%>
	</pre>
	<br>
	<p>
Enter eventID for update or delete: <input type="text" name="eventID" size="50" />
	</p>
	<input type="submit" name="Create New Event" value="Create New Event" /> <input type="submit" name="Edit An Event" value="Edit An Event" />  <input type="submit" name="Delete An Event" value="Delete An Event" /> <input type="submit" name="Share An Event" value="Share An Event" />

	</form>
</body>
</html>