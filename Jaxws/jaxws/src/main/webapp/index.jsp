<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h2>${message}</h2>
	</tiles:putAttribute>
	
</tiles:insertDefinition>
