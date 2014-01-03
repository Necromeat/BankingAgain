
package commands;

import DTO.CustomerDTO;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;
import servlets.Factory;

/**
 *
 * @author Aaron, Kris, Lars, Timea, @08/11/2013
 */
public class ViewCustomerCommandByEmail extends TargetCommand {

    public ViewCustomerCommandByEmail(String target, SecurityRole role) {
        super(target, role);
    }

    

    @Override
    public String execute(HttpServletRequest request) {
        
        String idAsstr= request.getParameter("custemail");
        CustomerDTO cust = Factory.getInstance().getBankController().getCustomerByEmail(idAsstr);
        request.setAttribute("customer", cust);
        return super.execute(request); //To change body of generated methods, choose Tools | Templates.
    }
    
}
