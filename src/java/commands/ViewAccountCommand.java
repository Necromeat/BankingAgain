/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import DTO.AccountDTO;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;
import servlets.Factory;

/**
 *
 * @author Aaron
 */
public class ViewAccountCommand extends TargetCommand {

    public ViewAccountCommand(String target, SecurityRole role) {
        super(target, role);
    }

    

    @Override
    public String execute(HttpServletRequest request) {
        String idAsstr= request.getParameter("accountid");
        long id = Long.parseLong(idAsstr);
        AccountDTO account = Factory.getInstance().getBankController().getAccount(id);
        request.setAttribute("account", account);
        
         
        
        return super.execute(request); //To change body of generated methods, choose Tools | Templates.
    }
    
}
