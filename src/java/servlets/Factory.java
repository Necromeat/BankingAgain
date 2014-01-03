/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import commands.TargetCommand;
import commands.Command;
import commands.LoginCommand;
import commands.LogoutCommand;
import commands.MyPageCommand;
import commands.ShowLoginCommand;
import contract.BankDataInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;
import utils.UAgentInfo;

/**
 *
 * @author hsty
 */
public class Factory {
    BankDataInterface businessDataBean1 = lookupBusinessDataBeanRemote();
    BankDataInterface businessDataBean = lookupBusinessDataBeanRemote();
    
    private static Factory instance = new Factory();
    
    private Map<String, Command> commands = new HashMap<>();
    
    private Factory()
    {
        //Login
          commands.put("showLogin", new ShowLoginCommand("/login/login.jsp",SecurityRole.All));
        Map<SecurityRole,String> rolePages = new HashMap<>();
        rolePages.put(SecurityRole.Customer, "/customer/CustomerMain.jsp");
        rolePages.put(SecurityRole.BankTeller, "/bankTeller/BankTellerMain.jsp");
        
        //Logout
        commands.put("logout", new LogoutCommand("/all/main.jsp", SecurityRole.All));
        
        commands.put("login", new LoginCommand(rolePages,"/login/login.jsp"));
        //All
        commands.put("main", new TargetCommand("/main.jsp",SecurityRole.All));
       //Customer
        
       //BankTeller
        
        
    }

    public static Factory getInstance() {
        
               return instance;
    }
    
    public  BankDataInterface getBankController(){
        return businessDataBean;
    }

  public Command getCommand(String cmdStr, HttpServletRequest request) {
        if (cmdStr == null) {
            cmdStr = isMobileDevice(request) ? "mobileMain" : "main";
        }
     Command cmd = commands.get(cmdStr);

    //This is the most important place in terms of security
    //If you fail here your security is broken
    if (cmd instanceof TargetCommand) {
      SecurityRole requiredRole = ((TargetCommand) cmd).getRole();
      if (requiredRole != SecurityRole.All
              && !request.isUserInRole(requiredRole.toString())) {
        {
          throw new SecurityException("You don't have the necessary rights for this operation");
        }
      }
    }
    return cmd;
    }
  
   public boolean isMobileDevice(HttpServletRequest res) {
        String userAgent = res.getHeader("User-Agent");
        String httpAccept = res.getHeader("Accept");
        UAgentInfo detector = new UAgentInfo(userAgent, httpAccept);
        return detector.detectMobileQuick();
    }

    private BankDataInterface lookupBusinessDataBeanRemote() {
        try {
            Context c = new InitialContext();
            return (BankDataInterface) c.lookup("java:global/DailyBankingBusinessDataBase/BusinessDataBean!contract.BankDataInterface");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
   
}
