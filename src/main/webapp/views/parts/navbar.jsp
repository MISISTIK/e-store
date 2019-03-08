<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<nav id="topnavbar" class="navbar navbar-light bg-light justify-content-between">
    <div class="custom-control-inline ">
        <button id="sidebarCollapse" type="button" class="navbar-toggler mt-1 mr-3">
            <span class=" navbar-toggler-icon"></span>
        </button>
        <div class="navbar-brand"><h3>E-store</h3></div>
    </div>

    <div class="navbar-nav mr-3 ">Welcome, ${sessionScope.user}</div>
    <div class="custom-control-inline ">
        <button type="button" class="btn btn-success mr-2">
            Корзина: <span class="badge badge-light ml-1">
            <c:choose>
                <c:when test="${not empty cart.size && cart.size > 0}">
                    ${cart.size}
                </c:when>
                <c:otherwise>0</c:otherwise>
            </c:choose>
        </span>
        </button>
        <a href="/logout" class="btn btn-outline-primary ">Logout</a>
    </div>
</nav>

