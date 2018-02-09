<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	ID: ${command.id}
	<a href="javascript:view(${command.id})">Name:${command.name}</a>
	
	<br/>
	<img src="/student/avatar/6" alt="Smiley face" height="42" width="42">
<dialog id="viewStudent" styple="width:50%;border:1px dotted black;">
	<im id="content"></div>
	<button id="hide">Close</button>
</dialog>
<script>
function view(id) {
	document.getElementById('content').innerHTML = 'id: ' + id;
	var dialog = document.getElementById('viewStudent');
	dialog.show();
};

	(function(){
		var dialog = document.getElementById('viewStudent');
		document.getElementById('hide').onclick = function(){
			dialog.close();
		};
	})();
</script>
</body>
</html>