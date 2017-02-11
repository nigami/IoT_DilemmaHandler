/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.person;

import business.UserAccount.UserAccount;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ilanigam17
 */
public class ProfessionalsProfile extends Person {

    private ArrayList<String> courseExpertiseList;
    private UserAccount userAccount;
    private ArrayList<String> scheduleList;
    private Boolean onlyPaidMode;
    private double hourlyWage;

    public ProfessionalsProfile() {
        courseExpertiseList = new ArrayList<>();
        scheduleList = new ArrayList<>();
    }

    public ArrayList<String> getCourseExpertiseList() {
        return courseExpertiseList;
    }

    public void setCourseExpertiseList(ArrayList<String> courseExpertiseList) {
        this.courseExpertiseList = courseExpertiseList;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public ArrayList<String> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(ArrayList<String> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public void addCourseExpertise(String courseName) {
        this.courseExpertiseList.add(courseName);
    }

    public void addSchedule(String day) {
        this.scheduleList.add(day);
    }

    public String retrieveScheduleList() {
        if (scheduleList != null) {
            return scheduleList.toString();
        }
        return null;
    }

    public Boolean getOnlyPaidMode() {
        return onlyPaidMode;
    }

    public void setOnlyPaidMode(Boolean onlyPaidMode) {
        this.onlyPaidMode = onlyPaidMode;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

}
