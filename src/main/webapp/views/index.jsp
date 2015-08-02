<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>${title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">-->
    <link href="${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/plugins/css/jquery.raty.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>
<body class="main-background">

<jsp:include page="fragments/nav.jsp" />

<section id="page-content">
    <div class="container">
        <jsp:include page="fragments/${page}.jsp" />
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
          <h4 class="modal-title" id="myModalLabel">${messageTitle}</h4>
        </div>
        <div class="modal-body">
          ${message}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

  <script src="${pageContext.request.contextPath}/assets/plugins/js/jquery-2.1.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
  <script>$('#messageModal').modal('show')</script>
<%}%>

<jsp:include page="fragments/footer.jsp" />

<script src="${pageContext.request.contextPath}/assets/plugins/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/js/jquery.dotdotdot.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/js/jquery-textarea-maxlength.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/js/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/js/jquery.raty.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/js/scrollReveal.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>

<%

    String share = (String) request.getAttribute("page");

    if (share == "share") {%>

<script>
    $(document).ready(function($) {
        //Set maxlength of all the textarea (call plugin)
        $().maxlength("#medium-description", "#medium-description-counter");
    });
</script>

<%}%>

</body>
</html>