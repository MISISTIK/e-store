<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<nav id="topnavbar" class="navbar navbar-light bg-light justify-content-between">
    <div class="custom-control-inline ">
        <button id="sidebarCollapse" type="button" class="navbar-toggler mt-1 mr-3">
            <span class=" navbar-toggler-icon"></span>
        </button>
        <a href="/products" class="navbar-brand"><h3>E-store</h3></a>
    </div>

    <div class="navbar-nav mr-3 ">Welcome, ${sessionScope.user}</div>
    <div class="custom-control-inline">
        <a href="/cart" class="btn btn-success mr-2">
            Корзина: <span id="cart_size" class="badge badge-light ml-1"></span>
        </a>
        <a href="/logout" class="btn btn-outline-primary ">Logout</a>
    </div>
</nav>

