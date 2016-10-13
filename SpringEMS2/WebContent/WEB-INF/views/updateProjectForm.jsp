<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<font size="5">Project Allocation form:</font>
	<br>
	<br>
	<form action="updateProAllocate" method="post">
	<%-- <form:form method="POST" action="updateProAllocate"	modelAttribute="projectall"> --%>
		Project Allocation Id: <input type="text" name="projectAllocationId" value="">
		<br>
		<br>
		<input type="submit" value="Submit">
		</form>
</body>
</html>