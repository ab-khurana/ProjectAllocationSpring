<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body bgcolor="grey">
	<center>
	<h1>Login Page</h1>

		<form action="loginUser" method="post">
			
			Username: <input name="username" type="text" value="" required><br><br>
			Password: <input name="password" type="password" value="" required><br><br>
			
			
			 <br><br>
			  <input type="submit" value="Login">
			
		</form>
	</center>
</body>
</html>