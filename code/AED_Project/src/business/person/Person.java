/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.person;

import business.appointments.AppointmentHistory;
import business.appointments.Appointment;
import business.patient.Patient;
import java.util.Date;

/**
 *
 * @author ilanigam17
 */
public class Person {

    private String name;
    private int id;
    private int age;
    private Patient patient;
    private static int count = 1;
    private String stAddress;
    private String town;
    private int zipCode;
    private String occupation;
    private String emailId;
    private int areaCde;
    private long phoneNum;
    private float gpa;
    private String schoolName;
    private AppointmentHistory appointHistory;
    private Date dob;
    private Funds fundsAvail;

    public Person() {
        this.id = count;
        count += 1;
        appointHistory = new AppointmentHistory();
        fundsAvail = new Funds();

    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getAreaCde() {
        return areaCde;
    }

    public void setAreaCde(int areaCde) {
        this.areaCde = areaCde;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public AppointmentHistory getAppointHistoryList() {
        return appointHistory;
    }

    public void setAppointHistoryList(AppointmentHistory appointHistoryList) {
        this.appointHistory = appointHistoryList;
    }

    public Appointment createAppointment() {
        Appointment appointment = new Appointment();
        this.appointHistory.getAppointmentList().add(appointment);
        return appointment;
    }

    public AppointmentHistory getAppointHistory() {
        return appointHistory;
    }

    public void setAppointHistory(AppointmentHistory appointHistory) {
        this.appointHistory = appointHistory;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Funds getFundsAvail() {
        return fundsAvail;
    }

    public void setFundsAvail(Funds fundsAvail) {
        this.fundsAvail = fundsAvail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
