/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.appointments;

import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class AppointmentHistory {

    private ArrayList<Appointment> appointmentList;

    public AppointmentHistory() {
        appointmentList = new ArrayList<>();
    }

    public ArrayList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(ArrayList<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

}
