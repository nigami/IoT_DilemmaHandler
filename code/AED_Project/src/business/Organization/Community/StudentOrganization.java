/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization.Community;

import business.Organization.Organization;
import business.Role.Role;
import business.Role.StudentRole;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class StudentOrganization extends Organization {

    public StudentOrganization() {
        super(Organization.CommunityOrgType.Student.getValue());
    }

    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new StudentRole());
        return roleList;
    }
}
