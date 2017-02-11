/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Enterprise;

import business.Organization.Organization;
import business.Organization.OrganizationDirectory;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public abstract class Enterprise extends Organization {

    private EnterpriseType enterpriseType;
    private OrganizationDirectory organizationDirectory;

    public Enterprise(String name, EnterpriseType type) {
        super(name);
        this.enterpriseType = type;
        this.organizationDirectory = new OrganizationDirectory();
        this.roles = new ArrayList();
    }

    public static enum EnterpriseType {

        COMMUNITY("Community"),
        FINANCE("Financial");

        private String value;

        private EnterpriseType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return this.value;
        }
    }

    public EnterpriseType getEnterpriseType() {
        return this.enterpriseType;
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return this.organizationDirectory;
    }
}
