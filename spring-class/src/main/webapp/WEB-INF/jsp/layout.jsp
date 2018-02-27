    <%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
    <%@taglib   uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1" cellpadding="2" cellspacing="2" align=center>
	
		<tr>
			<td height="30"> Welcome to Java Spring Clazz</td>
		</tr>
		
		<tr>
			<td>
				<a href="/">Trang chu</a>
				<sec:authorize access="!hasAnyRole('ROLE_USER')">
				<a href="/dang-nhap" style="margin-left:30px">Dang nhap</a>
				</sec:authorize>
				
				<sec:authorize access="hasAnyRole('ROLE_USER')">
				<a href="/nguoi-dung" style="margin-left:30px">Ca nhan</a>
				<a href="/logout" style="margin-left:30px">logout</a>
				</sec:authorize>
			</td>
		</tr>
		
		<tr>
			<td><tiles:insertAttribute name="body" /></td>
		</tr>
	
	</table>
</body>
</html>