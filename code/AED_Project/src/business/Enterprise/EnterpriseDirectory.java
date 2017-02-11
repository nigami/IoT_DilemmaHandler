/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Enterprise;

import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class EnterpriseDirectory {

    private ArrayList<Enterprise> enterpriseList;

    public EnterpriseDirectory() {
        this.enterpriseList = new ArrayList();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return this.enterpriseList;
    }

    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type) {
        Enterprise enterprise = null;
        if (type == Enterprise.EnterpriseType.COMMUNITY) {
            enterprise = new CommunityEnterprise(name);
            this.enterpriseList.add(enterprise);
        } else if (type == Enterprise.EnterpriseType.FINANCE) {
            enterprise = new FinancialEnterprise(name);
            this.enterpriseList.add(enterprise);
        }
        return enterprise;
    }
}
