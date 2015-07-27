<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="rateme.entity.User"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Registrieren | RateMe</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/style.css" rel="stylesheet">
</head>
<body>

<jsp:include page="fragments/nav.jsp" />

<jsp:useBean id="user" scope="request" class="rateme.entity.User"></jsp:useBean>


<div class="container">
    <div class="row">
        <div class="col-md-6">

            <form class="form-horizontal" action="/" method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Register</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="username">Username</label>
                        <div class="controls">
                            <input type="text" id="username" name="username" placeholder="" class="form-control input-lg" value="<jsp:getProperty name="user" property="username"/>">
                            <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="email">E-mail</label>
                        <div class="controls">
                            <input type="email" id="email" name="email" placeholder="" class="form-control input-lg" value="<jsp:getProperty name="user" property="email"/>">
                            <p class="help-block">Please provide your E-mail</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password">Password</label>
                        <div class="controls">
                            <input type="password" id="password" name="password" placeholder="" class="form-control input-lg" value="<jsp:getProperty name="user" property="password"/>">
                            <p class="help-block">Password should be at least 6 characters</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password_confirm">Password (Confirm)</label>
                        <div class="controls">
                            <input type="password" id="password_confirm" name="password_confirm" placeholder="" class="form-control input-lg" value="<jsp:getProperty name="user" property="password_confirm"/>">
                            <p class="help-block">Please confirm password</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button class="btn btn-success">Register</button>
                        </div>
                    </div>
                </fieldset>
            </form>

        </div>
    </div>
</div>




<script src="/assets/plugins/js/jquery-2.1.4.min.js"></script>
<script src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>