<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jstl/fmt' %>
<%@ page isELIgnored='false' %>
<%@ page contentType='text/html; charset=UTF-8' %>
<c:set var="prod_len" value="${fn:length(products)}"/>
<html>
<head>
    <link rel='stylesheet' type="text/css" href='<c:url value='/static/css/bootstrap.min.css'/>'/>
    <link rel='stylesheet' type="text/css" href='<c:url value='/static/css/payment.css'/>'/>

</head>
<body>
<%@ include file='parts/navbar.jsp' %>

<table align="center" class="mt-3">
    <tr>
        <td>
            <pre id="checkout_content">
            </pre>
        </td>
    </tr>
</table>

<div class="container text-center">
    <div class="paymentCont">
        <div class="headingWrap">
            <h3 class="headingTop text-center">Select Your Payment Method</h3>
        </div>
        <div class="paymentWrap">
            <div class="btn-group paymentBtnGroup btn-group-justified" data-toggle="buttons">
                <label class="btn paymentMethod">
                    <div class="method visa"></div>
                    <input type="radio" name="options" checked>
                </label>
                <label class="btn paymentMethod">
                    <div class="method master-card"></div>
                    <input type="radio" name="options">
                </label>
                <label class="btn paymentMethod">
                    <div class="method amex"></div>
                    <input type="radio" name="options">
                </label>
                <label class="btn paymentMethod">
                    <div class="method vishwa"></div>
                    <input type="radio" name="options">
                </label>
                <label class="btn paymentMethod">
                    <div class="method ez-cash"></div>
                    <input type="radio" name="options">
                </label>
            </div>
        </div>
        <div class="btn-group btn-group-justified">
            <a href="/products" class="btn btn-warning pull-left btn-fyi"><span class="glyphicon glyphicon-chevron-left"></span>
                CONTINUE
                SHOPPING
            </a>
            <div class="btn btn-success pull-right btn-fyi">PAY<span
                    class="glyphicon glyphicon-chevron-right"></span></div>
        </div>
    </div>
</div>

<script src='<c:url value='/static/js/bootstrap.min.js'/>'></script>
<script src='<c:url value='/static/js/jquery-3.3.1.min.js'/>'></script>
<script src='<c:url value='/static/js/popper.min.js'/>'></script>
<script src='<c:url value='/static/js/myscript.js'/>'></script>

<script type="text/javascript">
    <c:if test="${not empty sessionScope.user}">
    $(document).ready(function () {
        $.ajax({
            url: '/cart/size',
            type: 'post',
            contentType: 'application/json',
            success: function (resp) {
                var data = JSON.parse(resp);
                $('#cart_size').html(data.size);
            }
        });
    });

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });


    $.ajax({
        url: '/checkout',
        type: 'post',
        success: function (content) {
            $('#checkout_content').html(content);
        },
        error: function () {
            console.log("Error checkout");
        }
    });

    </c:if>
</script>
</body>
</html>

