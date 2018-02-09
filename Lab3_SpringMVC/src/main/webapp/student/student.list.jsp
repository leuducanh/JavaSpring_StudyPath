<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LIST</title>
</head>
<body>

<table border="1">
	<tr>
		<td>id</td>
		<td>Name</td>
		<td>Age</td>
	</tr>
	
	<c:forEach items="${students}" var="student">
		<tr>
			<td>${student.id}</td>
			<td>${student.name}</td>
			<td>${student.age}</td>
			<td><a href="delete/${student.id}">delete</a></td>
			<td><a href="/student/edit/${student.id}">get</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>