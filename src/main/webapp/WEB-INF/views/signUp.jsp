<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Neighbour Sign up</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/admin/css/sb-admin-2.min.css"/>" rel="stylesheet" type="text/css">

</head>

<body class="bg-gradient-primary">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-register-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                                </div>
                                <form:form method="post" modelAttribute="user" class="user">
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <form:input path="firstName" class="form-control form-control-user" id="firstName" placeholder="First Name"/>
                                            <form:errors path="firstName" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                        </div>
                                        <div class="col-sm-6">
                                            <form:input path="lastName" class="form-control form-control-user" id="lastName" placeholder="Last Name"/>
                                            <form:errors path="lastName" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:input path="email" class="form-control form-control-user" id="email" placeholder="Email Address"/>
                                        <form:errors path="email" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                    </div>
                                    <div class="form-group">
                                        <form:input path="address" class="form-control form-control-user" id="address" placeholder="Address"/>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <form:input path="login" class="form-control form-control-user" id="login" placeholder="Login"/>
                                            <form:errors path="login" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            <form:input path="password" class="form-control form-control-user" id="password" placeholder="Password"/>
                                            <form:errors path="password" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>
                                        </div>
                                        <div class="col-sm-6">
                                            <form:input path="confirmPassword" class="form-control form-control-user" id="password" placeholder="Confirm Password"/>
                                            <form:errors path="confirmPassword" cssStyle="color: #be2617; font-size: small" cssClass="error"></form:errors>

                                        </div>
                                    </div>
                                    <c:if test="${notEqualPasswords!=null}">
                                        <div class="form-group row">
                                            <p style="color: #be2617; font-size: small">${notEqualPasswords}</p>
                                        </div>
                                    </c:if>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">Sign up</button>
                                </form:form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="forgot-password.html">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="/signIn">Already have an account? Sign in!</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="/">Second thoughts? Go back to the main page</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<!-- Bootstrap core JavaScript-->
<script src="/resources/admin/vendor/jquery/jquery.min.js"></script>
<script src="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/resources/admin/js/sb-admin-2.min.js"></script>

</body>

</html>