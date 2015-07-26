$(document).ready(function($) {

    /**
     * Start Plugins
     */

    //Set maxlength of all the textarea (call plugin)
    $().maxlength("#medium-description", "#medium-description-counter");

    screenshotPreview();

    // set userId in hidden input with id="user-id"
    var userId = 2;
    $("#user-id").value = userId; // TODO userId aus Cookie auslesen

});