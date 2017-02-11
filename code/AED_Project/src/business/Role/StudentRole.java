
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Role;

import business.Enterprise.Enterprise;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import business.ecosystem.Ecosystem;
import business.person.PersonDirectory;
import javax.swing.JPanel;
import userInterface.youthEntry.AddPersonJPanel;
import userInterface.youthEntry.YouthEntryWorkAreaJPanel;

/**
 *
 * @author ilanigam17
 */
public class StudentRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount acc, Organization org, Enterprise enterprise, Ecosystem ecosystem) {
        if (acc.getUsername().equalsIgnoreCase("student") && acc.getPassword().equals("student")) {
            return new AddPersonJPanel(userProcessContainer, org.getPersonDirectory(), ecosystem, enterprise, null);
        }
        return new YouthEntryWorkAreaJPanel(userProcessContainer, acc, enterprise, ecosystem); //To change body of generated methods, choose Tools | Templates.
    }
}
