/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Enterprise;

import business.Role.Role;
import business.UserAccount.UserAccountDirectory;
import business.person.PersonDirectory;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class FinancialEnterprise extends Enterprise {

    private UserAccountDirectory financialChallengedDirectory;

    private float minimumWage;

    public FinancialEnterprise(String name) {
        super(name, Enterprise.EnterpriseType.FINANCE);
        financialChallengedDirectory = new UserAccountDirectory();
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        return this.roles;
    }

    public UserAccountDirectory getFinancialChallengedDirectory() {
        return financialChallengedDirectory;
    }

    public void setFinancialChallengedDirectory(UserAccountDirectory financialChallengedDirectory) {
        this.financialChallengedDirectory = financialChallengedDirectory;
    }

    public float getMinimumWage() {
        return minimumWage;
    }

    public void setMinimumWage(float minimumWage) {
        this.minimumWage = minimumWage;
    }

}
