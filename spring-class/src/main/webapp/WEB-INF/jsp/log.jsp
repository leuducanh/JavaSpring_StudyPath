<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<tiles:insertDefinition name="template">
<tiles:putAttribute name="body">
	<h1>login</h1>
	<form   method="POST" action="/a">
	
		<p><input type="text" name="username" placeholder="Username"/></p>
		
		<p><input type="submit" value="Login" ></p>
	</form>
</tiles:putAttribute>
</tiles:insertDefinition>
