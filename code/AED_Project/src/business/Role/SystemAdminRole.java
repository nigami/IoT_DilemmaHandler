/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Role;

import business.Enterprise.Enterprise;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import business.ecosystem.Ecosystem;
import javax.swing.JPanel;
import userInterfaceMain.SystemAdminWorkAreaJPanel;

/**
 *
 * @author ilanigam17
 */
public class SystemAdminRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount paramUserAccount, Organization paramOrganization, Enterprise paramEnterprise, Ecosystem system) {
        return new SystemAdminWorkAreaJPanel(userProcessContainer, system);
    }

}
