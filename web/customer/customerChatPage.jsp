
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%--Header--%>
    <%@include file="../WEB-INF/jspf/Cusheader.jspf" %>
    <%--/Header--%>
<script>
      var ws =new WebSocket("wss://localhost:8080/echo");
      
    $(document).ready(function(){
  
              $("#btn").click(function(){
                  ws.onmessage($("#msgsend").val());
                  
                    
                    $("#msgsend").val("");
                });
            });
            
            function dataReady(data){
                $("#msgall").append(data.text+'\n'+"a");
            }
            
            
             ws.onopen = function(evt)    { 
                    $("#msgall").append("Connection open ..."+'\n'); 
                }; 
                ws.onmessage = function(evt) {
                        $("#msgall").append(evt.data+'\n'+"Anana");                    
                            
                    };
                    
                 
                ws.onclose = function(evt)   { 
                    $("#msgall").append("Connection closed."+'\n'); 
                };    
            
</script>


    <%--Nav--%>
    
    <div id="navigation">
        <%@include file="../WEB-INF/jspf/CusNav.jspf" %>
        </div>
    
    <%--/Nav--%>
    
    <%--Body--%>
    
    <div id="main">
        <p>Good to see you ${Customer.firstName}</p>
    
    
<div id="chatpanel">
    <h2>Web socket test</h2><br>
    <textarea id='msgall' rows='10' cols="50"></textarea><br/>
    <input type="text" id='msgsend'><button id='btn'>Send</button>
</div>
    <%--/Body--%>
    <%@include file="../WEB-INF/jspf/CusFooter.jspf" %>
   </div> 

</html>


