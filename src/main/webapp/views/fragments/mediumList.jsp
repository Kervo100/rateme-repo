<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
  <section class="page-content">
    <div class="row">
      <div class="col-sm-6 col-md-4">
        <div class="thumbnail">
          <div class="caption">
            <h3 class="medium-title text-center">Medium Titel</h3>
            <dl class="dl-horizontal">
              <dt>Link</dt>
              <dd class="medium-link"><a href="www.youtube.com">www.youtube.com</a></dd>
              <dt>Kategorie</dt>
              <dd class="medium-category">Video</dd>
              <dt>Beschreibung</dt>
              <dd class="medium-description">Lorem Ipsum</dd>
              <dt>Geteilt von</dt>
              <dd class="medium-user">Max Mustermann</dd>
            </dl>
            <p class="text-center"><a href="#" class="btn btn-primary" role="button">Details</a></p>
          </div>
        </div>
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