<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="util.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/addProduct.css"/>
<title>Update Product</title>
</head>
<body>
	<h2>Update Product</h2>
	
	<%
	String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
	if(errorMessage != null && !errorMessage.isEmpty()){
	%>
	
	<p class = "error-message" ><%= errorMessage %></p>
	<% 
	}
	%>
    <div class="registration-box">
        <form action="${pageContext.request.contextPath}/UpdateProductServlet" method="post" enctype="multipart/form-data">
        	<img src="${pageContext.request.contextPath}/images/product/${product.getProductImgUrlFromPart()}" 
        	style="display: block; margin-left: auto; margin-right: auto; width: 50%; object-fit: contain;" width="150" height="160" alt="product">
            <div class="input-group">
                <div class="input-field">
                    <label for="prodname">Product Name</label>
                    <input type="hidden" name="uprodId" id="uprodId" value="${product.getProductId()}">
                    <input type="text" name="uprodName" id="prodname" required="required" value="${product.getProductName()}">
                </div>
                <div class="input-field">
                    <label for="productdesc">Product Description</label>
                    <input type="text" name="uprodDesc" id="productdesc" value="${product.getProductDesc()}">
                </div>
            </div>
            <div class="input-group">
                <div class="input-field">
                    <label for="categoryid">Category ID</label>
                    <!--  Dynamically change the category -->
                    <select name="categId" id="categoryid" required="required">
					  <option value="1" ${product.getCategoryId() == 1 ? "selected" : ""}>1</option>
					  <option value="2" ${product.getCategoryId() == 2 ? "selected" : ""}>2</option>
					  <option value="3" ${product.getCategoryId() == 3 ? "selected" : ""}>3</option>
					</select>
                </div>
                <div class="input-field">
                    <label for="productprice">Product Price</label>
                    <input type="text" name="uprodPrice" id="productprice" required="required" value="${product.getProductPrice()}">
                </div>
            </div>
            <div class="input-group">
                <div class="input-field">
                    <label for="stockqty">Stock Quantity</label>
                    <input type="text" maxlength="10" name="ustockQty" id="stockqty" required="required" 
                    value="${product.getStockQty()}">
                </div>
                <div class="input-field">
                    <label for="stocklevel">Stock Level</label>
                    <input type="text" name="ustockLvl" id="stocklevel" value="${product.getStockLevel()}">
                </div>
            </div>
            <div class="input-group">
                <div class="input-field">
                    <label for="image">Product Image</label>
                    <input type="file" id="image" name="uprodImage" value="${product.getProductImgUrlFromPart()}">
                </div>
            </div>
            <button type="submit" id="submitBtn">Update</button>
        </form>
    </div>
</body>
</html>