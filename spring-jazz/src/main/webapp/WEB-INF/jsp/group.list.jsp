<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>Group</h1>
		<table style="width: 100%">
			<tr>
				<td>Name</td>
				<td>#</td>
			</tr>
			<tr>
				<td colspan="2">
					<form method="get" action="/danh-sach">
						<input type="text" name="q"/>
					</form>
				</td>
			</tr>
		
			<c:forEach items="${groups}" var="item" varStatus="loop">
				<tr>
					<td><a href="/adanh-sach?group=${item.id}">${item.name}</a>
						<ul>
							<c:forEach items="${item.users}" var="user" varStatus="loop">
								<li>${user.username} - ${user.age}</li>
							</c:forEach>
						</ul>
					</td>
					<td><a href="xoa/${item.id}">Delete</a></td>
					<td><a href="sua?id=${item.id}">Update</a></td>
					<td><a href="adanh-sach?groupid=${item.id}">check</a></td>
					<td></td>
				</tr>
			</c:forEach>
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>