<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="util.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
<style>
.product-list {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-around;
	padding: 20px;
}

.product {
	padding:5px;
	width: 22%;
	border: 1px solid #ddd;
	border-radius: 5px;
	margin-bottom: 20px;
}
.product:hover {
	border: 1px solid #999;
}

.product img {
	width: 100%;
	/*border-radius: 5px;*/
	margin-bottom: 10px;
}

.product h2 {
	font-size: 18px;
	margin-bottom: 10px;
}

.product p {
	font-size: 14px;
	margin-bottom: 10px;
	color: #777;
}

button, .button {
	border-radius: 20px;
	border: 1px solid #FF4B2B;
	background-color: #FF4B2B;
	color: #FFFFFF;
	font-size: 12px;
	font-weight: bold;
	padding: 10px 10px;
	letter-spacing: 1px;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
	cursor: pointer;
}
input,
select {
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	width: 20%;
}
.product {
    position: relative;
    padding-bottom: 60px;
}

.add-to-cart {
    position: absolute;
    bottom: 10px;
    left: 0;
    width: 100%;
    text-align: center;
}}

</style>
</head>
<body>
	<jsp:include page="header.jsp"/>
	
	<div class="product-list">
		<c:forEach var="product" items="${products}">
			<div class="product">
				<div><img src="${pageContext.request.contextPath}/images/photo1.png"
					alt="${product.getProductName()}">
				<h2>${product.getProductName()}</h2>
				<p>${product.getProductDesc()}</p>
				<p><b>${product.getProductPrice()}</b></p></div>
				<div class="add-to-cart">
				<form action="${pageContext.request.contextPath}/product" method="post">
					<input type="hidden" name="product_id" value="${product.getProductId()}" />
					<input type="number" name="item_qty" value="1" />
					<input type="hidden" name="task" value="ADD_PRODUCT_TO_CART" />
					<button>Add to Cart</button>
				</form>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>