<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Of Project Allocation</title>
</head>
<script language="javascript">
function deleteRecord(id){
    var f=document.form;
    f.method="post";
    f.action='deletes/'+id;
    f.submit();
}
function editRecord1(id){
    var f=document.form;
    f.method="get";
    f.action='editallocate/'+id;
    f.submit();
}
</script>
<body>
	<%@include file="header.html"%>
	<form method="get" name="form">
		<table>
			<tr>
				<td><%@include file="linklist.jsp"%></td>
				<td>
					<%-- <%List list = (LinkedList)"${list}"; %> --%>
					<a href="searchbyemployee">Search by employee ID</a><br><br>
					<a href="searchbyproject">Search by Project Name</a><br><br>
					<table border=black>
						<tr>
							<th>Project Allocation ID</th>
							<th>Project ID</th>
							<th>Project Name</th>
							<th>Employee ID</th>
							<th>Designation</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Status</th>
						</tr>
						<c:forEach items="${list}" var="object" varStatus="status">
							<tr>
								<td><c:out value="${object.projectAllocationId}">
									</c:out></td>

								<td><c:out value="${object.projectId}"></c:out></td>
								<td><c:out value="${object.projectName}"></c:out></td>
								<td><c:out value="${object.employeeId}"></c:out></td>
								<td><c:out value="${object.designation}"></c:out></td>
								<td><c:out value="${object.startDate}"></c:out></td>
								<td><c:out value="${object.endDate}"></c:out></td>
								<td><c:out value="${object.status}"></c:out></td>
								<td><input name="edit" type='button' value='EDIT'
									onclick="editRecord1(${object.projectAllocationId});"></td>
								<td><input name="delete" type='button' value='DELETE'
									onclick="deleteRecord(${object.projectAllocationId});"></td>
							</tr>
						</c:forEach>
					</table>

				</td>
			</tr>

		</table>
	</form>
	<%@include file="footer.html"%>
</body>
</html>