<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
  <section class="page-content">
    <div id="media-list">
      <div class="row">

          <c:if test="${not empty mediaList}">

            <c:forEach var="medium" items="${mediaList}" varStatus="status">
              <div class="thumbnail" data-sr>
                <div class="caption">
                  <h3 class="medium-title text-center">${medium.getName()}</h3>
                  <dl class="dl-horizontal">
                    <dt>Link</dt>
                    <dd class="medium-link"><a href="${linkList[status.index].getUrl()}">${linkList[status.index].getUrl()}</a></dd>
                    <dt>Kategorie</dt>
                    <dd class="medium-category">${medium.getCategory().getName()}</dd>
                    <dt>Beschreibung</dt>
                    <dd class="medium-description ellipsis">${medium.getDescription()} <a href="/medium/${medium.getId()}" class="readmore">mehr <i class="glyphicon glyphicon-chevron-right"></i></a></dd>
                    <dt>Geteilt von</dt>
                    <dd class="medium-user">${medium.getUser().getUsername()}</dd>
                  </dl>
                  <p class="text-center"><a href="/medium/${medium.getId()}" class="btn btn-primary" role="button">Details</a></p>
                </div>
              </div>
            </c:forEach>

          </c:if>

      </div>
    </div>
  </section>

  <%
    String message = (String ) request.getAttribute("message");

    if (message != null) {%>

      <!-- Modal -->
      <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Medium teilen</h4>
            </div>
            <div class="modal-body">
              ${message}
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary" data-dismiss="modal">Schließen</button>
            </div>
          </div>
        </div>
      </div>

      <script src="/assets/plugins/js/jquery-2.1.4.min.js"></script>
      <script src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
      <script>$('#messageModal').modal('show')</script>
    <%}%>

</div>