$(document).ready(function() {
    'use strict';

    $('.toRender').each( function(i, e) {
        $(this).html(katex.renderToString($(this).text() + "\\\\", {
            throwOnError: false,
        }));
    });

})