/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commands;

import DTO.CustomerDTO;
import DTO.UserDTO;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;
import servlets.Factory;

/**
 *
 * @author Andrew
 */
public class BankTellerAddCustomerCommand  extends TargetCommand {

    public BankTellerAddCustomerCommand(String target, SecurityRole role) {
        super(target, role);
    }
    
     @Override
    public String execute(HttpServletRequest request) {
     String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (!firstName.equals("")) {
            UserDTO user = new UserDTO(email, 0, password);
            servlets.Factory.getInstance().getBankController().addUser(user.getPw(), user.getEmail());
            CustomerDTO cust = new CustomerDTO(0, firstName, lastName, email);
            servlets.Factory.getInstance().getBankController().addCustomer(cust);
            return super.execute(request);
        } else {
            return super.execute(request); //To change body of generated methods, choose Tools | Templates.
        } }
}
