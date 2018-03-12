<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>User</h1>
		<table style="width: 100%">
			<tr>
				<td>Name</td>
				<td>#</td>
					</tr>
			<c:forEach items="${users}" var="item" varStatus="loop">
				<tr>
					<td>${item.username}</td>
					<td><a href="/chi-tiet/${item.username}">User detail</a></td>
				</tr>
			</c:forEach>
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>