/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.finance;

import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.Community.StudentOrganization;
import business.Organization.Finance.SanctionManagerFinOrganization;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import business.WorkQueue.AppointmentWorkRequest;
import business.WorkQueue.FinancialWorkRequest;
import business.WorkQueue.WorkRequest;
import business.ecosystem.Ecosystem;
import business.utils.Validate;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ilanigam17
 */
public class SanctionManagerFinanceJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization org;
    private Enterprise enterprise;
    private Ecosystem ecosystem;
    private WorkRequest workReq;

    /**
     * Creates new form SanctionManagerFinanceJPanel1
     */
    public SanctionManagerFinanceJPanel(JPanel userProcessContainer, UserAccount userAccount, Organization org, Enterprise enterprise, Ecosystem ecosystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.org = org;
        this.enterprise = enterprise;
        this.ecosystem = ecosystem;
        populateNotification();
        populateSanctionOrgReqTable();
        approveJBtn.setVisible(false);
        denyJBtn.setVisible(false);
    }

    public void populateSanctionOrgReqTable() {
        DefaultTableModel dtm = (DefaultTableModel) sanctionOrgReqJTable.getModel();
        dtm.setRowCount(0);
        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
            if (request instanceof FinancialWorkRequest) {
                Object row[] = new Object[7];
                row[0] = request;
                row[1] = ((FinancialWorkRequest) request).getSender();
                row[2] = ((FinancialWorkRequest) request).getApprovalReqDate();
                row[3] = ((FinancialWorkRequest) request).getOfficerAccount();
                row[4] = ((FinancialWorkRequest) request).getApprovedDate();
                row[5] = ((FinancialWorkRequest) request).getApprovalStatus();
                row[6] = ((FinancialWorkRequest) request).getAutoApproved();
                dtm.addRow(row);

                if (((FinancialWorkRequest) request).getAutoApproved() != null) {
                    approveAutoEligible((FinancialWorkRequest) request);

                }
            }
        }

    }

    public void approveAutoEligible(FinancialWorkRequest finReq) {
        if (((FinancialWorkRequest) finReq).getFinalApprover() == null) {
            finReq.setCheckedNotification(false);
            finReq.setApprovedDate(new Date());
            finReq.setApprovalStatus("Finance Approved!");
            finReq.setFinalApprover(userAccount);
            finReq.setCaseStudyDetails("Auto Eligible" + "\n" + "Sanctioned amount will be transferred to Professional's account directly.");

            //Step2: Go inside each network to check each enterprise
            for (Network network : ecosystem.getNetworkList()) {
                //Step 2-a: Check against each enterprise
                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                    //Step3: Check against each organization inside that enterprise
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof StudentOrganization) {
                            for (UserAccount acc : organization.getUserAccountDirectory().getUserAccountList()) {
                                if (acc == (finReq).getSender()) {
                                    acc.getWorkQueue().getWorkRequestList().add(finReq);
                                }
                            }
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Request sent to concerned student!");
            populateSanctionOrgReqTable();
        }
    }

    public void populateNotification() {
        DefaultListModel DLM = new DefaultListModel();
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (!(organization instanceof SanctionManagerFinOrganization)) {
                for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
                    //  if(((AppointmentWorkRequest)request).getAppointmentReq().getFinStatus().equalsIgnoreCase("Assigned")){
                    try {
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                        String reportDate = df.format(request.getRequestDate());
                        DLM.addElement(request.getId() + "(" + reportDate + ")" + request.getMessage());
                    } catch (Exception e) {
                       // System.err.println("youthEntryWorkAreaJPanel..........." + e);
                    }
                    //}
                }
            }
        }
        pendingReqJList.setModel(DLM);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        sanctionOrgReqJTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        fundingJPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        caseFundsDescJTxt = new javax.swing.JTextArea();
        caseFamilyIncomeJTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        caseFamilyExpJTxt = new javax.swing.JTextField();
        caseFundReqJTxt = new javax.swing.JTextField();
        caseAmtNeedJTxt = new javax.swing.JTextField();
        approveJBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        caseDescJtxt = new javax.swing.JTextArea();
        jSlider1 = new javax.swing.JSlider();
        denyJBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        pendingReqJList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jProgressBar2 = new javax.swing.JProgressBar();
        studNameJTxt = new javax.swing.JTextField();
        studReqDateJTxt = new javax.swing.JTextField();
        studAmtNeedJTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        studSchoolJtxt = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));

        sanctionOrgReqJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Case ID", "SenderId", "Request date", "Pre-Approver", "Response date", "Status", "Auto Approved"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sanctionOrgReqJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanctionOrgReqJTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sanctionOrgReqJTable);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Sanction Manager View");

        fundingJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Case details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 102, 102))); // NOI18N

        caseFundsDescJTxt.setColumns(20);
        caseFundsDescJTxt.setRows(5);
        jScrollPane3.setViewportView(caseFundsDescJTxt);

        caseFamilyIncomeJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseFamilyIncomeJTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("Total Family funds per month:");

        jLabel2.setText("Case Study for funds:");

        jLabel3.setText("Funds required:");

        jLabel4.setText("Family Expenses per month:");

        jLabel5.setText("Amount approved:");

        caseFamilyExpJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseFamilyExpJTxtActionPerformed(evt);
            }
        });

        caseFundReqJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseFundReqJTxtActionPerformed(evt);
            }
        });

        approveJBtn.setText("Approve!");
        approveJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveJBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Description:");

        caseDescJtxt.setColumns(20);
        caseDescJtxt.setRows(5);
        jScrollPane4.setViewportView(caseDescJtxt);

        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        denyJBtn.setText("Deny!");
        denyJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                denyJBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fundingJPanelLayout = new javax.swing.GroupLayout(fundingJPanel);
        fundingJPanel.setLayout(fundingJPanelLayout);
        fundingJPanelLayout.setHorizontalGroup(
            fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fundingJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(fundingJPanelLayout.createSequentialGroup()
                        .addComponent(denyJBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(approveJBtn))
                    .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fundingJPanelLayout.createSequentialGroup()
                            .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel1))
                            .addGap(28, 28, 28)
                            .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(caseFamilyIncomeJTxt)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)))
                        .addGroup(fundingJPanelLayout.createSequentialGroup()
                            .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2))
                            .addGap(36, 36, 36)
                            .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(caseFundReqJTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(fundingJPanelLayout.createSequentialGroup()
                                    .addComponent(caseAmtNeedJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                .addComponent(caseFamilyExpJTxt)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fundingJPanelLayout.setVerticalGroup(
            fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fundingJPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(fundingJPanelLayout.createSequentialGroup()
                        .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(caseFamilyIncomeJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(caseFamilyExpJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(caseFundReqJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fundingJPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(5, 5, 5))
                            .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(caseAmtNeedJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fundingJPanelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2))
                    .addGroup(fundingJPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(fundingJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approveJBtn)
                    .addComponent(denyJBtn))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pendingReqJList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pending Requests", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        pendingReqJList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingReqJListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(pendingReqJList);

        jPanel3.setBackground(new java.awt.Color(249, 246, 246));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 102, 102))); // NOI18N

        studNameJTxt.setEditable(false);

        studReqDateJTxt.setEditable(false);

        studAmtNeedJTxt.setEditable(false);
        studAmtNeedJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studAmtNeedJTxtActionPerformed(evt);
            }
        });

        jLabel8.setText("Name:");

        jLabel9.setText("Request date:");

        jLabel10.setText("Appointment name:");

        jLabel19.setText("School:");

        studSchoolJtxt.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studAmtNeedJTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(studNameJTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(studReqDateJTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(studSchoolJtxt))
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studNameJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studReqDateJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studAmtNeedJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(studSchoolJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                            .addComponent(fundingJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fundingJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void approveJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveJBtnActionPerformed
        if (((Validate.ValidateEmpty(caseAmtNeedJTxt.getText(), "Please specify approved amount"))) && ((Validate.ValidateFloat(caseAmtNeedJTxt.getText(), "Please specify approved amount")))
                && Validate.ValidateEmpty(caseFundsDescJTxt.getText(), "Please specify case study")) {
            ((FinancialWorkRequest) this.workReq).setCheckedNotification(false);
            ((FinancialWorkRequest) this.workReq).setApprovedDate(new Date());
            ((FinancialWorkRequest) this.workReq).setApprovalStatus("Finance Approved!");
            ((FinancialWorkRequest) this.workReq).setApprovedAmt(Float.valueOf(caseAmtNeedJTxt.getText()));
            ((FinancialWorkRequest) this.workReq).setFinalApprover(userAccount);
            String issueDetails = ((FinancialWorkRequest) this.workReq).getCaseStudyDetails() + "\n\n" + userAccount + ":\n" + caseFundsDescJTxt.getText();
            ((FinancialWorkRequest) this.workReq).setCaseStudyDetails(issueDetails + "\n" + "Sanctioned amount will be transferred to Professional's account directly.");
            this.workReq.setStatus("Academic finance Sanctioned");

            //Step2: Go inside each network to check each enterprise
            for (Network network : ecosystem.getNetworkList()) {
                //Step 2-a: Check against each enterprise
                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                    //Step3: Check against each organization inside that enterprise
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof StudentOrganization) {
                            for (UserAccount acc : organization.getUserAccountDirectory().getUserAccountList()) {
                                if (acc == ((FinancialWorkRequest) this.workReq).getSender()) {
                                    acc.getWorkQueue().getWorkRequestList().add(this.workReq);
                                }
                            }
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Request sent to concerned student!");
            populateSanctionOrgReqTable();
            populateNotification();

        }
    }//GEN-LAST:event_approveJBtnActionPerformed

    private void caseFamilyIncomeJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseFamilyIncomeJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caseFamilyIncomeJTxtActionPerformed

    private void studAmtNeedJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studAmtNeedJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studAmtNeedJTxtActionPerformed

    private void sanctionOrgReqJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanctionOrgReqJTableMouseClicked
        // TODO add your handling code here:

        if (sanctionOrgReqJTable.getSelectedRow() > -1) {
            DefaultTableModel dtm = (DefaultTableModel) sanctionOrgReqJTable.getModel();
            FinancialWorkRequest finWorkReq = (FinancialWorkRequest) dtm.getValueAt(sanctionOrgReqJTable.getSelectedRow(), 0);
            this.workReq = finWorkReq;

            caseDescJtxt.setText(finWorkReq.getAppointmentReq().getReasonOfAppointment() + "\n" + finWorkReq.getOfficerAccount().getPerson().getName() + ":\n" + finWorkReq.getCaseStudyDetails());
            caseFamilyIncomeJTxt.setText(String.valueOf(finWorkReq.getSender().getPerson().getFundsAvail().getFamilyMonIncome()));
            caseFamilyExpJTxt.setText(String.valueOf(finWorkReq.getSender().getPerson().getFundsAvail().getFamilyMonExpense()));
            caseFundReqJTxt.setText(String.valueOf(finWorkReq.getAppointmentReq().getTotalFeeOfAppointment()));

            jSlider1.setMaximum(Integer.valueOf(finWorkReq.getAppointmentReq().getTotalFeeOfAppointment()));
            caseAmtNeedJTxt.setText(String.valueOf(finWorkReq.getApprovedAmt()));
            disableFundFields();
            changeColor(finWorkReq.getCaseTypeNeed());
            if (finWorkReq.getAppointmentReq().getAutoApproved() != true) {
                approveJBtn.setVisible(true);
                denyJBtn.setVisible(true);
            }
        }
    }//GEN-LAST:event_sanctionOrgReqJTableMouseClicked

    public void disableFundFields() {
        //caseFundsDescJTxt.setEditable(false);
        caseFamilyIncomeJTxt.setEditable(false);
        caseFamilyExpJTxt.setEditable(false);

    }

    public void changeColor(String caseStrength) {
        if ((!caseStrength.isEmpty()) && caseStrength.equalsIgnoreCase("Strong")) {
            fundingJPanel.setBackground(Color.green);
        }
        if ((!caseStrength.isEmpty()) && caseStrength.equalsIgnoreCase("Managable")) {
            fundingJPanel.setBackground(Color.blue);
        }
        if ((!caseStrength.isEmpty()) && caseStrength.equalsIgnoreCase("weak")) {
            fundingJPanel.setBackground(Color.red);
        }

    }
    private void caseFundReqJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseFundReqJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caseFundReqJTxtActionPerformed

    private void pendingReqJListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingReqJListMouseClicked

        if (pendingReqJList.getSelectedValue() != null) {
            // System.out.println("value................"+pendingReqJList.getSelectedValue());
            String[] parts = String.valueOf(pendingReqJList.getSelectedValue()).split("\\(");
            String[] dateRetrive = parts[1].split("\\)");
            int idMatch = Integer.valueOf(parts[0]);
            for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String reportDate = df.format(request.getRequestDate());

                    if ((Integer.valueOf(request.getId()) == idMatch) && (reportDate.equals(dateRetrive[0]))) {
                        if (request instanceof AppointmentWorkRequest) {
                            studNameJTxt.setText(request.getSender().getPerson().getName());
                            studReqDateJTxt.setText(String.valueOf(request.getRequestDate()));
                            studAmtNeedJTxt.setText(request.getReceiver().getPerson().getName());
                            studSchoolJtxt.setText(request.getSender().getPerson().getSchoolName());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_pendingReqJListMouseClicked

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
        caseAmtNeedJTxt.setText(String.valueOf(jSlider1.getValue()));
    }//GEN-LAST:event_jSlider1StateChanged

    private void denyJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_denyJBtnActionPerformed
        ((FinancialWorkRequest) this.workReq).setCheckedNotification(false);
        ((FinancialWorkRequest) this.workReq).setApprovedDate(new Date());
        ((FinancialWorkRequest) this.workReq).setApprovalStatus("Finance Cancelled!");
        ((FinancialWorkRequest) this.workReq).setApprovedAmt(0);
        ((FinancialWorkRequest) this.workReq).setFinalApprover(userAccount);
        String issueDetails = ((FinancialWorkRequest) this.workReq).getCaseStudyDetails() + "\n\n" + userAccount + ":\n" + caseFundsDescJTxt.getText();
        ((FinancialWorkRequest) this.workReq).setCaseStudyDetails(issueDetails + "\n" + "Request for finance is denied");
        this.workReq.setStatus("Academic finance Denied");
       JOptionPane.showMessageDialog(null, "Request denied");
    }//GEN-LAST:event_denyJBtnActionPerformed

    private void caseFamilyExpJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseFamilyExpJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caseFamilyExpJTxtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveJBtn;
    private javax.swing.JTextField caseAmtNeedJTxt;
    private javax.swing.JTextArea caseDescJtxt;
    private javax.swing.JTextField caseFamilyExpJTxt;
    private javax.swing.JTextField caseFamilyIncomeJTxt;
    private javax.swing.JTextField caseFundReqJTxt;
    private javax.swing.JTextArea caseFundsDescJTxt;
    private javax.swing.JButton denyJBtn;
    private javax.swing.JPanel fundingJPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable jTable1;
    private javax.swing.JList pendingReqJList;
    private javax.swing.JTable sanctionOrgReqJTable;
    private javax.swing.JTextField studAmtNeedJTxt;
    private javax.swing.JTextField studNameJTxt;
    private javax.swing.JTextField studReqDateJTxt;
    private javax.swing.JTextField studSchoolJtxt;
    // End of variables declaration//GEN-END:variables
}
