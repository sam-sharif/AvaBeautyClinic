<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Appointment List</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src=https://code.jquery.com/jquery-1.12.4.js></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

<div class="container">
  <h1> <span class="label label-primary">Appointment List</span></h1>
  <br><br>
  
  
		<!-- customer search form -->
	<div class="container">
   
     <form class="form-inline" action="appointmentSearchList">
      <div class="form-group">
       <label class="sr-only" for="Search">Search:</label>
       <input type="search" class="form-control" id="Search" placeholder="Name/LastName"  name="searchItem">
      </div>
       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
       <input type="hidden" name="action" value="customerSearch">
       <input type="submit" class="btn btn-danger" value="Search">
      </form>
    </div>
	
	<br>
	<!-- date search form -->

<div class="container">
  
  <form class="form-inline" action="appointmentSearchList" method="get">
    <div class="form-group">
      <label class="sr-only" for="datepicker1">Choose your date from:</label>
      <input class="form-control" placeholder="Choose your date from" type="text" name="date_from" id="datepicker1">
    </div>
    <div class="form-group">
      <label class="sr-only" for="datepicker">to:</label>
      <input class="form-control" placeholder="to" type="text" name="date_to" id="datepicker">
    </div>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <input type="hidden" name="action" value="dateSearch">
      <input type="submit" class="btn btn-danger" value="Search">
      
  </form>
</div>

  <br><br>
  

  <table class="table table-hover" border="2">
    <thead style="background-color : MediumSeaGreen;">
      <tr>
      
        <th style="font-size:2vw;">Date</th>
        <th style="font-size:2vw;">Time</th>
        <th style="font-size:2vw;">Firstname</th>
        <th style="font-size:2vw;">Lastname</th>
        <th style="font-size:2vw;">Phone</th>
        <th style="font-size:2vw;">Email</th>
        <th style="font-size:2vw;">Applied Date</th>
      </tr>
    </thead>
    <c:forEach var="app" items="${appointments}">
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
						<td style="font-weight: bold;" ><p class="bg-danger"> ${app.customer.firstName}</p> </td>
						<td style="font-weight: bold;"><p class="bg-danger"> ${app.customer.lastName} </p></td>
						<td style="font-weight: bold;"> <p class="bg-danger">${app.customer.phoneNo} </p></td>
						<td style="font-weight: bold;"><p class="bg-danger"> ${app.customer.email}</p></td>
						<td style="font-weight: bold;"><p class="bg-danger"> ${app.appliedDate}</p></td>
					</tr>
				
				</c:forEach>
    </tbody>
  </table>
</div>
<c:url var="LandingPage" value="/"/>
<a href="${LandingPage}">Back to HomePage</a>

</body>
<script>
  $(function() {
    $("#datepicker").datepicker();
    $("#datepicker1").datepicker();
  });
  
</script>
</html>

