/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.finance;

/**
 *
 * @author ilanigam17
 */
public class FinancialOrgDetails {

    private double totalBudget;

    private double remainingBudget;

    private double totalApprovedAmt;

    private int noOfCasesSolved;

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public double getTotalApprovedAmt() {
        return totalApprovedAmt;
    }

    public void setTotalApprovedAmt(double totalApprovedAmt) {
        this.totalApprovedAmt = totalApprovedAmt;
    }

    public int getNoOfCasesSolved() {
        return noOfCasesSolved;
    }

    public void setNoOfCasesSolved(int noOfCasesSolved) {
        this.noOfCasesSolved = noOfCasesSolved;
    }

}
