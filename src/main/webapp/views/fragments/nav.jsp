<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
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
      <form class="navbar-form navbar-left" role="search" action="/search">
        <div class="input-group">
            <div class="input-group-btn">
              <select class="form-control" id="medium-category" name="medium-category">
                <option value="1">Video</option>
                <option value="2">Audio</option>
                <option value="3">Bild</option>
              </select>
            </div>
          <input type="text" name="searchTerm" class="form-control" placeholder="Search">
          <span class="input-group-btn">
            <button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
          </span>
        </div>
      </form>
      <ul class="nav navbar-nav">
          <li id="tooltip-wrapper-share-button">
              <a href="/share" class="btn btn-primary" id="share-button">Share</a>
          </li>
      </ul>
      <form class="navbar-form navbar-right" action="/login" method="post">
        <input type="text" id="currentPageID" name="currentPage" style="display: none;" value="${page}">

        <%
        String loginCookie = (String ) request.getAttribute("loginCookie");
        if(loginCookie == null) { loginCookie = "false"; }
        if (loginCookie.equals("false")) {%>
            <div class="form-group">
               <input type="email" name="email" class="form-control" id="email-login" placeholder="Email" required>
            </div>
            <div class="form-group">
               <input type="password" name="password" class="form-control" id="password-login" placeholder="Password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
            <a href="/register" class="btn btn-primary">Register</a>
        <%}
        else {%>
            <div class="form-group">
              <input type="email" name="email" style="display: none;">
            </div>
            <div class="form-group">
              <input type="password" name="password" style="display: none;">
            </div>
            <div class="form-group">
              <label style="color: #FFFFFF;">${username}</label>
            </div>
            <button type="submit" class="btn btn-primary">Logout</button>

            <%
            String isAdmin = (String) request.getAttribute("isAdmin");
            if (isAdmin != null) {
              if(isAdmin.equals("true")) {%>
                <a href="/user-list" class="btn btn-primary" role="button">User-List</a>
            <%}
            }
        }%>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>