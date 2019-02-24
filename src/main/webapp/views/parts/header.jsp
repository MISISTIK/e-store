<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>

<link rel='stylesheet' href='/static/css/bootstrap.min.css'/>
<link rel='stylesheet' href='/static/css/style.css'/>

<c:if test="${not empty sessionScope.user}">
    <%@ include file='navbar.jsp' %>
</c:if>


