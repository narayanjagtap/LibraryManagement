<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Null Data</title>
</head>
<body>
<br>
<br>
<br>
<c:if test="${error ne null }">
	<h1 style="color: red;text-align: center;">${error }</h1>
</c:if>	

</body>
</html>