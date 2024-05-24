<%@page import="util.StringUtils"%>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400);
.message-success {
    position: relative;
    margin-top:10px;
    margin-left: 20px;
    margin-bottom: 10px;
    padding: 10px;
    background-color: #e6ffe6;
    text-align: left;
    font: 400 1.2em 'Open Sans', sans-serif;
    border: 1px solid #99ff99;
    color: #2a8723;
    font-weight: bold;
}

.message-warning {
    position: relative;
    margin-top:10px;
    margin-bottom: 10px;
    padding: 10px;
    background-color: #ffe6e6;
    text-align: left;
    font: 400 1.2em 'Open Sans', sans-serif;
    border: 1px solid #ff9999;
    
}

.message-failure {
    position: relative;
     margin-top:10px;
    margin-bottom: 10px;
    padding: 10px;
    background-color: #ffe6e6;
    text-align: left;
    font: 400 1.2em 'Open Sans', sans-serif;
    border: 1px solid #ff9999;
    color: #b00;
    font-weight: bold;
}
</style>
<%
	String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
	if(errorMessage != null && !errorMessage.isEmpty()){
	%>
	
	<div class="message-failure"><p class = "error-message" ><%= errorMessage %></p></div>
	<% 
	}
	%>
<%
	String successMessage = (String) request.getAttribute(StringUtils.SUCCESS_MESSAGE);
	if(successMessage != null && !successMessage.isEmpty()){
	%>
	
	<div class="message-success"><p class = "success-message" ><%= successMessage %></p></div>
	<% 
	}
	%>	