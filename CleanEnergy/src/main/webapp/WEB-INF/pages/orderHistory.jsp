<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order History</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/orderHistory.css" />
</head>
<body>
	<%@ include file="../../pages/header.jsp"%>
	<div class="container_order">
		<c:choose>
			<c:when test="${empty orders}">
				<div class="order-history-empty-message">
					<h2>No order history</h2>
				</div>
			</c:when>
		</c:choose>
		<div class="order-history">
			<table>
				<thead>
					<tr>
						<th>Order ID</th>
						<th>Order Date</th>
						<th style="text-align: center">Order Status</th>
						<th style="text-align: right">Order Amount</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.getOrderId()}</td>
							<td>${order.getOrderDate()}</td>
							<td style="text-align: center"><span class="order-status order-${order.getOrderStatus()}">${order.getOrderStatus()}</span></td>
							<td style="text-align: right">${order.getOrderAmount()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>




		</div>
	</div>
	<%@ include file="../../pages/footer.jsp"%>
</body>
</html>