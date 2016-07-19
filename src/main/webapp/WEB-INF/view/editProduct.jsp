<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>

	<h2>
		<b>Add Product</b>
	</h2>
	<%-- <form:errors path="userModel.*" /> --%>
	<form action="/product/updateProduct" method="post">
		<input type="hidden" name="pid" value="${pmodel.pid}"/>
		<label>Product Name</label> 
		<input type="text" name="pname" value="${pmodel.pname}" /><br /><br />
		
		<label>Price</label> 
		<input type="text" name="price" value="${pmodel.price}"/><br /><br /> 
		
		<label>Quantity</label>
		<input type="text" name="quantity" value="${pmodel.quantity}"/><br /><br />
		
		<label>Category</label>
		<select name="cmodel.cid">
			<c:forEach items="${clist}" var="cModel">
				<option value="${cModel.cid}"  ${cModel.cid == pmodel.cmodel.cid ? 'selected' : ''}>${cModel.cname}</option>
			</c:forEach>
		</select> <br /> <br />
		
		 <input type="submit" value="Submit" />
	</form>
</body>
</html>