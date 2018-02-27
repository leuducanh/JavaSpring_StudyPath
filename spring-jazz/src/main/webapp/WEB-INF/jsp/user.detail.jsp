<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>user</h1>

	<h1>User</h1>
	<p>${user.username}</p>
	<p>${user.group.name}</p>
	</tiles:putAttribute>
</tiles:insertDefinition>