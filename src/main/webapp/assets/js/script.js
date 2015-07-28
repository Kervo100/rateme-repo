$(document).ready(function($) {

    /**
     * Start Plugins
     */

    screenshotPreview();

    $(".ellipsis").dotdotdot({
        height		: 100,
        after: "a.readmore"
    });

    /*$('.medium-link .pathname').each(function() {
        var path = $(this).html().split( '/' );
        if ( path.length > 1 ) {
            var name = path.pop();
            $(this).html( path.join( '/' ) + '<span class="filename">/' + name + '</span>' );
            $(this).dotdotdot({
                after: 'span.filename',
                wrap: 'letter'
            });
        }
    });

    /**
     * End Plugins
     */

    // set userId in hidden input with id="user-id"
    console.log($.cookie("rateMe_LoggedIn"));
    $("#user-id").value = $.cookie("rateMe_LoggedIn"); // TODO userId aus Cookie auslesen

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