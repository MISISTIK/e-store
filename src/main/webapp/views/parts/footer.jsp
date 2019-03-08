<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<c:if test="${not empty sessionScope.user}">
    </div>
    </div>
</c:if>


<script src='<c:url value='/static/js/bootstrap.min.js'/>'></script>
<script src='<c:url value='/static/js/jquery-3.3.1.min.js'/>'></script>
<script src='<c:url value='/static/js/popper.min.js'/>'></script>
<script src='<c:url value='/static/js/myscript.js'/>'></script>

<script>
    <c:if test="${not empty sessionScope.user}">
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
    </c:if>
</script>
</body>
</html>
