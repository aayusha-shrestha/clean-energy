<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${successMessage}
	<%-- <c:if test="${not empty successMessage}">
        <div class="success-message">${successMessage}</div>
    </c:if>
    <c:redirect url="${MESSAGE_PAGE}" /> --%>
</body>
</html>