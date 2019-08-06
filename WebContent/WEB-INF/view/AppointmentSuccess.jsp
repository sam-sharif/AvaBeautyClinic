<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2 style="color: red;">Dear ${name}, your appointment was successfully set on ${date}</h2>
<c:url var="LandingPage" value="/"/>
<a href="${LandingPage}">Back to HomePage</a>
</body>
</html>