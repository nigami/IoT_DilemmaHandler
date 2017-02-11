/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization.Finance;

import business.Organization.Organization;
import business.Role.Role;
import business.Role.SactionManagerFinRole;
import business.finance.FinancialOrgDetails;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class SanctionManagerFinOrganization extends Organization {

    private FinancialOrgDetails financialOrgDetails;

    public FinancialOrgDetails getFinancialOrgDetails() {
        return financialOrgDetails;
    }

    public void setFinancialOrgDetails(FinancialOrgDetails financialOrgDetails) {
        this.financialOrgDetails = financialOrgDetails;
    }

    public SanctionManagerFinOrganization() {
        super(Organization.FinancialOrgType.Sanction.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new SactionManagerFinRole());
        return roleList;
    }
}
