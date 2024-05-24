<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clean Energy</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/footer.css">
</head>
<body>
<div id="footer">
	<!-- Footer -->
    <footer class="footer">
        <div class="footer__info">
            <a href="${pageContext.request.contextPath}/index.jsp">
                <img class="footer__logo" src= "${pageContext.request.contextPath }/images/logo4.png" alt="logo">
            </a>
            <h3>
                Contact us for nationwide delivery or <br/>
                local, in-store pick up.
            </h3>
            <p><i class="fa-solid fa-location-dot"></i>  Kamal Pokhari, Kathmandu</p>
            <p><i class="fa-solid fa-phone"></i> 01-4961345</p>
            <p><i class="fa-solid fa-envelope"></i> cleanenergy@gmail.com</p>
        </div>
        <div class="footer__links-store">
            <h3>Our Store</h3>
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/product">Product</a></li>
                <li><a href="${pageContext.request.contextPath}/pages/contact.jsp">About Us</a></li>
            </ul>
        </div>
        <div class="footer__links-useful">
            <h3>Useful Links</h3>
            <ul>
                <li><a href="https://hafeleappliances.com/blogs/kitchen-trends/picking-a-new-combi-microwave-oven-the-5-point-checklist" target="_blank">Tips</a></li>
                <li><a href="https://www.repairadda.com/blog/5-most-common-problems-in-electrical-appliances-and-repairs" target="_blank">Tricks</a></li>
                <li><a href="https://blog.sajilosewa.com/how-to-take-care-of-electronic-appliances/" target="_blank">Care</a></li>
            </ul>
        </div>
        <div class="footer__subscription">
            <h3>Don't miss updates from us!</h3>
            <p>
                Get regular email subscription to festival discounts. <br/>
                Subscribe for exclusive offers.
            </p>
            <input type="email" id="email" placeholder="Enter your email address">
            <button class="footer__subscription-btn" onclick="subscribe()">Subscribe</button>
        </div>
    </footer>
    
    <!-- External JS -->
    <script src = "${pageContext.request.contextPath}/js/subscribe.js"></script>
</div>
</body>
</html>