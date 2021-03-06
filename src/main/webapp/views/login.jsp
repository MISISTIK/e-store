<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page isELIgnored='false' %>
<%@ page contentType='text/html; charset=UTF-8' %>

<%@ include file='parts/header.jsp' %>

<div class='card' style='margin: auto; width: 350px;margin-top: 15%'>
    <article class='card-body' >
        <h4 class='card-title text-left mb-4 mt-1'>E-store
            <a href='/register' class='float-right btn btn-outline-primary'>Sign up</a>
        </h4>
        <hr>
        <c:if test='${not empty infoMessage}'><div class='alert alert-info' role='alert'>${infoMessage}</div></c:if>
        <form action='/login' method='post'>
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Login</span>
                    </div>
                    <input name='email' <c:if test='${not empty param.email}'>value='${param.email}'</c:if> class='form-control' placeholder='Email' type='email'>
                    <div id='emailErr' class='invalid-feedback'></div>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Pass</span>
                    </div>
                    <input name='pass' class='form-control' placeholder='******' type='password'>
                    <div id='passErr' class='invalid-feedback'></div>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <div class='form-group'>
                <button type='submit' class='btn btn-primary btn-block'>Login</button>
            </div>
        </form>
    </article>
</div>


<%@ include file='parts/footer.jsp' %>

<script>
$( document ).ready(function() {
    var emailM = '${emailError}';
    var passM = '${passError}';

    if (emailM !== '') {
        $('input[name=email]').addClass('is-invalid');
        $('#emailErr').html(emailM);
    }

    if (passM !== '') {
        $('input[name=pass]').addClass('is-invalid');
        $('#passErr').html(passM);
    }
});
</script>
