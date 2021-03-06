package chatProgram;

import java.io.IOException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author krismaini
 */
@ServerEndpoint("/echo")
public class EchoEndPoint  {

   //public static List<Session>  //...or even <String> if you just want usernames
    
   @OnMessage
   public void onMessage(Session session, String msg) {
      
      
      try {
         for (Session s:session.getOpenSessions()) {
                 if(s.isOpen()){
                    s.getBasicRemote().sendText(msg);
                 }
      }
         // Perhaps to get a list of all users: session.getOpenSessions()
         // iterate over all open sessions and "session.getBasicRemote().sendText(msg);" to each
      } catch (IOException e) {  
          System.out.println("Caught exception: "+e.getMessage());
      }
     
   }

   
   

    
}

