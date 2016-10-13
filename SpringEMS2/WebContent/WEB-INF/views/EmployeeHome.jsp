<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>welcome to home page 

<h3>${status.empName}</h3>
<form action="PersonalInformation" method="post">

<input type="submit" value="PersonalInformation"> 
</form>

 Change Password: <a href="ChangePassword.html">Change Password</a>
<br>
 leave veiw <a href="LeavePage.html"> leave veiw </a> 

</h3>
</center>



</body>
</html>