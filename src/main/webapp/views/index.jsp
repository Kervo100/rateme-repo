<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>${title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/style.css" rel="stylesheet">
</head>
<body>

<jsp:include page="fragments/nav.jsp" />

<section id="page-content">
    <jsp:include page="fragments/${page}.jsp" />
</section>

<jsp:include page="fragments/footer.jsp" />

<script src="/assets/plugins/js/jquery-2.1.4.min.js"></script>
<script src="/assets/plugins/js/jquery.dotdotdot.min.js"></script>
<script src="/assets/plugins/js/jquery-textarea-maxlength.js"></script>
<script src="/assets/plugins/js/jquery.cookie.js"></script>
<script src="/assets/plugins/js/scrollReveal.min.js"></script>
<script src="/assets/plugins/js/website-preview.js"></script>
<script src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/js/script.js"></script>

<%

    String share = (String ) request.getAttribute("page");

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