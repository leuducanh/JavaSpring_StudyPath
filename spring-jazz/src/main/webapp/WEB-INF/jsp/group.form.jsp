<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>Add group</h1>
		<form  method="post" action="/luu" commandName="command">
		<input type="hidden" name="${_csrf.parameterName}" 
		value="${_csrf.token}">
		<p><form:input type="hidden"  path="command.id" name="id"/></p>
			<p>
				<form:input type="text"  path="command.name" name="name"/>
			</p>
			
				<input type="submit" name="add"  value="Add">
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>