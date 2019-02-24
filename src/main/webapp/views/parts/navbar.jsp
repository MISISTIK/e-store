<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand mb-0 h1">E-shop</a>
    <div class="navbar-text mr-3">Welcome, ${sessionScope.user}</div>
    <a href="?logout" class="btn btn-outline-primary">Logout</a>
</nav>

