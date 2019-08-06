<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    Welcome <sec:authentication property="name" />
    
</sec:authorize>

<h1>
This is Beauty clinic and if you want to make an appointment please login first</h1>
<c:url var="loginPage" value="showMyLoginPage"/>
<c:url var="guestPage" value="guest"/>
<c:url var="ownerPage" value="owner"/>
<c:url var="makeAppointment" value="appointment/makeAppointmentForm"/>
<c:url var="myProfile" value="myProfile"/>

<a href="${loginPage}">Login</a><br><br>
<a href="${makeAppointment}">Make An Appointment</a><br><br>
<a href="${myProfile}">My Profile</a><br><br>
<sec:authorize access="hasRole('OWNER')">
   <a href="${ownerPage}">owner</a>
</sec:authorize>
<br>
<sec:authorize access="hasRole('GUEST')">
   <a href="${guestPage}">guest</a>
</sec:authorize>
<br>
<form:form action="logout" method="POST">
<input type="submit" value="Logout">
</form:form>
<br><br>
<c:url var="appointmentList" value="appointment/appointmentList"/>

<a href="${appointmentList}">AppointmentList</a><br>
</body>
</html>


