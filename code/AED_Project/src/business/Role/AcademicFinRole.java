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
import javax.swing.JPanel;
import userInterface.finance.AcademicFinanceJPanel;

/**
 *
 * @author ilanigam17
 */
public class AcademicFinRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount userAccount, Organization organization, Enterprise enterprise, Ecosystem ecoSystem) {
        return new AcademicFinanceJPanel(userProcessContainer, userAccount, organization, enterprise, ecoSystem);

    }

}
