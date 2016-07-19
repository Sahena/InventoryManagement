<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Category</title>
</head>
<body>

	<h2>
		<b>Add Category</b>
	</h2>
	<%-- <form:errors path="userModel.*" /> --%>
	<form action="saveCategory" method="post">
		<label>Category Name</label> <input type="text" name="cname" /><br />
		<br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>