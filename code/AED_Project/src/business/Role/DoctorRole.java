/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Role;

import business.Enterprise.Enterprise;
import business.Organization.Community.DoctorOrganization;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import business.ecosystem.Ecosystem;
import javax.swing.JPanel;
import userInterface.doctor.DoctorWorkAreaJPanel;

/**
 *
 * @author ila
 */
public class DoctorRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, Ecosystem ecoSystem) {
        return new DoctorWorkAreaJPanel(userProcessContainer, account, (DoctorOrganization) organization, ecoSystem);
    }

}
