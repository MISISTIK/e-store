<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h1>Product details: "${product.name}"</h1>
<hr>
Categories: <a href="/e-store">All</a> | <a href="?cat=1">Intel</a> | <a href="?cat=2">AMD</a> | <a href="?cat=3">Videocards</a>

<table>
    <tr>
        <td>
            <img src="/img/${param.id}.jpg" height="300">
        </td>
        <td>
            <ul>
                <li>Id = ${product.id}</li>
                <li>Name = ${product.name}</li>
                <li>Description = ${product.description}</li>
                <li>Category = ${product.category}</li>
            </ul>
        </td>
    </tr>
</table>
</body>
</html>