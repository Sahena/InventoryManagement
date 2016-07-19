<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
</head>
<body>
	<table>
		<th>Product</th>
		<th>Price</th>
		<th>Quantity</th>
		<th>Category</th>
		<th>Edit</th>
		<th>Delete</th>
		<c:forEach items="${plist}" var="pModel">
			<tr>
				<td>${pModel.pname}</td>
				<td>${pModel.price}</td>
				<td>${pModel.quantity}</td>
				<td>${pModel.cmodel.cname}</td>
				<td><a href="<c:url value='editProduct/${pModel.pid}'/>">Edit</a></td>
				<td><a href="<c:url value='deleteProduct/${pModel.pid}'/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>