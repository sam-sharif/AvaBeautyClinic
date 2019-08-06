<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Customer</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
		  
		  <style type="text/css">
		  .error{color: red}
		  </style>
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
		
			<h2>please Enter Your Information:</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Customer</h3>
	
		<form:form action="processRegisterationForm2" modelAttribute="newCustomer" method="POST">

			<!-- if id is null then will be saved otherwise updated -->
			<form:hidden path="id" />
			<form:hidden path="username" />	
			
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName" /></td>
						<td><form:errors path="firstName"/></td>
					</tr>
				
					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName" /></td>
						<td><form:errors path="lastName"/></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" /></td>
						<td><form:errors path="email"/></td>
					</tr>
					
					<tr>
						<td><label>Phone No:</label></td>
						<td><form:input path="phoneNo" /></td>
						<td><form:errors path="phoneNo"/></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		
	
	</div>

</body>

</html>










