/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.patient;

/**
 *
 * @author ilanigam17
 */
public class Patient {

    private String name;
    private String id;
    private int age;
    private String docName;
    private String pharmacyName;
    private String bloodGrp;
    private VitalSignHistory vitalHistory;

    public Patient() {

        vitalHistory = new VitalSignHistory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public VitalSignHistory getVitalHistory() {
        return vitalHistory;
    }

    public void setVitalHistory(VitalSignHistory vitalHistory) {
        this.vitalHistory = vitalHistory;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String deriveStatus(VitalSignInfo vi) {
        String status = "Abnormal";
        float respRate = vi.getRespRate();
        float heartRate = vi.getHeartRate();
        float sbp = vi.getSystolicBP();
        float weight = vi.getWeight();
        if (age > 0 && age <= 3) {
            if ((respRate >= 20 && respRate <= 30) && (heartRate >= 80 && heartRate <= 130) && (sbp >= 80 && sbp <= 110) && (weight >= 22 && weight <= 31)) {
                status = "Normal";
            }
        }
        if (age >= 4 && age <= 5) {
            if ((respRate >= 20 && respRate <= 30) && (heartRate >= 80 && heartRate <= 120) && (sbp >= 80 && sbp <= 110) && (weight >= 31 && weight <= 40)) {
                status = "Normal";
            }
        }
        if (age >= 6 && age <= 12) {
            if ((respRate >= 20 && respRate <= 30) && (heartRate >= 70 && heartRate <= 110) && (sbp >= 80 && sbp <= 120) && (weight >= 41 && weight <= 92)) {
                status = "Normal";
            }
        }
        if (age > 12) {
            if ((respRate >= 12 && respRate <= 20) && (heartRate >= 55 && heartRate <= 105) && (sbp >= 110 && sbp <= 120) && (weight > 110)) {
                status = "Normal";
            }
        }

        return status;
    }

}
