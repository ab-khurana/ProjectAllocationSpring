<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%@ page import="java.util.*"%>
<script language="javascript">
	
	function validateForm() {
		var x = document.forms["allocateForm"]["startDate"].value;
		var y = document.forms["allocateForm"]["endDate"].value;
		if (y < x) {
			alert("Date must be filled correctly");
			return false;
		}
	}
</script>
<body>
	<center>
		<br> <br> <br> <br> <font size="5" color="Red">Project
			Allocations:</font> <br> <br>
		<form:form action="allocateEmployee" method="post" onsubmit="return validateForm()" modelAttribute="allocateForm" name = "allocateForm">
			
			Project name: <select name="projectName">
			
								<c:forEach var="projectName" items="${projectName}">
									<option value="${projectName}">${projectName}</option>
								</c:forEach>
				
						</select><form:errors path="projectName" /> <br> <br> 
						
						
			Designation: <select name="designation">
							<option value="designer">Designer</option>
							<option value="developer">Developer</option>
							<option value="tester">Tester</option>
							<option value="Associate Consultant">Associate Consultant</option>
							<option value="Consultant">Consultant</option>
							<option value="Software Engineer">Software Engineer</option>
						</select><form:errors path="designation" /><br> <br> <br> 
			
			
			Employee name: <select name="employeeName">
								<c:forEach var="employeeName" items="${employeeName}">
									<option value="${employeeName}">${employeeName}</option>
								</c:forEach>
							</select><form:errors path="employeeName" /> <br> <br> 
	
	
			Start Date(YYYY-MM-DD) : <input  name="startDate" placeholder="yyyy-MM-dd"/>
			<form:errors path="startDate" /><br> <br> 
			<br>
			End Date(YYYY-MM-DD) :<input  name="endDate" placeholder="yyyy-MM-dd"/> <form:errors path="endDate" /><br><br> 
			<br>
			<input type="submit" value="Submit">

		</form:form><br>
		 <form action="listOfProjectAllocation" method="get">
		
								<input type="submit" value ="list projects">

								</form>
	</center>
</body>
</html>