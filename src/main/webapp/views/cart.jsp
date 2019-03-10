<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jstl/fmt' %>
<%@ page isELIgnored='false' %>
<%@ page contentType='text/html; charset=UTF-8' %>
<c:set var="prod_len" value="${fn:length(products)}"/>
<html>
<head>
    <link rel='stylesheet' type="text/css" href='<c:url value='/static/css/bootstrap.min.css'/>'/>
    <link rel='stylesheet' type="text/css" href='<c:url value='/static/css/style.css'/>'/>

</head>
<body>
<%@ include file='parts/navbar.jsp' %>

<div class="container">
    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <th style="width:50%">Product</th>
            <th style="width:10%">Price</th>
            <th style="width:8%">Quantity</th>
            <th style="width:22%" class="text-center">Subtotal</th>
            <th style="width:10%"></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${prod_len > 0}">
            <c:set var = "id" value = "1"/>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td data-th="Product">
                        <div class="row">
                            <div class="col-sm-3 hidden-xs"><img src='/static/img/${product.key.id}.jpg' width="50"
                                                                 height="50"
                                                                 class="img-responsive"/></div>
                            <div class="col-sm-9">
                                <h5 class="nomargin">${product.key.name}</h5>
                                <p>${product.key.description}</p>
                            </div>
                        </div>
                    </td>
                    <td data-th="Price" id = "price_${id}">${product.key.price}</td>
                    <td data-th="Quantity">
                        <div class="custom-control-inline" >
                            <button onclick="minus(${id},${product.key.id})" class="button btn btn-outline-danger">-</button>
                            <input id="qty_${id}" style="width: 60px;" type="number" class="form-control text-center" value="${product.value}" disabled>
                            <button onclick="plus(${id},${product.key.id})" class="button btn btn-outline-success">+</button>
                        </div>
                    </td>
                    <td data-th="Subtotal" class="text-center" id="subtotal_${id}">1.99</td>
                    <td class="actions" data-th="">
                        <a class="btn btn-danger btn-sm" href="/cart/remove/${product.key.id}">Remove</a>
                    </td>
                </tr>
                <c:set var = "id" value = "${id+1}"/>
            </c:forEach>
        </c:if>
        </tbody>
        <tfoot>
        <tr>
            <td><a href="/products" class="btn btn-warning">Continue Shopping</a></td>
            <td colspan="2" class="hidden-xs"></td>
            <td class="hidden-xs text-center" id="total"><strong></strong></td>
            <td><a href="/checkout" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a>
            </td>
        </tr>
        </tfoot>
    </table>
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
        recalc();
    });
    
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });

    function minus(id,pid) {
        var eqty = $('#qty_'+id);
        var qty = Number(eqty.val());
        if (qty !== 1) {
            $.ajax({
                url: '/cart/minus/'+pid,
                type: 'post',
                success: function () {
                    $('#qty_'+id).val(qty-1);
                    recalc();
                },
                error: function(){
                    console.log("Error minus");
                }
            });
        }
    }

    function plus(id,pid) {
        var eqty = $('#qty_'+id);
        var qty = Number(eqty.val());
        if (qty < 100) {
            $.ajax({
                url: '/cart/plus/'+pid,
                type: 'post',
                success: function () {
                    $('#qty_'+id).val(qty + 1);
                    recalc();
                },
                error: function(){
                    console.log("Error plus");
                }
            });
        }
    }

    function recalc() {
        var sum = 0;
        for (var i = 1; i < ${prod_len}+1;i++) {
            var esub = $('#subtotal_'+i);
            var subtotal = Number($('#price_'+i).html()) * $('#qty_'+i).val();
            esub.html("" + subtotal);
            sum += Number(esub.html());
        }
        $('#total').html("<strong>Total " + sum +"</strong>");
    }
    </c:if>
</script>
</body>
</html>

