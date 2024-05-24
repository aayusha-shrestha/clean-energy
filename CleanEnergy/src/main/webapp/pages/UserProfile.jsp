<%@page import="util.StringUtils"%>
<%@page import="controller.DatabaseController"%>
<%@page import="model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User details</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/UserProfile.css" />
</head>
<body>
	  <%
            // Fetch user data from the server
            DatabaseController dbController = new DatabaseController();
            String username = (String) session.getAttribute("username"); 
            UserModel user = dbController.getUserByUsername(username);

            // Populate form fields with user data
            if (user != null) {     
      %>
      
    <div class="sidebar">
        <div class="sidebar-content">
            <div class="user-profile">
                <img src="${pageContext.request.contextPath}/images/user/<%= user.getImage() %>" class="img-fluid"  
	               		 style="border-radius:30%;
	               		 max-width: 70px; ">
            </div> 
            <div class="user-name">
                <h2><%= user.getUsername() %></h2>
            </div>
            <div class="sidebar-links">
                <ul>
                	<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/orderHistory">Order History</a></li>
                </ul>
            </div> 
        </div>
    </div>
    <div class="container">
        <div class="user-info">
        	<%
				String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
				if(errorMessage != null && !errorMessage.isEmpty()){
				%>
				
				<p class = "error-message" ><%= errorMessage %></p>     
				<% 
				}
			%>
        	 <h3 class="user-detail-heading">User Detail</h3>
            <form id="userDataForm" action="${pageContext.request.contextPath}/UpdatesServlet" method="post" enctype="multipart/form-data" >
             
                <div class="user-image"> 
               		 <img src="${pageContext.request.contextPath}/images/user/<%= user.getImage() %>" class="img-fluid"  
	               		 style="border-radius:30%; 
	               		 max-width: 100px; ">    
               		 <!-- width: 70px;
   					 height: 70px;
   					 object-fit: cover;
   					 border-radius: 10%; "> -->
           			<br>
           			
      		   </div>
        
                <div class="row">
                    <div class="col">
                        <label for="user_name">Username:</label>
                        <input type="text" id="username" name="username" value="<%= user.getUsername() %>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="first_name">First Name:</label>
                        <input type="text" id="firstname" name="firstname" value="<%= user.getFirstName() %>" required>
                    </div>
                    <div class="col">
                        <label for="last_name">Last Name:</label>
                        <input type="text" id="lastname" name="lastname" value="<%= user.getLastName() %>" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="gender">Gender:</label>
                        <select id="gender" name="gender">
                            <option value="male" <%= user.getGender().equals("male") ? "selected" : "" %>>Male</option>
                            <option value="female" <%= user.getGender().equals("female") ? "selected" : "" %>>Female</option>
                        </select>
                    </div>
                    <div class="col">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="phone_number">Phone Number:</label>
                        <input type="tel" id="phonenumber" name="phonenumber" value="<%= user.getPhoneNumber() %>" required>
                    </div>
                    <div class="col">
                        <label for="password">Password:</label>
                        <input type="text" id="password" name="password" value="<%= user.getPassword() %>" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="image">Select image:</label>
                        <input type="file" id="image" name="image" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit" name="action" value="update">Update</button>
                    </div>
                </div>
                <%
                    } else {
                %>
                <p>Error: Failed to fetch user data.</p>
                <%
                    }
                %>
            </form>
         </div>
      </div>
    </body>
</html>