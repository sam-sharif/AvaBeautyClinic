<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src=https://code.jquery.com/jquery-1.12.4.js></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<style type="text/css">
.validation{color: red;}
</style>

<title>Make Appointment</title>
</head>
<body>

<div class="container">
<h1> Make an Appointment</h1>

Welcome ${usernameOverApp}

<c:if test="${error!=null }">
<p style="font-weight: bold; color: red; font-size: large;;">${error}</p>
</c:if>

<form action="appointmentProccessing" method="post">

<input type="hidden" name="userName" value="${usernameOverApp}">

Choose your date :
<input type="text" name="date" id="datepicker">

<br><br>
<select name="time">
<option value="NONE" label="--- Select ---">
<option value="1" label="10am - 12pm">
<option value="2" label="12pm - 2pm">
<option value="3" label="2pm - 4pm">
<option value="4" label="4pm - 6pm">
</select>
<br><br>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="submit" value="submit">

</form>
</div>
<br><br>

<!-- Table of saved appointments -->
<div class="container">

<h1> <span class="label label-primary">Saved Dates List</span></h1>
  <br>
  
 <table class="table table-hover" border="2">
    <thead style="background-color : MediumSeaGreen;">
      <tr>
      
        <th style="font-size:1vw;">Date</th>
        <th style="font-size:1vw;">Time</th>
        
      </tr>
    </thead>
    <c:forEach var="app" items="${savedList}">
					<tbody>		
					<tr>
					    <td style="font-weight: bold;"><p class="bg-danger"> ${app.appointmentDate} </p></td>
						<td style="font-weight: bold;"> <p class="bg-danger">
						<c:choose>
						<c:when test="${app.appointmentTime==1}">10am - 12pm</c:when>
						<c:when test="${app.appointmentTime==2}">12pm - 2pm</c:when>
						<c:when test="${app.appointmentTime==3}">2pm - 4pm</c:when>
						<c:when test="${app.appointmentTime==4}">4pm - 6pm</c:when>
						</c:choose>
						</p></td>
						
					</tr>
				
				</c:forEach>
    </tbody>
  </table>
  
  <br><br>
<c:url var="LandingPage" value="/"/>
<a href="${LandingPage}">Back to HomePage</a>

  </div>


</body>
<script>
  $(function() {
    $("#datepicker").datepicker();
  });
</script>
</html>