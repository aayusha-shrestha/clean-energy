<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Clean Energy</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/login.css" />
</head>
<body>
<h2></h2>
<div class="container" id="container">
	<div class="form-container sign-in-container">
	<div class="overlay">
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>Enter your personal details and start journey with us</p>
				<a class="button" href="${pageContext.request.contextPath}/index.jsp" class="ghost" id="Home">Home</a>
			</div>
		</div>
		
	</div>
	<div class="overlay-container">
		<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
			<div class="form-controls-login">
			<jsp:include page="../pages/message.jsp" />
			<h1>Log in</h1>
			<span>with your account</span>
			<input type="text" id="username" name="username" placeholder="Username" />
			<input type="password" id="password" name="password" placeholder="Password" />
			<button type="submit">Log In</button>
			</div>
			<a href="${pageContext.request.contextPath}/pages/register.jsp">Register Your Account</a>
		</form>
	</div>
</div>

</body>
</html>