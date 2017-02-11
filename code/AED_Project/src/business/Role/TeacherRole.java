/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Role;

import business.Enterprise.Enterprise;
import business.Organization.Organization;
import business.Organization.Community.TeacherOrganization;
import business.UserAccount.UserAccount;
import business.ecosystem.Ecosystem;
import business.person.ProfessionalsProfile;
import javax.swing.JPanel;
import userInterface.teacher.AddTeacherJPanel;
import userInterface.teacher.TeacherEntryWorkAreaJPanel;
import userInterface.teacher.TeacherHelpOfferingPortalJPanel;

/**
 *
 * @author ilanigam17
 */
public class TeacherRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount acc, Organization org, Enterprise enterprise, Ecosystem ecosystem) {
        if (acc.getPerson()!=null && acc.getPerson().getEmailId()!=null) {
            return new TeacherEntryWorkAreaJPanel(userProcessContainer, acc, (TeacherOrganization) org, enterprise, ecosystem);
        } else {
            return new AddTeacherJPanel(userProcessContainer, (TeacherOrganization) org, acc, null, enterprise, ecosystem);
        }
    }

}
