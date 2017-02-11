/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.person;

import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class ProfessionalsDirectory {

    private ArrayList<ProfessionalsProfile> teacherList;

    public ProfessionalsDirectory() {
        teacherList = new ArrayList<>();
    }

    public ArrayList<ProfessionalsProfile> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(ArrayList<ProfessionalsProfile> teacherList) {
        this.teacherList = teacherList;
    }

    public ProfessionalsProfile addTeacherProfile() {
        ProfessionalsProfile teacherProfile = new ProfessionalsProfile();
        teacherList.add(teacherProfile);
        return teacherProfile;
    }
}
