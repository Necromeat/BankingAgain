<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script>
    var ws = new WebSocket("ws://localhost:8080/DailyBanking/echo");
                ws.onopen = function(evt)    { 
                    $("#msgall").append("Connection open ..."+'\n'); 
                }; 
                ws.onmessage = function(evt) {
                    $("#msgall").append(evt.data+'\n');
                }; 
                ws.onclose = function(evt)   { 
                    $("#msgall").append("Connection closed."+'\n'); 
                };
            $(document).ready(function(){
                $("#btn").click(function(){
                    ws.send($("#msgsend").val());
                    $("#msgsend").val("");
                });
            });
</script>
<div id="main">
    <p id="username">${custemail} is currently logged in</p>
    
<div id="chatpanel">
    <h2>Web socket test</h2><br>
    <textarea id='msgall' rows='10' cols="50"></textarea><br/>
    <input type="text" id='msgsend'><button id='btn'>Send</button>
</div>
    
</div>
</html>
