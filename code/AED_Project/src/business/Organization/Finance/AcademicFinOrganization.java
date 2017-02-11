/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization.Finance;

import business.Enterprise.Enterprise;
import business.Organization.Organization;
import business.Role.AcademicFinRole;
import business.Role.Role;
import business.Role.TeacherRole;
import business.finance.FinancialOrgDetails;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class AcademicFinOrganization extends Organization {

    private FinancialOrgDetails financialOrgDetails;

    public FinancialOrgDetails getFinancialOrgDetails() {
        return financialOrgDetails;
    }

    public void setFinancialOrgDetails(FinancialOrgDetails financialOrgDetails) {
        this.financialOrgDetails = financialOrgDetails;
    }

    public AcademicFinOrganization() {
        super(Organization.FinancialOrgType.Academic.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new AcademicFinRole());
        return roleList;
    }

}
