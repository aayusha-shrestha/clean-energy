<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="util.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/admin.css"/>
	</head>
	
	<body>
	<ul class="utilities">
            <br>
            <li class="logout warn"><a href="${pageContext.request.contextPath}/LogoutServlet">Log Out</a></li>
    </ul>
    <h1>Product List</h1>
    <%
    String successMessage = (String) request.getAttribute(StringUtils.SUCCESS_MESSAGE);
	String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
	if(errorMessage != null && !errorMessage.isEmpty()){
	%>
	
	<p style="text-align: center;"><%=errorMessage%></p>
	<% 
	}
	%>
	
	<%
	if(successMessage != null && !successMessage.isEmpty()){
	%>
	
	<p style="text-align: center;"><%=successMessage%></p>
	<% 
	}
	%>
    <!-- header -->
		<header role="banner">
		  <h1>Admin Panel</h1>
		</header>
		<!-- header ends -->
		
		<!-- side menu -->
		<nav role='navigation'>
        <ul class="main">
            <li class="product"><a href="#productTable" onclick="${pageContext.request.contextPath}/admin">Product</a></li>
            <li class="orders-menu"><a href="#orderTable">Orders</a></li>
        </ul>
    	</nav>
    	<!-- side menu ends -->
    	
	<!-- title -->
    <main role="main">
        <section class="attendance" id="productTable">
            <div class="attendance-list">
                <div class="list" style="display: flex-table-row;">
                    <h1>Product List</h1>
                    <a href="${pageContext.request.contextPath}/pages/addProduct.jsp" class="add-button">Add Products</a>
				
                <table class="table">
                    <thead>
                        <tr>
                            <th>product_id</th>
                            <th>product_name</th>
                            <th>product_desc</th>
                            <th>category_id</th>
                            <th>product_price</th>
                            <th>stock_qty</th>
                            <th>stock_level</th> 
                            <th>product_image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${products}" var="product">
                        <tr>
                        	<td>${product.productId}</td>
			                <td>${product.productName}</td>
			                <td>${product.productDesc}</td>
			                <td>${product.categoryId}</td>
			                <td>${product.productPrice}</td>
			                <td>${product.stockQty}</td>
			                <td>${product.stockLevel}</td>
			                <td>
			                <img src="${pageContext.request.contextPath}/images/product/${product.productImgUrlFromPart}" width="100" height="80" alt="product">
			                </td>
                            <td class="actions">
                            	<!-- The ? indicates the beginning of the query string -->
                                <form action="${pageContext.request.contextPath}/UpdateProductServlet" 
                                method="get">
								    <input type="hidden" name="updateProdId" value="${product.productId}">
								    <button type="submit">Edit</button>
								</form>
								<form id="deleteForm-${product.productId}" method="post"
								action="${pageContext.request.contextPath}/DeleteProductServlet">
                                	<input type="hidden" name="deleteProdId" value="${product.productId}">
								    <button type="submit" onclick="return confirmDelete('${product.productId}')">
								    	Delete
								    </button>
								</form>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            </div>
        </section>

        <!-- Order Table -->
        <section class="attendance" id="orderTable">
            <div class="attendance-list">
                <h1>Order List</h1>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>User ID</th>
                            <th>Status</th>
                            <th>Order Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.orderDate}</td>
                            <td>${order.userId}</td>
                            <td>${order.orderStatus}</td>
                            <td>${order.orderAmount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        </main>
</body>
	<script>
	function confirmDelete(productId) {
		if (confirm("Are you sure you want to delete product " + productId + "?")) {
			document.getElementById("deleteForm-" + productId).submit();
		} else {
			return false;
		}
	}
	function confirmDeliver(orderId) {
		if (confirm("Are you sure you want to deliver order " + orderId + "?")) {
			document.getElementById("deliverForm-" + orderId).submit();
		} else {
			return false;
		}
	}
	</script>
</html>