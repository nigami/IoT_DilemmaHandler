/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Enterprise;

import business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class CommunityEnterprise extends Enterprise {

    public CommunityEnterprise(String name) {
        super(name, Enterprise.EnterpriseType.COMMUNITY);
    }

    public ArrayList<Role> getSupportedRole() {
        return this.roles;
    }
}
