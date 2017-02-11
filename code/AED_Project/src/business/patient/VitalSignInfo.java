/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.patient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VitalSignInfo {

    private float respRate;
    private float heartRate;
    private float systolicBP;
    private float weight;
    private Boolean hivStatus;
    private String descIssue;
    private String currentTime;
    private String sensorId;

    public float getRespRate() {
        return respRate;
    }

    public void setRespRate(float respRate) {
        this.respRate = respRate;
    }

    public float getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(float heartRate) {
        this.heartRate = heartRate;
    }

    public float getSystolicBP() {
        return systolicBP;
    }

    public void setSystolicBP(float systolicBP) {
        this.systolicBP = systolicBP;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public Boolean getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(Boolean hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getDescIssue() {
        return descIssue;
    }

    public void setDescIssue(String descIssue) {
        this.descIssue = descIssue;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Date getTimeStamp() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = format.parse(getCurrentTime());
        return date;
    }

    @Override
    public String toString() {
        return currentTime; //To change body of generated methods, choose Tools | Templates.
    }

}
