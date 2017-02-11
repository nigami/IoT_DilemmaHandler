/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.patient;

import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class VitalSignHistory {

    private ArrayList<VitalSignInfo> vitalHistory;

    public VitalSignHistory() {
        vitalHistory = new ArrayList<>();
    }

    public VitalSignInfo addVitalInfo() {
        VitalSignInfo vitalSign = new VitalSignInfo();
        vitalHistory.add(vitalSign);
        return vitalSign;
    }

    public ArrayList<VitalSignInfo> getVitalHistory() {
        return vitalHistory;
    }

    public void setVitalHistory(ArrayList<VitalSignInfo> vitalHistory) {
        this.vitalHistory = vitalHistory;
    }
}
