<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="error-template">
        <h1>
          Oops!</h1>
        <h2>
          404 Seite nicht gefunden</h2>
        <div class="error-details">
          Sorry, ein Fehler ist aufgetreten. Diese Seite kann nicht gefunden werden!
        </div>
        <div class="error-actions">
          <a onclick="history.back();history.back()" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span> Zurück</a>
          <a href="mailto:moritz.ellmers@web.de" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Support</a>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
