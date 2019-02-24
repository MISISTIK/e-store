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
                    <div id='emailErr' class='invalid-feedback'></div>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--NAME-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Name</span>
                    </div>
                    <input name='name' <c:if test='${not empty param.name}'>value='${param.name}'</c:if> class='form-control' placeholder='Name' type='text'>
                    <div id='nameErr' class='invalid-feedback'></div>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--PASS-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Pass</span>
                    </div>
                    <input name='pass' class='form-control' placeholder='******' type='password'>
                    <div id='passErr' class='invalid-feedback'></div>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--PASS CONFIRM-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Repass</span>
                    </div>
                    <input name='repass' class='form-control' placeholder='******' type='password'>
                    <div id='repassErr' class='invalid-feedback'></div>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--AGE CONFIRM-->
            <div class='form-group'>
                <div class='input-group'>
                    <div class='input-group-prepend'>
                        <span class='input-group-text'>Age</span>
                    </div>
                    <input name='age' <c:if test='${not empty param.age}'>value='${param.age}'</c:if> class='form-control' placeholder='Age' type='number'>
                    <div id='ageErr' class='invalid-feedback'></div>
                </div> <!-- input-group.// -->
            </div> <!-- form-group// -->
            <!--GENDER-->
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Gender</label>
                </div>
                <select name='gender' class="custom-select" id="inputGroupSelect01">
                    <option value="Male" <c:if test= '${empty param.gender ||  param.gender == "Male"}'>selected</c:if>>Male</option>
                    <option value="Female" <c:if test= '${param.gender == "Female"}'>selected</c:if> >Female</option>
                </select>
                <div id='genderErr' class='invalid-feedback'></div>
            </div>
            <!--ADDRESS-->
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect02">Address</label>
                </div>
                <select name='address' class="custom-select" id="inputGroupSelect02">
                    <option value='Ukraine' <c:if test= '${empty param.address ||  param.address == "Ukraine"}'>selected</c:if>>Ukraine</option>
                    <option value='Uganda' <c:if test= '${param.address == "Uganda"}'>selected</c:if>>Uganda</option>
                    <option value="Hawaii" <c:if test= '${param.address == "Hawaii"}'>selected</c:if>>Hawaii</option>
                </select>
                <div id='addressErr' class='invalid-feedback'></div>
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
        var emailM = '${emailError}';
        var passM = '${passError}';
        var repassM = '${repassError}';
        var nameM = '${nameError}';
        var ageM = '${ageError}';
        var genderM = '${genderError}';
        var addressM = '${addressError}';

        if (m !== '' && t !== '') {
            $('#infoAlert').addClass(t).removeAttr('hidden').html(m);
        }

        if (emailM !== '') {
            $('input[name=email]').addClass('is-invalid');
            $('#emailErr').html(emailM);
        }

        if (passM !== '') {
            $('input[name=pass]').addClass('is-invalid');
            $('#passErr').html(passM);
        }

        if (repassM !== '') {
            $('input[name=repass]').addClass('is-invalid');
            $('#repassErr').html(repassM);
        }

        if (nameM !== '') {
            $('input[name=name]').addClass('is-invalid');
            $('#nameErr').html(nameM);
        }

        if (ageM !== '') {
            $('input[name=age]').addClass('is-invalid');
            $('#ageErr').html(ageM);
        }

        if (genderM !== '') {
            $('input[name=gender]').addClass('is-invalid');
            $('#genderErr').html(genderM);
        }

        if (addressM !== '') {
            $('input[name=address]').addClass('is-invalid');
            $('#addressErr').html(addressM);
        }
    });
</script>
