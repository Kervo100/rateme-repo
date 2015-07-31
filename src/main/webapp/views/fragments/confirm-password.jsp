<%--
  Created by IntelliJ IDEA.
  User: kervinzeller
  Date: 28.07.15
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>Registrieren | RateMe</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/assets/css/style.css" rel="stylesheet">
</head>
<body style="height: 10px;">


<div class="container">
  <div class="row">
    <div class="col-md-6" style="height: 650px;">

      <form class="form-horizontal" action="/register-user" method="POST">
        <fieldset>
          <div id="legend">
            <legend class="">Register</legend>
          </div>


          <div class="control-group">
            <label class="control-label" for="username">Username</label>
            <div class="controls">
              <input type="text" id="username" name="username" placeholder="" class="form-control input-lg" pattern=".{5,}" required title="5 characters minimum" required>
              <p class="help-block">Username can contain any letters or numbers, without spaces</p>
            </div>
          </div>



          <div class="control-group">
            <label class="control-label" for="email">E-mail</label>
            <div class="controls">
              <input type="email" id="email" name="email" placeholder="" class="form-control input-lg" required>
              <p class="help-block">Please provide your E-mail</p>
            </div>
          </div>



          <div class="control-group">
            <label class="control-label" for="password">Password</label>
            <div class="controls">
              <input type="password" id="password" name="password" placeholder="" class="form-control input-lg" pattern=".{6,}" required title="6 characters minimum" required >
              <p class="help-block">Enter your desired Password</p>
            </div>
          </div>



          <div class="panel-body">
            <div class="alert alert-danger">
              <a class="close" data-dismiss="alert" href="#">×</a>Please make sure your password matches the confirmation!
            </div>
          </div>



          <div class="control-group">
            <label class="control-label" for="passwordConfirm">Password (Confirm)</label>
            <div class="controls">
              <input type="password" id="passwordConfirm" name="passwordConfirm" placeholder="" class="form-control input-lg" required>
              <p class="help-block">Please confirm Password</p>
            </div>
          </div>



          <div class="control-group">
            <!-- Button -->
            <div class="controls">
              <button class="btn btn-default btn-primary">Register</button>
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

