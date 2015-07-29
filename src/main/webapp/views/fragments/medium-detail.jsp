<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
  <div class="page-header">
    <h1>${medium.name}<small>${medium.category.name}</small></h1>
  </div>
  <div class="page-content">

    <dl class="dl-horizontal">
      <dt>Link</dt>
      <dd class="medium-link"><a href="${link.getUrl()}">${link.getUrl()}</a></dd>
      <dt>Kategorie</dt>
      <dd class="medium-category">${medium.getCategory().getName()}</dd>
      <dt>Beschreibung</dt>
      <dd class="medium-description ellipsis">${medium.getDescription()} <a href="/medium/${medium.getId()}" class="readmore">mehr <i class="glyphicon glyphicon-chevron-right"></i></a></dd>
      <dt>Geteilt von</dt>
      <dd class="medium-user">${medium.getUser().getUsername()}</dd>
      <dt>Bewertung</dt>
      <dd class="medium-rating">5</dd>
    </dl>

    <form action="/medium-detail/${medium.id}/post-send" method="post">
      <input type="text" name="medium-comment" placeholder="Add comment">

      <button class="btn btn-primary" role="button">Post</button>
    </form>

  </div>

</div>