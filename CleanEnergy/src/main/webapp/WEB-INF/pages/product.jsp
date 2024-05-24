<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.StringUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/product.css" />
</head>
<body>
<%@ include file="../../pages/header.jsp" %>
<div id="container_product">
	<%@ include file="../../pages/message.jsp" %>
	<div class="product-list">
		<c:choose>
			<c:when test="${empty products}">
				<div class="product-not-found-message">
					<h2>No products found</h2>
				</div>
			</c:when>
		</c:choose>
		<c:forEach var="product" items="${products}">
			<div class="product">
				<div>
				<img src="${pageContext.request.contextPath}/images/product/${product.getProductImgUrlFromPart()}"
					alt="${product.getProductName()}" style="height: 220px; object-fit: contain;">
				<h2>${product.getProductName()}</h2>
				<p>${product.getProductDesc()}</p>
				</div>
				<div class="details-row">
					<div class="details-col-left"><b><p>Price: ${product.getProductPrice()}</b></p></div>
					<div class="details-col-right"><p><b>Stock: ${product.getStockQty()}</b></p></div>
				</div>
				<div class="add-to-cart">
				<form method="post"
					action="${pageContext.request.contextPath}/product">
					<input type="hidden" name="product_id" value="${product.getProductId()}" />
					<input class="qty-cart-add" type="number" min="1" name="item_qty" value="1" />
					<input type="hidden" name="task" value="ADD_PRODUCT_TO_CART" />
					<input type="submit" class="btn-cart-add" value="Add to Cart"/>
				</form>
				</div>
			</div>

		</c:forEach>

		<!-- Add more product divs as needed -->
	</div>

</div>
	<%@ include file="../../pages/footer.jsp" %>
</body>
</html>