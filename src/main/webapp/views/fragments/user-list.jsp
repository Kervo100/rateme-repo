<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
  <section class="page-content">
    <div id="user-list">
      <div class="row">

          <c:if test="${not empty userList}">

            <c:forEach var="user" items="${userList}" varStatus="status">
              <div class="thumbnail" data-sr>
                <div class="caption">
                  <h3 class="user-title text-center">${user.getUsername()}</h3>
                  <dl class="dl-horizontal">
                    <dt>Email</dt>
                    <dd class="TODO CSS">${user.getEmail()}</dd>
                    <dt>Passwort</dt>
                    <dd class="TODO CSS">${user.getPassword()}</dd>
                    <dt>Admin</dt>
                    <dd class="TODO CSS">${user.isAdmin()}<a href="/userToogleAdmin/${user.getId()}" class="btn btn-default" role="button">Toggle</a></dd>
                    <dt>Gesperrt</dt>
                    <dd class="TODO CSS">${user.isBlocked()}<a href="/userToogleBlocked/${user.getId()}" class="btn btn-default" role="button">Toggle</a></dd>
                  </dl>
                  <p class="text-center">
                    <a href="/userDelete/${user.getId()}" class="btn btn-primary" role="button">Löschen</a>
                  </p>
                </div>
              </div>
            </c:forEach>

          </c:if>

      </div>
    </div>
  </section>
</div>