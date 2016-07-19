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
		<th>Category</th>
		<th>Delete</th>
		<th>Edit</th>
		<c:forEach items="${clist}" var="cModel">
			<tr>
				<td>${cModel.cname}</td>
				<td><a
					href="<c:url value='/category/editCategory/${cModel.cid}'/>">Edit</a></td>
				<td><a
					href="<c:url value='/category/deleteCategory/${cModel.cid}'/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>