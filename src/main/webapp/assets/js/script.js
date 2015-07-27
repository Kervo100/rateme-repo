$(document).ready(function($) {

    /**
     * Start Plugins
     */

    screenshotPreview();

    $(".ellipsis").dotdotdot({
        ellipsis	: '... ',
        height		: 100,
        after: "a.readmore"
    });

    /**
     * End Plugins
     */

    // set userId in hidden input with id="user-id"
    var userId = 2;
    $("#user-id").value = userId; // TODO userId aus Cookie auslesen

});