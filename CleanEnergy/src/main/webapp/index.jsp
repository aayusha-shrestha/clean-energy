<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Clean Energy - Home</title>
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/header.css" />
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/footer.css" />
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/index.css" />
<style type="text/css">
#container_product {
	margin: 0 auto;
	width: 90%;
}
.product-list {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	padding: 20px 0px 20px 0px;
}

.product {
	padding:5px;
	width: 23%;
	border: 1px solid #ddd;
	border-radius: 5px;
	margin-bottom: 20px;
}
.product:hover {
	border: 1px solid #999;
}

.product img {
	width: 100%;
	height: 220px;
	margin-bottom: 10px;
	object-fit: contain;
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

.btn-cart-add{
	border-radius: 20px;
	border: 1px solid #4a7d94;
	background-color: #4a7d94;
	color: #FFFFFF;
	font-size: 12px;
	font-weight: bold;
	padding: 10px 10px;
	letter-spacing: 1px;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
	cursor: pointer;
	padding: 10px;
	box-sizing: border-box;
}
.qty-cart-add{
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	width: 25%;
}
.product {
    position: relative;
    padding-bottom: 60px; /* Adjust as needed to accommodate the button */
    /* Existing styles */
}

.add-to-cart {
    position: absolute;
    bottom: 10px; /* Adjust as needed */
    left: 0;
    width: 100%;
    text-align: center;
}
.product-not-found-message {
	font-size: 20px;
	margin-bottom: 20px;
	display: block; 
	line-height: 1.5;
	text-align: center;
}
.details-row{
	display: flex;
	justify-content: space-between;
}
.details-col-left{
	
}
.details-col-right{
	
	

</style>
</head>
<body>
	<jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
	<%@ include file="../../pages/message.jsp" %>
	
	<!-- Landing section -->
    <section class = "container">
        <div class="container__body">
            <h1>
                YOUR DESTINATION <br/>
                <span style="color:rgba(0, 0, 0, 0.912)">TO CONVENIENCE</span>
            </h1>
            <p>Power up your home with top-quality electrical appliances</p>
            <p>for every need and budget.</p><br><br>
            <div class="container__btn">
                <a href="${pageContext.request.contextPath}/product">Shop Now</a>
            </div>
        </div>
        <div class = "container__img">
            <img src = "${pageContext.request.contextPath}/images/home6.jpg">
        </div>       
    </section>
    <div id="container_product">
     <h1>TOP SELLING PRODUCTS</h1>
    <div class="product-list">
     <sql:setDataSource var="dbConnection" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/clean_energy_db" user="root" password="" />

	<sql:query var="products" dataSource="${dbConnection}">
		select *
		from products as p
		LEFT JOIN (
			select count(*) as total, pp.product_id
			from orders_products as  pp
			group by pp.product_id 
		) as op on op.product_id=p.product_id
		order by op.total desc
		limit 4
	
	</sql:query>
	<c:forEach var="product" items="${products.rows}">
	<div class="product">
				<div>
				<img src="${pageContext.request.contextPath}/images/product/${product.product_image}"
					alt="${product.product_name}">
				<h2>${product.product_name}</h2>
				<p>${product.product_price}</p>
				</div>
				<div class="details-row">
					<div class="details-col-left"><b><p>Price: ${product.product_price}</b></p></div>
					<div class="details-col-right"><p><b>Stock: ${product.stock_qty}</b></p></div>
				</div>
				<div class="add-to-cart">
				<form method="post"
					action="${pageContext.request.contextPath}/product">
					<input type="hidden" name="product_id" value="${product.product_id}" />
					<input class="qty-cart-add" type="number" min="1" name="item_qty" value="1" />
					<input type="hidden" name="task" value="ADD_PRODUCT_TO_CART" />
					<input type="submit" class="btn-cart-add" value="Add to Cart"/>
				</form>
				</div>
			</div>
	</c:forEach>
	</div>
    </div>
	<jsp:include page="<%=StringUtils.PAGE_URL_FOOTER%>" />
</body>
</html>