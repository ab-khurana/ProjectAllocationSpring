<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center><br><br>
<form:form action="searchproject" name="formsearch" method="post" modelAttribute="searchAllocateForm" >
Enter Employee ID: <input type="text" name="employeeId"> 
<form:errors path="employeeId" /><br><br><br>
<input type="submit" value="Submit">
</form:form>
</center>
</body>
</html>