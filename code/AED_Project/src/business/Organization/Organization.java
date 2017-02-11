/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization;

import business.Organization.Finance.SanctionManagerFinOrganization;
import business.Role.Role;
import business.UserAccount.UserAccountDirectory;
import business.WorkQueue.WorkQueue;
import business.person.PersonDirectory;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public abstract class Organization {

    private String name;
    private WorkQueue workQueue;
    private PersonDirectory personDirectory;
    private UserAccountDirectory userAccountDirectory;
    private int organizationID;
    private static int counter;
    protected ArrayList<Role> roles = null;

    public static enum CommunityOrgType {

        Admin("Admin Organization"), Doctor("Doctor Organization"), Student("Student Organization"),  Teacher("Teacher Organization");

        private String value;

        private CommunityOrgType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum FinancialOrgType {

        Admin("Admin Organization"), Sanction("Sanction Organization"), Academic("Academic Organization");

        private String value;

        private FinancialOrgType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public Organization(String name) {
        this.name = name;
        this.workQueue = new WorkQueue();
        this.personDirectory = new PersonDirectory();
        this.userAccountDirectory = new UserAccountDirectory();
        this.organizationID = counter;
        counter += 1;
        this.roles = new ArrayList();
    }

    public abstract ArrayList<Role> getSupportedRole();

    public UserAccountDirectory getUserAccountDirectory() {
        return this.userAccountDirectory;
    }

    public int getOrganizationID() {
        return this.organizationID;
    }

    public PersonDirectory getPersonDirectory() {
        return this.personDirectory;
    }

    public String getName() {
        return this.name;
    }

    public WorkQueue getWorkQueue() {
        return this.workQueue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public String toString() {
        return this.name;
    }
}
