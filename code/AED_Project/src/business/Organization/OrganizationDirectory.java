/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization;

import business.Organization.Community.StudentOrganization;
import business.Organization.Community.DoctorOrganization;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class OrganizationDirectory {

    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        this.organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return this.organizationList;
    }

    public Organization createOrganization(Organization.CommunityOrgType type) {
        Organization organization = null;
        if (type.getValue().equals(Organization.CommunityOrgType.Doctor.getValue())) {
            organization = new DoctorOrganization();
            this.organizationList.add(organization);
        } else if (type.getValue().equals(Organization.CommunityOrgType.Student.getValue())) {
            organization = new StudentOrganization();
            this.organizationList.add(organization);
        }
        return organization;
    }
}
