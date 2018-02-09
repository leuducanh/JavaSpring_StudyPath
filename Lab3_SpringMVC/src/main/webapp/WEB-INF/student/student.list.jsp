<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LIST</title>

<script>
	
	function view(id) {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET","json/"+id,true);
		xmlHttp.onload = function(){
			if(this.status != 200) return;
			console.log(this.responseText);
		
			var student = JSON.parse(this.responseText);
			document.getElementById('content').innerHTML = 'Name: ' + student.name;
			var dialog = document.getElementById('viewStudent');
			dialog.show();
		};
		xmlHttp.send();
	}
</script>

</head>
<tiles:insertDefinition name="studentTemplate">
    	<tiles:putAttribute name="body">
    	<h2>Listofstudent</h2>
    	
    	<body>


    	</tiles:putAttribute>
    </tiles:insertDefinition> 
    
<form method="get" action="list">
<table border="1">
	<tr>
		<td colspan="4"><input type="text" name="q" size="30"/> </td>
		<td><input type="submit" value="Submit"/></td>
	</tr>

	<tr>
		<td>id</td>
		<td>Name</td>
		<td>Age</td>
	</tr>
	
	<c:forEach items="${students}" var="student">
		<tr>
			<td><a href="javascript:view(${student.id})">${student.name}</a></td>
			<%-- <td>${student.name}</td>
			<td>${student.age}</td> --%>
			<td><a href="delete/${student.id}">delete</a></td>
			<td><a href="/student/edit/${student.id}">get</a></td>
		</tr>
	</c:forEach>
</table>
</form>
<dialog id="viewStudent" styple="width:50%;border:1px dotted black;">
	<div id="content"></div>
	<button id="hide">Close</button>
</dialog>

<script>
	(function(){
		var dialog = document.getElementById('viewStudent');
		document.getElementById('hide').onclick = function(){
			dialog.close();
		};
	})();
</script>

</body>
    	

</html>