<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Contact Us</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/contact.css" />   
   <script>
    function validateForm() {
      var firstname = document.forms["registration-form"]["firstname"].value;
      var lastname = document.forms["registration-form"]["lastname"].value;
      var company = document.forms["registration-form"]["company"].value;
      var phone = document.forms["registration-form"]["phone"].value;
      var course = document.forms["registration-form"]["course"].value;
      var message = document.forms["registration-form"]["message"].value;
      var message = document.forms["registration-form"]["message"].value;

      var alphabeticPattern = /^[A-Za-z]+$/;

      if (!alphabeticPattern.test(firstname)) {
        alert("Invalid First Name. It must contain only String value.");
        return false;   
      }

      if (!alphabeticPattern.test(lastname)) {
        alert("Invalid Last Name. It must contain only String value.");
        return false;
      }

      if (!alphabeticPattern.test(company)) {
        alert("Invalid Company Name. It must contain only String value.");
        return false;
      }
      
      if (!isInteger(phone)) {
          alert("Invalid Phone Number. It must contain only Integer value.");
          return false;
        } 

      if (!alphabeticPattern.test(course)) {
        alert("Invalid Course Name. It must contain only String value.");
        return false;
      }
      
      if (!alphabeticPattern.test(message)) {
          alert("Invalid Message. It must contain only String value.");
          return false;
        }
      
      alert("You have successfully sent message!");
      return true;
    }

    function isString(value) {
      return typeof value === 'string' || value instanceof String;
    }

    function isInteger(value) {
      return !isNaN(value) && parseInt(Number(value)) == value && !isNaN(parseInt(value, 10));
    }
  </script>
  
</head>

<body ng-app="">
	<jsp:include page="header.jsp"/>
  <div class="container">
    <div class="box">
      <div class="text">
        <h1>Contact <span class="red">Us</span></h1>
        <hr class="redline">
        <p>Have Questions? We have answers.</p>
      </div>
    </div>

    <div class="content">
      <div class="address">
        <h3>Our Address</h3>
        <div class="redline-address"></div>
        <p>Islington College</p>
        <p>Kamal Pokhari</p>
        <p>Kathmandu, Nepal - 4600</p>
        <div class="phone-e">
          <p><span class="glyphicon glyphicon-envelope"> </span> cleanenergy@gmail.com</p>
          <p><span class="glyphicon glyphicon-phone"></span> 01-4961345</p>
        </div>
      </div>

      <div class="touch registration-box">
        <h4>Let's get in touch</h4>
        <form action="" method="POST" name="registration-form" onsubmit="return validateForm()">
          <div class="form-group">
          	<label for="firstname">First Name </label>
            <input type="text" class="form-control" placeholder="First Name" ng-model="firstname" name="firstname" required>    
          </div>
          <div class="form-group">
          	<label for="lastname">Last Name </label> 
            <input type="text" class="form-control" placeholder="Last Name" ng-model="lastname" name="lastname" required>    
          </div> 
          <div class="form-group">
          	<label for="company">Company Name</label>
            <input type="text" class="form-control" placeholder="Company Name" ng-model="company" name="company" required>    
          </div>
          <div class="form-group">
          	 <label for="phone">Phone Number</label>
            <input type="text" class="form-control" placeholder="Phone" ng-model="phone" name="phone" required>    
          </div>
          <div class="form-group">
          	<label for="course">Course</label>
            <input type="text" class="form-control" placeholder="Course" ng-model="course" name="course" required>    
          </div>
          <div class="form-group">
          	<label for="message">Message</label>
            <textarea class="form-control" rows="6" placeholder="Message" ng-model="message" name="message" required></textarea>
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-success">SEND MESSAGE</button>
          </div>
        </form>
      </div>  

    </div>

  <div class="map-container">
      <div class="map">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d56516.50289301269!2d85.28438080978006!3d27.708595691868936!2m3!1f0!2f0!3
        f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb1908434cb3c5%3A0x1fdf1a6d41d2512d!2sIslington%20College!5e0!3m2!1sen!2snp!4v1713541797123!5m2!1sen!2snp" 
        width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
      </div>
  </div>
  </div>
  <jsp:include page="footer.jsp"/>
</body>

</html>