/*global $, document*/
$(document).ready(function () {
    "use strict";
    $("#buy").click(function () {
        var links = "/shop/";
        $("#buying").find("input").each(function () {
            links = links + $(this).attr("id") + "=" + $(this).val() + "&";
        });
        $("#buying-form").attr("action", links);
        $(this).closest("form").validate();
        $(this).closest("form").submit();
    });
});
