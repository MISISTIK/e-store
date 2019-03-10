<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file='parts/header.jsp' %>

<table>
    <tr>
        <td>
            <img src='<c:url value='/static/img/${param.id}.jpg'/>' height="300">
        </td>
        <td width="400">
            <div class="container ml-4">
                <h4 class="card-title text-center">${product.name}</h4>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Description: ${product.description}</li>
                    <li class="list-group-item">Price: ${product.price}</li>
                    <a class="btn btn-outline-primary mt-3" onclick="send()">Add to cart</a>
                </ul>
            </div>
        </td>
    </tr>
</table>
<%@ include file='parts/footer.jsp' %>
<script type="text/javascript">
    function send() {
        var product_id = {
            id: ${product.id}
        };
        $.ajax({
            url: '/cart',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(product_id),
            success: function (responce) {
                $('#cart_size').html(responce.size);
            },
            error: function () {
                console.log("Error");
            }
        });
    }
</script>