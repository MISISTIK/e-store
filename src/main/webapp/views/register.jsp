<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="parts/header.jsp" %>
<div class='card' style='margin: auto; width: 400px;margin-top: 15%'>
    <article class='card-body'>
        <h4 class='card-title text-left mb-4 mt-1'>E-store Registration
            <a href='/login' class='float-right btn btn-outline-primary'>Login</a>
        </h4>
        <hr>
        <div id='infoAlert'class='alert' role='alert' hidden></div>
        <form action="/register" method="post">
            <!--LOGIN-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Login</span>
                    </div>
                    <input name='email' <c:if test='${not empty param.email}'>value='${param.email}'</c:if> class='form-control' placeholder='Email' type='email'>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--NAME-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Name</span>
                    </div>
                    <input name='name' class='form-control' placeholder='Name' type='text'>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--PASS-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Pass</span>
                    </div>
                    <input name='pass' class='form-control' placeholder='******' type='password'>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--PASS CONFIRM-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Repass</span>
                    </div>
                    <input name='repass' class='form-control' placeholder='******' type='password'>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--AGE CONFIRM-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Age</span>
                    </div>
                    <input name='age' class='form-control' placeholder='Age' type='number'>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--GENDER-->
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Gender</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01">
                    <option value="M" selected>Male</option>
                    <option value="F">Female</option>
                </select>
            </div>
            <!--ADDRESS-->
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect02">Address</label>
                </div>
                <select class="custom-select" id="inputGroupSelect02">
                    <option value="Ukraine" selected>Ukraine</option>
                    <option value="Uganda">Uganda</option>
                    <option value="Hawaii">Hawaii</option>
                </select>
            </div>
            <!--REGISTER BUTTON-->
            <div class='form-group'>
                <button type='submit' class='btn btn-primary btn-block'>Register</button>
            </div>
        </form>
    </article>
</div>
<%@ include file="parts/footer.jsp" %>

<script>
    $( document ).ready(function() {
        var m = '${alertMessage}';
        var t = '${alertType}';
        if (m != '' && t != '') {
            $('#infoAlert').addClass(t).removeAttr('hidden').html(m);
        }

        <c:if test='${not empty emailError}'>
        $('input[name=email]').addClass('is-invalid');
        var emailErr = document.getElementById('emailErr');
        emailErr.innerText = '${emailError}';
        </c:if>

        <c:if test='${not empty passError}'>
        $('input[name=pass]').addClass('is-invalid');
        var passErr = document.getElementById('passErr');
        passErr.innerText = '${passError}';
        </c:if>
    });
</script>
