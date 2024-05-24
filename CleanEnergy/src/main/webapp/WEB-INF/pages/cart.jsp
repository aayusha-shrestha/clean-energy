<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.StringUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<script type="text/javascript">
	function confirmRemoveProduct() {
		if (confirm('Are you sure to remove product? ')) {
			return true;
		} else {
			return false;
		}
	}
	
	function confirmOrder() {
		if (confirm('Are you sure to confirm order? ')) {
			return true;
		} else {
			return false;
		}
	}
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/cart.css" />
</head>
<body>
	<%@ include file="../../pages/header.jsp"%>
	<div class="container_cart">
		<%@ include file="../../pages/message.jsp"%>
		<div class="cart">
			<div class="cart-left">
				<c:choose>
					<c:when test="${empty cartItems}">
						<div class="cart-empty-message">
							<h2>Your cart is empty</h2>
						</div>
					</c:when>
				</c:choose>
				<c:forEach var="cartItem" items="${cartItems}">
					<div class="cart-item">
						<div class="cart-item-col">
							<img src="${pageContext.request.contextPath}/images/product/${cartItem.getProductImage()}"
								alt="${cartItem.getProductName()}" alt="Item 1"
								class="item-image">
						</div>

						<div class="cart-item-col item-name">${cartItem.getProductName()}</div>
						<div class="cart-item-col">
							<form method="post"
								action="${pageContext.request.contextPath}/cart">
								Quantity: <input type="number" min="1" name="item_qty"
									class="item-qty" value="${cartItem.getItemQty()}" /> <input
									type="hidden" name="cart_item_id"
									value="${cartItem.getCartItemId()}" /> <input type="hidden"
									name="task" value="UPDATE_QTY_PRODUCT" />
								<input type="submit" class="remove-btn" value="Update"/>
							</form>
						</div>
						<div class="cart-item-col item-price">
							<span>$${cartItem.getProductPrice()}</span>
							<form method="post"
								action="${pageContext.request.contextPath}/cart"
								onsubmit="return confirmRemoveProduct()">
								<input type="hidden" name="cart_item_id"
									value="${cartItem.getCartItemId()}" /> <input type="hidden"
									name="task" value="REMOVE_PRODUCT" />
								<input type="submit" class="remove-btn" value="Remove" />
							</form>
						</div>
					</div>
				</c:forEach>

			</div>
			<div class="cart-right">
				<h2>Cart Summary</h2>
				<div class="total">
					Total: ${StringUtils.CART_CURRENCY_SYMBOL}<span>${cartTotal }</span>
				</div>
				<c:choose>
					<c:when test="${empty cartItems}">
					</c:when>
					<c:otherwise>
						<form method="post"
							action="${pageContext.request.contextPath}/cart" onsubmit="return confirmOrder()">
							<button class="checkout-btn">Confirm Order</button>
							<input type="hidden" name="task" value="CREATE_ORDER" />
						</form>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>
	<%@ include file="../../pages/footer.jsp"%>
</body>
</html>