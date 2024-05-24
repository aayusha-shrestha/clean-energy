<%@page import="util.UserUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="util.StringUtils"%>
<%
	HttpSession userSession = request.getSession();
	String currentUser = (String) userSession.getAttribute(StringUtils.USER_NAME);
	boolean isUserLoggedIn = UserUtils.isUserLoggedIn(request);
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clean Energy</title>
    <!-- External CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/header.css">
    <style>
    /* second navigation */
	.navig-container {
    	width: 100%;
    	background-color: #f4f8f9;
	}
	
	.navig ul {
	    list-style-type: none;
	    margin-right: 95px;
	    padding: 0;
	    text-align: right;
	}

	.navig ul li {
	    display: inline;
	}

	.navig ul li a {
	    display: inline-block;
	    padding: 10px 20px;
	    text-decoration: none;
	    color: #7ca3b4;
	}

	.navig ul li a:hover {
	    color: #7e64a3;
	    transition: .2s;
	}
    </style>
</head>
<body>
	<!-- Nav bar-->
    <header>
        <a href="${pageContext.request.contextPath}/index.jsp">
            <img class="logo" src="${pageContext.request.contextPath}/images/logo4.png" alt="logo">
        </a>
        <nav>
            <ul class="nav-content" id="side-menu">
                <div>
                <%
        // Get the value of the "price" parameter from the URL
        String price = request.getParameter("price")!=null?request.getParameter("price"):"";
        
        // Get the value of the "q" parameter from the URL
        String q = request.getParameter("q")!=null?request.getParameter("q"):"";
    %>
                   <form action="${pageContext.request.contextPath}/product" method="get">
                    <input style="width: 25%" type="text" value="<%= price %>" class="input-search" name="price" placeholder="Max Price..">
                    <input type="text" class ="input-search" value="<%= q %>" name="q" placeholder="Search..">
                   <button class="btn-search" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>                
                </form>
                </div>
                <%
                if (!isUserLoggedIn){ 
                %>
                	<li><a href="<%=contextPath + StringUtils.LOGIN_PAGE%>">Login</a></li>
                    <li><a href="<%=contextPath + StringUtils.REGISTER_PAGE%>">Sign Up</a></li>
                <%
                }
                %>
                
                <%
                if (isUserLoggedIn){
                %>
                	<form action ="<%=contextPath + StringUtils.SERVLET_URL_LOGOUT %>" method="post">
                		<input type="submit" value="Logout">
                	</form>
                <%
                } 
                %>
                <a href="${pageContext.request.contextPath}/cart"><i class="fa-solid fa-cart-shopping"></i></a>
                <a href="${pageContext.request.contextPath}/pages/UserProfile.jsp"><i class="fa-solid fa-user"></i></a>
            </ul>
        </nav>
    </header>
    
    <!-- Second Nav bar-->
    <div class="navig-container">
            <div class="navig">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/product">Product</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/contact.jsp">About Us</a></li>
                </ul>
            </div>
        </div>
</body>
</html>