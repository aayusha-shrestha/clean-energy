<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/register.css" />	
	<style type="text/css">
	button, .button {
	border-radius: 20px;
	border: 1px solid #FF4B2B;
	background-color: #FF4B2B;
	color: #FFFFFF;
	font-size: 12px;
	font-weight: bold;
	padding: 12px 45px;
	letter-spacing: 1px;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
	cursor: pointer;
}
button:active {
	transform: scale(0.95);
}

button:focus {
	outline: none;
}
	</style>
</head>

<body>
<jsp:include page="../pages/header.jsp" />
	<div class="container-register">
	<jsp:include page="../pages/message.jsp" />
		<h1>Registration Form</h1>
		<form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
			<div class="row">
				<div class="col">
					<label for="firstName">First Name:</label> <input type="text"
						id="firstName" name="firstName" required>
				</div>
				<div class="col">
					<label for="lastName">Last Name:</label> <input type="text"
						id="lastName" name="lastName" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> <input type="text"
						id="username" name="username" required>
				</div>
				
				<div class="col">
					<label for="email">Email:</label> <input type="text" id="email"
						name="email" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender" required>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="col">
					<label for="phoneNumber">Phone Number:</label> <input type="tel"
						id="phoneNumber" name="phoneNumber" required>
				</div>
				
			</div>
			<div class="row">
				
				<div class="col">
					<label for="profileImage">Profile Image:</label> <input
						type="file" id="image" name="image" required>
				</div>
				
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
				<div class="col">
					<label for="retypePassword">Retype Password:</label> <input
						type="password" id="retypePassword" name="retypePassword" required>
				</div>
			</div>
			<input class="btn-register" type="submit" name="Submit" value="Register" />
		</form>
	</div>
	<jsp:include page="../pages/footer.jsp" />
</body>
</html>