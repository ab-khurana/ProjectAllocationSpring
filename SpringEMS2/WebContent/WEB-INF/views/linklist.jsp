<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Map"%>
<%@page import="com.cfs.ems.model.*"%>
<%@page import= "org.springframework.web.servlet.ModelAndView"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home Page</title>
</head>
<body><%-- <%@include file="header.html"%> --%>

	<table style="table-layout: auto;width: 15%;" border="2"
		cellpadding=5 cellspacing=5>
		</div>

		<tr>
			<td width="500%" height="500px" align="center">

				
				<div align="left">
					<p>
					<%-- 	<font color="grey" style=""> Welcome admin  	${status} + " "		+ " with Employee ID as "
									
						 ! --%>
						 <font color = "red">Hello <% out.print(request.getSession().getAttribute("empName")); %> Welcome to EMS!</font><br> 
					<%-- <p>
						The Total Employees are
						<%	out.print(request.getSession().getAttribute("employeeCount"));	%>
						! --%>
					<DIV align="LEFT">
						<P>
							<!-- DASHBOARD<br> <button><a href="dashboard.jsp">Dashboard</a></button><br>
							<br> -->
						<P>
							<a href="view.html">EMPLOYEE</a><br>
							
							
							<!--  <button><br> <a href="listemployee.com">list employee</a><br></button>
							<button><a href="searchemployee.com">search employee</a><br></button>
							<button><a href="searchemployee.com">DELETE employee</a><br></button> -->
						<p>	PROJECT
							
							<form action='CreateProject' method='post'>
							<input type='submit' value='Create Project'>
							</form>
	
							<form action='DeleteProject' method='post'>
							<input type='submit' value='Delete Project'>
							</form>
		
							<form action='EditProject' method='post'>
							<input type='submit' value='Edit Project'>
							</form>

							<form action='SearchProject' method='post'>
							<input type='submit' value='Search Project'>
							</form>
							
							<form action='PrintAllServlet' method='post'>
							<input type='submit' value='List of Projects'>
							</form>
							
							<!-- PROJECT<br> <button><a href="createproject.com">create project</a><br></button>
							<button><a href="listproject.com">list project</a><br> </button>
							<button><a href="searhproject.com">search project</a><br></button> -->
						<p>
							<!-- CLIENT<br> <a href="createclient.com">create client</a><br>
							<a href="listclient.com">list client</a><br> <a
								href="searhclient.com">search client</a><br> -->
						<p>
							PROJECT ALLOCATION<br>
							<form action="projectAllocateform" method="get"> 
							<input type="submit" value="allocate project">
							</form> 
							
								
								 <!-- <a href="searchproject.com">search project</a><br> -->
								 
								 <form action="listOfProjectAllocation" method="get">
		
								<input type="submit" value ="list  and edit projects">

								</form>
								
								<!-- <a href="listOfProjects.jsp">list  and edit projects</a><br> -->
								<!-- <a href="loginpage.jsp">remove employee from project</a><br> -->
						<p>
							<!-- LEAVE<br> <a href="apply leave.com">apply leave</a><br>
							<a href="approval.com">approval</a><br> -->
							<a href="Logout">LOGOUT</a>
					</div>
		</tr>
	</table>
	</div>
	<%-- <%@include file="footer.html"%> --%>
</body>
</html>