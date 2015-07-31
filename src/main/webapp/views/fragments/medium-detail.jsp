<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header" id="medium-detail">
    <h1>${medium.name} <small>${medium.category.name}</small></h1>
</div>
<div class="page-content">

    <section class="embed-responsive embed-responsive-16by9">

    <c:choose>
        <c:when test="${mediumDetailHead=='youtube'}">
            <embed wmode="window" allowfullscreen="true" type="application/x-shockwave-flash" src="${linkUrl}&showsearch=0&fs=1&rel=0&autoplay=0&amp;ap=%2526fmt%3D22"></embed>
        </c:when>
        <c:otherwise>
            <embed wmode="window" allowfullscreen="true" src="${link.getUrl()}"></embed>
        </c:otherwise>
    </c:choose>

    </section>

    <div class="row">
        <div class="col-md-9">
            <section id="medium-info-box">
                <h4>Posted on ${medium.getDate()} by ${medium.getUser().getUsername()}</h4>
                <p class="medium-description">${medium.getDescription()}</p>
            </section>
        </div>
        <div class="col-md-3">
            <section id="medium-rating">
                <form action="/medium/${medium.id}/rating-send" method="post">
                    <div class="tooltip-wrapper-rating">
                        <div class="raty">
                            <button type="submit" class="btn btn-primary btn-rating-submit">Rate</button>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>

    <section id="medium-comments">
        <h4>All comments</h4>
        <form action="/medium/${medium.id}/comment-send#medium-comments" method="post">
            <div class="tooltip-wrapper-comment">
                <textarea rows="2" type="text" class="form-control input-lg" id="comment-text" name="comment-text" placeholder="Add your comment"></textarea>
            </div>
            <button class="btn btn-primary btn-lg btn-post-comment" role="submit">Post</button>
        </form>
        <div class="comment-list">
            <h4></h4>
            <c:choose>
                <c:when test="${not empty commentList}">
                    <c:forEach var="comment" items="${commentList}">
                        <div class="well">
                            <h4>${comment.getUser().getUsername()}<small> ${comment.getTimestamp()}</small></h4>
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

<script>var mediumRating = "${mediumRating}"</script>