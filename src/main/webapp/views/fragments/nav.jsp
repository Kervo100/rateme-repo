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
      <a class="navbar-brand" href="/">Rate Me</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <form class="navbar-form navbar-left" role="search">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Search">
          <span class="btn btn-default input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
        </div>
          <input type="button" name="share" class="btn btn-default btn-primary" value="Share" onClick="parent.location='share'" />

      <ul class="nav navbar-nav">
       <!-- <li><a href="/share" class="btn btn-default btn-primary">Share</a></li>-->
      </ul>
      </form>

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
               <input type="password" name="password" class="form-control" id="password-login" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default btn-primary">Login</button>
            <input type="button" name="register" class="btn btn-default btn-primary" value="Register" onClick="parent.location='register'" />
        <%}
        else {%>
            <div class="form-group">
              <input type="email" name="email" style="display: none;">
            </div>
            <div class="form-group">
              <input type="password" name="password" style="display: none;">
            </div>
            <div class="form-group">
              <label style="color: #FFFFFF;">${loginCookie}</label>
            </div>
            <button type="submit" class="btn btn-default btn-primary">Logout</button>
        <%}%>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>