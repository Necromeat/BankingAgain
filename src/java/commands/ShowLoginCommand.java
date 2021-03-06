
package commands;

import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

/**
 *
 * @author Aaron
 */

// This command is necessary because if give us a type to test against
public class ShowLoginCommand extends TargetCommand {

    public ShowLoginCommand(String target, SecurityRole role) {
        super(target, role);
    }

    @Override
    public String execute(HttpServletRequest request) {
        return super.execute(request); 
    }
    
}
