<%@page import="com.cfs.ems.model.ProjectAllocation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cfs.ems.dao.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- team 3 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Project allocation</title>
</head>
<script language="javascript">
	
	function validateForm() {
		var x = document.forms["updateAllocateform"]["startDate"].value;
		var y = document.forms["updateAllocateform"]["endDate"].value;
		if (y < x) {
			alert("Date must be filled correctly");
			return false;
		}
	}
/* 	function editRecord1(id){
	    var f=document.forms["updateAllocateform"];
	    f.method="post";
	    f.action='updateprojectAllocation/'+id;
	    f.submit();
	} */
</script>
<%-- <%@include file="header.html"%> --%>
<body>

	<%-- <h3>${editProjectAllocation.employeeId}</h3> --%>
	<center>
		<form:form  modelAttribute="projectAll"
			method="post" onsubmit="return validateForm()" name="updateAllocateform" action="updateprojectAllocation">

			Project Allocation Id:<input name = "projectAllocationId" value="${editProjectAllocation.projectAllocationId}" readonly="readonly" /> <br>
			<br> Employee id:<input name="employeeId" value="${editProjectAllocation.employeeId}" readonly="readonly"/> <br>
			Project	name: <select name="projectName">
				<c:forEach var="projectName" items="${projectName}">
					<option value="${projectName}">${projectName}</option>
				</c:forEach>

			</select> <br> <br> <br> <br> Designation: <select
				name="designation" path="designation">
				<option value="developer">Developer</option>
				<option value="designer">Designer</option>
				<option value="tester">Tester</option>
				<option value="Associate Consultant">Associate Consultant</option>
				<option value="Consultant">Consultant</option>
				<option value="Software Engineer">Software Engineer</option>
			</select> <br>
			<br> Start date:<input type="text" name="startDate" required /><form:errors path="startDate" /><br>
			<br> End date:<input type="text" name="endDate"  required  /> <form:errors path="endDate" />
			<input	type="submit" value="Submit" >
		</form:form>

	</center>

</body>
</html>