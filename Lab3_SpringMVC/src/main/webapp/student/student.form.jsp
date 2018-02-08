<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Please input student information</h2>
	<form method="POST" action="save">
		<table>
			<tr>
				<td>Name:</td>
				<!-- <td><input name="name" type="text"></td>-->
				<td><form:input path="command.name"/><form:errors path="command.name"></form:errors></td>
			</tr>
			<tr>
				<td>Age:</td>
				<!--<td><input name="age" type="text"/></td>-->
				<td><form:input path="command.age" type="number"/><form:errors path="command.age"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit"/>
				</td>
			</tr>
		
		</table>
	</form>
</body>
</html>