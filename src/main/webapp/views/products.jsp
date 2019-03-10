<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="prod_len" value="${fn:length(products)}"/>

<%@ include file='parts/header.jsp' %>

<c:if test="${prod_len > 0}">
    <div class="card-columns ">
        <c:forEach var="i" begin="1" end="${prod_len}">
            <div class="card">
                <a href="?id=${products[i-1].id}">
                    <img class="card-img-top p-3" src='/static/img/${products[i-1].id}.jpg' style='resize: both'>
                </a>
                <div class="card-body">
                    <h4 class="card-title text-center">${products[i-1].name}</h4>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Description: ${products[i-1].description}</li>
                        <li class="list-group-item">Price: ${products[i-1].price}</li>
                        <a class="btn btn-outline-primary mt-3" onclick="send(${products[i-1].id})">Add to cart</a>
                    </ul>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<%@ include file='parts/footer.jsp' %>
<script type="text/javascript">
    function send(pid) {
        var product_id = {
            id: pid
        };

        $.ajax({
            url: '/cart',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(product_id),
            success: function (resp) {
                $('#cart_size').html(resp.size);
            }
        });
    }
</script>