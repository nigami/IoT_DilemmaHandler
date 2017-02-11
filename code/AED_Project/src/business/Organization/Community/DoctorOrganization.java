/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization.Community;

import business.Organization.Organization;
import business.Role.DoctorRole;
import business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class DoctorOrganization extends Organization {

    public DoctorOrganization() {
        super(Organization.CommunityOrgType.Doctor.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new DoctorRole());
        return roleList;//To change body of generated methods, choose Tools | Templates.
    }
}
