/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.WorkQueue;

import business.patient.VitalSignInfo;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class PersonalHealthIssueWorkRequest extends WorkRequest {

    private ArrayList<File> uploadList;
    private String testResult;
    private String issueDetails;
    private VitalSignInfo latestVital;

    public PersonalHealthIssueWorkRequest() {
        this.uploadList = new ArrayList<>();
    }

    public ArrayList<File> getUploadList() {
        return uploadList;
    }

    public void setUploadList(ArrayList<File> uploadList) {
        this.uploadList = uploadList;
    }

    public String getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(String issueDetails) {
        this.issueDetails = issueDetails;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public VitalSignInfo getLatestVital() {
        return latestVital;
    }

    public void setLatestVital(VitalSignInfo latestVital) {
        this.latestVital = latestVital;
    }

}
