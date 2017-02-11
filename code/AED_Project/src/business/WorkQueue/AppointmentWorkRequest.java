/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.WorkQueue;

import business.appointments.Appointment;

/**
 *
 * @author ilanigam17
 */
public class AppointmentWorkRequest extends WorkRequest {

    private Appointment appointmentReq;

    public Appointment getAppointmentReq() {
        return appointmentReq;
    }

    public void setAppointmentReq(Appointment appointmentRequest) {
        this.appointmentReq = appointmentRequest;
    }

}
