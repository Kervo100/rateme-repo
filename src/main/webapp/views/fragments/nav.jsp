<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">RateMe</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <form class="navbar-form navbar-left" role="search">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Suche">
          <span class="btn btn-default input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
        </div>
      </form>
      <ul class="nav navbar-nav">
        <li><a href="/share" class="btn btn-default">Teilen</a></li>
      </ul>
<<<<<<< HEAD
      <form class="navbar-form navbar-right">
        <div class="form-group">
          <input type="email" class="form-control" id="email-login" placeholder="Email">
        </div>
        <div class="form-group">
          <input type="password" class="form-control" id="password-login" placeholder="Passwort">
        </div>
        <button href="login" type="submit" class="btn btn-default">Einloggen</button>
          <input type="button" class="btn btn-default" onClick="parent.location='register'" value='Register'>
=======
      <form class="navbar-form navbar-right" action="/login" method="post">
        <input type="text" id="currentPageID" name="currentPage" style="display: none;" value="${page}">

        <%
        String loginCookie = (String ) request.getAttribute("loginCookie");
        if(loginCookie == null) { loginCookie = "false"; }
        if (loginCookie.equals("false")) {%>
            <div class="form-group">
               <input type="email" name="email" class="form-control" id="email-login" placeholder="Email">
            </div>
            <div class="form-group">
               <input type="password" name="password" class="form-control" id="password-login" placeholder="Passwort">
            </div>
            <button type="submit" class="btn btn-default">Einloggen</button>
        <%}
        else {%>
            <div class="form-group">
              <input type="email" name="email" id="email-login" style="display: none;">
            </div>
            <div class="form-group">
              <input type="password" name="password" id="password-login" style="display: none;">
            </div>
            <button type="submit" class="btn btn-default">Ausloggen</button>
        <%}%>

>>>>>>> master
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>