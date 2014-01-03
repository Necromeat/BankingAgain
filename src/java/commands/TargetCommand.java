/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

/**
 *
 * @author hsty
 */
public class TargetCommand implements Command {
    private final String target;
    private SecurityRole role;

    public TargetCommand(String target,SecurityRole role) {
        this.target = target;
        this.role=role;
    }

    public SecurityRole getRole() {
        return role;
    }
    @Override
    public String execute(HttpServletRequest request) {
   return target;
           }

   
  
    
    
    
}
