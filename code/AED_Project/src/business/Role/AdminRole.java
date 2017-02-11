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
import userInterface.enterprise.AdminEnterpriseWorkAreaJPanel;

/**
 *
 * @author ilanigam17
 */
public class AdminRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount entUserAcc, Organization paramOrganization, Enterprise enterprise, Ecosystem paramEcoSystem) {
        return new AdminEnterpriseWorkAreaJPanel(userProcessContainer, enterprise, entUserAcc);
    }

}
