<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
<h1>${headerMessage}</h1>
<!--<p>Hello ${userModel.userName}</p> -->
	<h3>
		<b>Category Management</b>
	</h3>
	<a href="/category/addCategory">Add Category</a>
	<br />
	<br />
	<a href="/category/displayCategory">Display Category</a>
	<br />
	<br />
	<h3>
		<b>Product Management</b>
	</h3>
	<a href="/product/addProduct">Add Product</a>
	<br />
	<br />
	<a href="/product/displayProduct">Display Product</a>
	<br />
	<br />
	<h3>
		<b>User Management</b>
	</h3>
	<a href="addUser">Add User</a>
	<br />
	<br />
	<a href="displayUser">Display User</a>
	<br />
	<br />
	<a href="logOut">Log Out</a>
</body>
</html>