<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
     <%@taglib   uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<p>
		<tr>
			<td>
				<a href="/">Trang chu</a>
				<sec:authorize access="!hasAnyRole('ROLE_USER')">
				<a href="/dang-nhap" style="margin-left:30px">Dang nhap</a>
				</sec:authorize>
				
				<sec:authorize access="hasAnyRole('ROLE_USER')">
				<a href="/nguoi-dung" style="margin-left:30px">Ca nhan</a>
				<a href="/logout" style="margin-left:30px">logout</a>
				<a href="javascript:document.getElementById('logout').submit();">dang xuat</a>
				</sec:authorize>
			</td>
		</tr>
	</p>
	
	<form action="/j_spring_security_logout" id="logout" method="post">
		<input type="hidden"  name="${_csrf.parameterName}" 
		value="${_csrf.token}"/>
	</form>
	
	<table border="1" cellpadding="2" cellspacing="2" align=center>

		<tr>
			<td><tiles:insertAttribute name="body" /></td>
		</tr>
	
	</table>
	
	
</body>
</html>