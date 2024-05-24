<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="util.StringUtils"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/addProduct.css"/>
<title>Add Product</title>
</head>
<body>
	<h2>Add Products</h2>
	
	<%
	String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
	if(errorMessage != null && !errorMessage.isEmpty()){
	%>
	
	<p class ="error-message" style="text-align: center;"><%= errorMessage %></p>
	<% 
	}
	%>
    <div class="registration-box">
        <form action="${pageContext.request.contextPath}/admin" method="post" enctype="multipart/form-data">
            <div class="input-group">
                <div class="input-field">
                    <label for="prodname">Product Name</label>
                    <input type="text" name="prodName" id="prodname" required="required" placeholder="Name of Product">
                </div>
                <div class="input-field">
                    <label for="productdesc">Product Description</label>
                    <input type="text" name="prodDesc" id="productdesc" placeholder="Product Details">
                </div>
            </div>
            <div class="input-group">
                <div class="input-field">
                    <label for="categoryid">Category ID</label>
                    <select name="categId" id="categoryid" required="required">
					    <option value="1">1</option>
					    <option value="2">2</option>
					    <option value="3">3</option>
					</select>
                </div>
                <div class="input-field">
                    <label for="productprice">Product Price</label>
                    <input type="text" name="prodPrice" id="productprice" required="required" placeholder="Enter the Price">
                </div>
            </div>
            <div class="input-group">
                <div class="input-field">
                    <label for="stockqty">Stock Quantity</label>
                    <input type="text" maxlength="10" name="stockQty" id="stockqty" required="required" placeholder="Enter the quantity">
                </div>
                <div class="input-field">
                    <label for="stocklevel">Stock Level</label>
                    <input type="text" name="stockLvl" id="stocklevel" placeholder="Enter the Stock">
                </div>
            </div>
            <div class="input-group">
                <div class="input-field">
                    <label for="image">Product Image</label>
                    <input type="file" id="image" name="prodImage" required="required" >
                </div>
            </div>
            <button type="submit" id="submitBtn">Add</button>
        </form>
    </div>
</body>
</html>