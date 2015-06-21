$(function () {
    var host = window.location.hostname;
    var port = window.location.port;
    var ws = new WebSocket('ws://'+ host +':'+ port +'/websocket');
    ws.onopen = function() {
        ws.send('hello');
    };
    ws.onmessage = function (evt) {
        $('#display-msg').append('<div>'+ evt.data +'</div>');
    };

    $('#btn-send').click(function () {
        var msg = $('#input-msg').val();
        console.log(msg);
        ws.send(msg);
    });

});

