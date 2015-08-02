$(document).ready(function($) {

    $(".ellipsis").dotdotdot({
        height		: 100,
        after: "a.readmore"
    });

    /**
     * Medium-Detail Checks
     */

    if(typeof mediumRating == "undefined") {
        mediumRating = 0;
    }

    var loggedIn = $.cookie("rateMe_LoggedIn");

    if ($(".score").val() != "") {
        $(".btn-rating-submit").css("display", "inline-block");
    }
    else {
        $(".btn-rating-submit").css("display", "none");
    }

    $(".raty-has-rated").raty({
        starType: 'i',
        readOnly: true,
        noRatedMsg: '',
        score: mediumRating
    });

    if (loggedIn == "false") {
        $("#comment-text").attr({
            disabled: true
        });
        $(".tooltip-wrapper-rating").tooltip({
            title: "Please login to rate",
            placement: "auto bottom"
        });
        $(".tooltip-wrapper-comment").tooltip({
            title: "Please login to add a comment",
            placement: "auto top"
        });
        $(".raty").raty({
            starType: 'i',
            readOnly: true,
            noRatedMsg: '',
            score: mediumRating
        });
        $(".btn-rating-submit").css("display", "none");
        $("#share-button").toggleClass("disabled");
        $("#tooltip-wrapper-share-button").tooltip({
            title: "Please login to share a link!",
            placement: "auto bottom"
        });
    }
    else {
        $(".raty").raty({ starType: 'i', score: mediumRating });
        $(".star-on-png").tooltip({ placement: "auto bottom"});
        $(".star-off-png").tooltip({ placement: "auto bottom"});
    }

    $("#comment-text")
        .on("focus", function(){
            $(".btn-post-comment").css("display", "inline-block");
            $(".comment-list").css("margin", "60px 0 0 0");
        })
        .on("focusout", function(){
            if(this.value == ""){
                $(".btn-post-comment").css("display", "none");
                $(".comment-list").css("margin", "0");
            }
        });

});

$(window).load(function() {
    // scrollreveal plugin!
    var config = {
        reset: true,
        move: '20px',
        delay: 'always',
        vFactor: 0.3, // required percent of an element be visible to trigger animation.
        easing:   'ease-in-out',
        scale:    { direction: 'up', power: '0%' },
        mobile: true
    };
    window.sr = new scrollReveal( config );
});

function editComment(commentId) {
    var commentElement = "#comment-" + commentId + " ";
    $(commentElement + ".current-comment-text").addClass("hidden");
    $(commentElement + ".comment-update").removeClass("hidden");
    $(commentElement + "form").css("margin", "0 0 48px 0");
}