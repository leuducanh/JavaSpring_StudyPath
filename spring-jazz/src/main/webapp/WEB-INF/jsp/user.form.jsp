<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>add user</h1>
	
	<form method="post" action="/aluu" commandName="command" >
	<input type="hidden" name="${_csrf.parameterName}" 
		value="${_csrf.token}">
		<p>Ten:<br/>
			<form:input type="text" path="command.username" name="username"/></p>
			<p>Mat khau:
			<form:input type="text" path="command.password" name="password"/></p>
			<p>Email:<br/>
			<form:input type="text" path="command.email" name="email"/></p>
			<p>Tuoi:
			<form:input type="text" path="command.age" name="age"/></p>
		</p>
		
		<p>
			<form:select path="command.groupid" id="group" name="group">
				<form:options items="${groups}"/>
			</form:select>
		</p>
		
		<p class="submit">
			<input type="submit" name="add" value="Add">
		</p>
	</form>
	
	</tiles:putAttribute>
</tiles:insertDefinition>