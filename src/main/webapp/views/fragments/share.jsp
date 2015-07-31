<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container" style="height: 700px;">
  <!--<section class="page-header"><h1>Share Media <small> Share any video or picture you like with others</small></h1></section>-->
  <div id="legend">
  <legend class="">Share Media</legend>
  </div>
  <br><br>
  <section class="page-content">
        <form class="form-horizontal" action="/medium-shared" method="post">

          <div class="form-group">
            <div class="col-md-12">
              <input type="text" maxlength="100" class="form-control input-lg" id="medium-title" name="medium-title" placeholder="Title" required>
            </div>
          </div>

          <div class="form-group">
            <div class="col-md-10">
              <input type="text" maxlength="1000" class="form-control input-lg" id="medium-link" name="medium-link" placeholder="Link" required>
            </div>
            <div class="col-md-2">
              <select class="form-control input-lg" id="medium-category" name="medium-category">
                <option value="1">Video</option>
                <option value="2">Audio</option>
                <option value="3">Bild</option>
              </select>
            </div>
          </div>

          <div class="form-group-lg" style="padding: 15px 0">
            <textarea maxlength="2000" class="form-control" rows="6" id="medium-description" name="medium-description" placeholder="Description"></textarea>
            <span class="text-muted" id="medium-description-counter"></span>
          </div>

          <div class="form-group">
            <div class="col-md-12">
              <button type="submit" class="btn btn-default btn-primary" id="medium-share-button">Share Media</button>
            </div>
          </div>

          <input id="user-id" name="user-id" style="display: none;" value="1">

        </form>
  </section>
</div>