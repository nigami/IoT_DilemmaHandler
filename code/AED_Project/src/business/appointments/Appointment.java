/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.appointments;

import java.util.Date;

/**
 *
 * @author ilanigam17
 */
public class Appointment {

    private String reasonOfAppointment;

    private String modeOfAppointment;

    private Date dateOfAppointment;

    private String chargesTypeOfAppointment;

    private String feeOfAppointment;

    private String timeOfAppointment;

    private String appointmentId;

    private static int count = 1;

    private int progressBarStatus;

    private String responseStatus;

    private String replyComments;

    private String dayOfAppointment;

    private String totalFeeOfAppointment;

    private String status;

    private Boolean autoApproved;

    private String finStatus;

    public Appointment() {
        appointmentId = String.valueOf(count++);

    }

    public String getReasonOfAppointment() {
        return reasonOfAppointment;
    }

    public void setReasonOfAppointment(String reasonOfAppointment) {
        this.reasonOfAppointment = reasonOfAppointment;
    }

    public String getModeOfAppointment() {
        return modeOfAppointment;
    }

    public void setModeOfAppointment(String modeOfAppointment) {
        this.modeOfAppointment = modeOfAppointment;
    }

    public String getChargesTypeOfAppointment() {
        return chargesTypeOfAppointment;
    }

    public void setChargesTypeOfAppointment(String chargesTypeOfAppointment) {
        this.chargesTypeOfAppointment = chargesTypeOfAppointment;
    }

    public String getFeeOfAppointment() {
        return feeOfAppointment;
    }

    public void setFeeOfAppointment(String feeOfAppointment) {
        this.feeOfAppointment = feeOfAppointment;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getTimeOfAppointment() {
        return timeOfAppointment;
    }

    public void setTimeOfAppointment(String timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProgressBarStatus() {
        return progressBarStatus;
    }

    public void setProgressBarStatus(int progressBarStatus) {
        this.progressBarStatus = progressBarStatus;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(String replyComments) {
        this.replyComments = replyComments;
    }

    public String getDayOfAppointment() {
        return dayOfAppointment;
    }

    public void setDayOfAppointment(String dayOfAppointment) {
        this.dayOfAppointment = dayOfAppointment;
    }

    public String getTotalFeeOfAppointment() {
        return totalFeeOfAppointment;
    }

    public void setTotalFeeOfAppointment(String totalFeeOfAppointment) {
        this.totalFeeOfAppointment = totalFeeOfAppointment;
    }

    public Boolean getAutoApproved() {
        return autoApproved;
    }

    public void setAutoApproved(Boolean autoApproved) {
        this.autoApproved = autoApproved;
    }

    public String getFinStatus() {
        return finStatus;
    }

    public void setFinStatus(String finStatus) {
        this.finStatus = finStatus;
    }

}
