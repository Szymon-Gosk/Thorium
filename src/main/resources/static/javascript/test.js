$(document).ready(function() {
    'use strict';

    let obj = $(".toRender")

    $('.target').html(katex.renderToString(obj.text() + "\\\\", {
        throwOnError: false,
    }));

    obj.css('display', 'none')

    console.log('done')
})