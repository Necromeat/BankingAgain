/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import commands.BankTellerAddCustomerCommand;
import commands.BanktellerViewCustomerPageCommand;
import commands.BanktellerAddCustomerPageCommand;
import commands.TargetCommand;
import commands.Command;
import commands.CustomerDetailCommand;
import commands.CustomerShowAccountHistory;
import commands.CustomerViewAccountsCommand;
import commands.LoginCommand;
import commands.LogoutCommand;
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
    private final String customerfolder="/customer/";
    private final String bankfolder="/bankteller/";
    
    private Map<String, Command> commands = new HashMap<>();
    
    private Factory()
    {
        //Login
          commands.put("showLogin", new ShowLoginCommand("/login/login.jsp",SecurityRole.All));
        Map<SecurityRole,String> rolePages = new HashMap<>();
        rolePages.put(SecurityRole.Customer,  customerfolder+"CustomerMain.jsp");
        rolePages.put(SecurityRole.BankTeller, bankfolder+"BankTellerMain.jsp");
        rolePages.put(SecurityRole.All, "/main.jsp");
        
        commands.put("login", new LoginCommand(rolePages,"/login/login.jsp"));
        //Logout
        commands.put("logout", new LogoutCommand("/main.jsp", SecurityRole.All));
        
        //All
        commands.put("main", new TargetCommand("/main.jsp",SecurityRole.All));
       //Customer
          commands.put("customerdetail", new CustomerDetailCommand(customerfolder+"customerViewDetails.jsp",SecurityRole.Customer));
          commands.put("customerviewaccounts", new CustomerViewAccountsCommand(customerfolder+"customerViewAccounts.jsp",SecurityRole.Customer));
          commands.put("customershowaccounthistory", new CustomerShowAccountHistory(customerfolder+"customerAccountHistory.jsp",SecurityRole.Customer));
          commands.put("Chat", new CustomerDetailCommand(customerfolder+"customerChatPage.jsp",SecurityRole.Customer));
       //BankTeller
         commands.put("viewcustomerpage", new BanktellerViewCustomerPageCommand(bankfolder+"viewCustomer.jsp",SecurityRole.BankTeller));
         commands.put("addcustomerpage", new BanktellerAddCustomerPageCommand(bankfolder+"addCustomer.jsp",SecurityRole.BankTeller));
         commands.put("addcustomer", new BankTellerAddCustomerCommand(bankfolder+"addCustomer.jsp",SecurityRole.BankTeller));
        
        
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
