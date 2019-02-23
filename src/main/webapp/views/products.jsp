<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h1>Products</h1>
Categories: <a href="/e-store">All</a> | <a href="?cat=1">Intel</a> | <a href="?cat=2">AMD</a> | <a href="?cat=3">Videocards</a>
<hr>
Корзина : ${sessionScope.cart.size}
<hr>
<c:set var="prod_len" value="${fn:length(products)}"/>
<hr>
<a href="?logout">Logout</a>

<c:if test="${prod_len > 0}">
    <table border='1'>
        <tr align="center">
            <c:forEach var="i" begin="1" end="${prod_len}">
            <td width="200" height="200">
                <br>
                <a href="?id=${products[i-1].id}"><img src="static/img/${products[i-1].id}.jpg" height="150"></a>
                <ul>
                    <li>Id = ${products[i-1].id}</li>
                    <li>Name = ${products[i-1].name}</li>
                    <li>Price = ${products[i-1].price}</li>
                </ul>
                <a href="/cart?buy=${products[i-1].id}"><button>BUY</button></a>
            </td>
            <c:if test="${i % 3 == 0 && i != prod_len}">
                </tr><tr align="center">
            </c:if>
            <c:if test="${i == prod_len}">
                </tr>
            </c:if>
            </c:forEach>
    </table>
</c:if>
</body>
</html>
