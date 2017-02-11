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
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author ilanigam17
 */
public abstract class Role {

    public abstract JPanel createWorkArea(JPanel paramJPanel, UserAccount paramUserAccount, Organization paramOrganization, Enterprise paramEnterprise, Ecosystem paramEcoSystem);

    public static enum RoleType {

        Admin("Admin"), Nurse("Nurse"), Receptionist("Receptionist"), Donor("Donor");

        private String value;

        private RoleType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return this.value;
        }
    }

    public String toString() {
        return getClass().getName().substring(getClass().getName().lastIndexOf(".") + 1);

    }
}
