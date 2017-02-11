/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.WorkQueue;

import business.UserAccount.UserAccount;
import business.appointments.Appointment;
import java.util.Date;

/**
 *
 * @author ilanigam17
 */
public class FinancialWorkRequest extends WorkRequest {

    private String finRequestId;
    private static int count = 1;
    private Appointment appointmentReq;
    private String approvalStatus;
    private Date approvalReqDate;
    private Date approvedDate;
    private UserAccount officerAccount;
    private float approvedAmt;
    private String caseStudyDetails;
    private Boolean autoApproved;
    private String eta;
    private String caseTypeNeed;
    private UserAccount finalApprover;

    public static enum CaseType {

        STRONG("Strong Case"), WEAK("Weak Case"), MODERATE("Managable Case"),;

        private String value;

        private CaseType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public FinancialWorkRequest() {
        finRequestId = String.valueOf(count++);
    }

    public Appointment getAppointmentReq() {
        return appointmentReq;

    }

    public void setAppointmentReq(Appointment appointmentRequest) {
        this.appointmentReq = appointmentRequest;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getApprovalReqDate() {
        return approvalReqDate;
    }

    public void setApprovalReqDate(Date approvalReqDate) {
        this.approvalReqDate = approvalReqDate;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public UserAccount getOfficerAccount() {
        return officerAccount;
    }

    public void setOfficerAccount(UserAccount officerAccount) {
        this.officerAccount = officerAccount;
    }

    public float getApprovedAmt() {
        return approvedAmt;
    }

    public void setApprovedAmt(float approvedAmt) {
        this.approvedAmt = approvedAmt;
    }

    public String getCaseStudyDetails() {
        return caseStudyDetails;
    }

    public void setCaseStudyDetails(String caseStudyDetails) {
        this.caseStudyDetails = caseStudyDetails;
    }

    public String getFinRequestId() {
        return finRequestId;
    }

    public void setFinRequestId(String finRequestId) {
        this.finRequestId = finRequestId;
    }

    public Boolean getAutoApproved() {
        return autoApproved;
    }

    public void setAutoApproved(Boolean autoApproved) {
        this.autoApproved = autoApproved;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getCaseTypeNeed() {
        return caseTypeNeed;
    }

    public void setCaseTypeNeed(String caseTypeNeed) {
        this.caseTypeNeed = caseTypeNeed;
    }

    public UserAccount getFinalApprover() {
        return finalApprover;
    }

    public void setFinalApprover(UserAccount finalApprover) {
        this.finalApprover = finalApprover;
    }

}
