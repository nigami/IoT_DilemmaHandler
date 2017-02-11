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
import userInterface.finance.SanctionManagerFinanceJPanel;

/**
 *
 * @author ilanigam17
 */
public class SactionManagerFinRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel paramJPanel, UserAccount paramUserAccount, Organization paramOrganization, Enterprise paramEnterprise, Ecosystem paramEcoSystem) {
        return new SanctionManagerFinanceJPanel(paramJPanel, paramUserAccount, paramOrganization, paramEnterprise, paramEcoSystem);
    }

}
