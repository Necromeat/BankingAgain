/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commands;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

/**
 *
 * @author Andrew
 */
public class BanktellerViewCustomerPageCommand extends TargetCommand{

    public BanktellerViewCustomerPageCommand(String target, SecurityRole role) {
        super(target, role);
    }
    
      @Override
    public String execute(HttpServletRequest request) {
         request.setAttribute("date", new Date());
        return super.execute(request); //To change body of generated methods, choose Tools | Templates.
    }
}
