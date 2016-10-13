<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ page import= "java.io.*" %>
 <%@ page import= "com.cfs.ems.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<%@include file="header.html"%>
<body>
	<table height="100px">
		<tr>
			<td><%@include file="linklist.jsp" %> </td>
		</tr>
	</table>
	<%@include file="footer.html"%>
</body>
</html>