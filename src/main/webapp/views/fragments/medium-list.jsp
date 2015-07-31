<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="page-content">
  <div id="media-list">
    <div class="row">

        <c:if test="${not empty mediaList}">

          <c:forEach var="medium" items="${mediaList}" varStatus="status">
            <div class="thumbnail" data-sr>
              <div class="caption">
                <a href="/medium/${medium.getId()}"><h3 class="medium-title text-center">${medium.getName()}</h3></a>
                <dl class="dl-horizontal">
                  <dt>Link</dt>
                  <dd class="medium-link"><a href="${linkList[status.index].getUrl()}">${linkList[status.index].getUrl()}</a></dd>
                  <dt>Category</dt>
                  <dd class="medium-category">${medium.getCategory().getName()}</dd>
                  <dt>Description</dt>
                  <dd class="medium-description ellipsis">${medium.getDescription()} <a href="/medium/${medium.getId()}" class="readmore">mehr <i class="glyphicon glyphicon-chevron-right"></i></a></dd>
                  <dt>Shared by</dt>
                  <dd class="medium-user">${medium.getUser().getUsername()}</dd>
                </dl>
                <p class="text-center">
                  <a href="/medium/${medium.getId()}" class="btn btn-primary" role="button">Details</a>

                  <%
                      String isAdmin = (String) request.getAttribute("isAdmin");
                      if (isAdmin != null) {
                        if(isAdmin.equals("true")) {%>
                          <a href="/mediumDelete/${medium.getId()}" class="btn btn-primary" role="button">Löschen</a>
                        <%}
                      }
                  %>
                </p>
              </div>
            </div>
          </c:forEach>

        </c:if>

    </div>
  </div>
</section>

</div>