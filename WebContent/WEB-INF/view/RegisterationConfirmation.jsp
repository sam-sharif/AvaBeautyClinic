<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<title>Registration Confirmation</title>
</head>

<body>

	<h2>Dear ${usernameOverApp}, you are registered successfully!</h2>

	<hr>
	<c:url var="AppointmentForm" value="/appointment/makeAppointmentForm"/>
	<a href="${AppointmentForm}">Make an Appointment</a>
	
	
	
</body>

</html>