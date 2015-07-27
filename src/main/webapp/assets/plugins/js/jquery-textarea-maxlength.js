/**
 * Created by Mo on 25.07.2015.
 */
$(function(){
    $.fn.maxlength = function(element, elementCounter){

        // initialization
        var length = $(element).val().length;
        var maxLength = $(element).attr("maxlength");
        $(elementCounter).html(length + "/" + maxLength);

        $(element).on("input", function() {
            length = this.value.length;
            if(length > maxLength) {
                event.preventDefault();
            }
            else {
                $(elementCounter).html(length + "/" + maxLength);
            }
        });
    }
});