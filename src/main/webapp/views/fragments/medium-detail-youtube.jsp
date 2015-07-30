<%@ page import="rateme.entity.Link" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>${medium.name} <small>${medium.category.name}</small></h1>
</div>
<div class="page-content">

    <div class="embed-responsive embed-responsive-16by9">
        <embed wmode="window" allowfullscreen="true" type="application/x-shockwave-flash" src="${linkUrl}&showsearch=0&fs=1&rel=0&autoplay=0&amp;ap=%2526fmt%3D22"></embed>
    </div>

    <div class="row">
        <div class="col-md-9">
            <section class="medium-info-box">
                <h4>Posted on ${medium.getDate()} by ${medium.getUser().getUsername()}</h4>
                <p class="medium-description">${medium.getDescription()}</p>
            </section>
        </div>
        <div class="col-md-3">
            <section class="medium-rating">
                <p>Bewertung</p>
                <p>5</p>
            </section>
        </div>
    </div>

    <section class="medium-comments">
        <h4>All comments</h4>
        <form action="/medium-detail/${medium.id}/comment-send" method="post">
            <textarea rows="2" type="text" class="form-control input-lg" name="comment-text" placeholder="Add your comment"></textarea>
            <input name="page" value="${page}" style="display: none;">
            <button class="btn btn-primary btn-lg btn-post-comment" role="submit">Post</button>
        </form>
        <div class="comment-list">
            <h4></h4>
            <c:choose>
                <c:when test="${not empty commentList}">
                    <c:forEach var="comment" items="${commentList}">
                        <div class="well">
                                ${comment.getText()}
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="well">
                        No comments
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </section>

</div>