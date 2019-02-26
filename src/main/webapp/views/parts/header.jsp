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

<div class="wrapper">
    <!-- Sidebar -->
    <nav id="sidebar" class="bg-light">
        <!--<div class="sidebar-header">-->
        <!--<h1>E-store</h1>-->
        <!--</div>-->

        <ul class="list-unstyled components">
            <li>
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false"
                   class="dropdown-toggle">Categories</a>
                <ul class="collapse show list-unstyled" id="homeSubmenu">
                    <li>
                        <div class="custom-control ml-4 custom-radio custom-control-inline">
                            <input onclick="check('All')" type="radio" name="defaultExampleRadios" class="custom-control-input" id="defaultInlineAll" <c:if test='${empty param.cat}'>checked</c:if>>
                            <label class="custom-control-label" for="defaultInlineAll">All</label>
                        </div>
                    </li>
                    <li>
                        <div class="custom-control ml-4 custom-radio custom-control-inline">
                            <input onclick="check('1')" type="radio" name="defaultExampleRadios" class="custom-control-input" id="defaultInline1" <c:if test='${not empty param.cat && param.cat==1}'>checked</c:if>>
                            <label class="custom-control-label" for="defaultInline1">CPU Intel</label>
                        </div>
                    </li>
                    <li>
                        <div class="custom-control ml-4 custom-radio custom-control-inline">
                            <input onclick="check('2')" type="radio" name="defaultExampleRadios" class="custom-control-input" id="defaultInline2" <c:if test='${not empty param.cat && param.cat==2}'>checked</c:if>>
                            <label class="custom-control-label" for="defaultInline2">CPU AMD</label>
                        </div>

                    </li>
                    <li>
                        <div class="custom-control ml-4 custom-radio custom-control-inline">
                            <input onclick="check('3')" type="radio" name="defaultExampleRadios" class="custom-control-input" id="defaultInline3" <c:if test='${not empty param.cat && param.cat==3}'>checked</c:if>>
                            <label class="custom-control-label" for="defaultInline3">GPU</label>
                        </div>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">Contact</a>
            </li>
            <li>
                <a href="#">About</a>
            </li>
        </ul>
    </nav>

    <div id='content' class='p-3'>
</c:if>


