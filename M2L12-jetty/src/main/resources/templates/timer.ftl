<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
  <title>Timer</title>
  <script>
    function setClientTime() {
      var currentTime = new Date();
      var hours = currentTime.getHours();
      var minutes = currentTime.getMinutes();
      var seconds = currentTime.getSeconds();
      if (minutes < 10)
        minutes = '0' + minutes;
      if (seconds < 10)
        seconds = '0' + seconds;
      document.getElementById('ClientTime').innerHTML = hours + ':' + minutes + ':' + seconds;
    }

    function refresh() {
      location.reload();
    }
  </script>
</head>
<body onload='setInterval(function(){refresh()}, ${refreshPeriod} ); setClientTime();'>
<p>Client time: <span id='ClientTime'></span></p>
<p>Server time: ${time}</p>
</body>
</html>