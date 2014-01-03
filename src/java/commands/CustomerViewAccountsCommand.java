package commands;


import DTO.AccountDTO;
import DTO.CustomerDTO;
import commands.TargetCommand;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;
import servlets.Factory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krismaini
 */
public class CustomerViewAccountsCommand extends TargetCommand{

    public CustomerViewAccountsCommand(String target, SecurityRole role) {
        super(target, role);
    }
    @Override
    public String execute(HttpServletRequest request) {
        CustomerDTO cust = Factory.getInstance().getBankController().getCustomer();
        request.setAttribute("customer", cust);
        Collection<AccountDTO> custAccounts = cust.getAccounts();
        request.setAttribute("custAccounts", custAccounts);
        return super.execute(request);
    }
}
