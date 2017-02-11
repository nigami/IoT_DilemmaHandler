/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization.Community;

import business.Organization.Organization;
import business.Role.Role;
import business.Role.TeacherRole;
import business.person.ProfessionalsDirectory;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class TeacherOrganization extends Organization {

    private ProfessionalsDirectory teacherDirectory;

    public TeacherOrganization() {
        super(Organization.CommunityOrgType.Teacher.getValue());
        teacherDirectory = new ProfessionalsDirectory();
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new TeacherRole());
        return roleList;
    }

    public ProfessionalsDirectory getTeacherDirectory() {
        return teacherDirectory;
    }

    public void setTeacherDirectory(ProfessionalsDirectory teacherDirectory) {
        this.teacherDirectory = teacherDirectory;
    }

}
