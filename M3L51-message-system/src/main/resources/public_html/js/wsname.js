var ws;

init = function() {
  ws = new WebSocket("ws://localhost:8091/name");
  ws.onopen = function (event) {

  };
  ws.onmessage = function (event) {
    var $textarea = document.getElementById("messages");
    $textarea.value = $textarea.value + event.data + "\n";
  };
  ws.onclose = function (event) {

  }
};

function sendName() {
  var messageField = document.getElementById("message");
  ws.send(messageField.value);
  messageField.value = "";
}