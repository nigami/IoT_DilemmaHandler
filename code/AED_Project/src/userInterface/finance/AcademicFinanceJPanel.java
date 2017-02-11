/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.finance;

import business.Enterprise.Enterprise;
import business.Enterprise.FinancialEnterprise;
import business.Organization.Finance.AcademicFinOrganization;
import business.Organization.Finance.SanctionManagerFinOrganization;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import business.WorkQueue.AppointmentWorkRequest;
import business.WorkQueue.FinancialWorkRequest;
import business.WorkQueue.WorkRequest;
import business.ecosystem.Ecosystem;
import business.utils.Validate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jcp.xml.dsig.internal.dom.Utils;

/**
 *
 * @author ilanigam17
 */
public class AcademicFinanceJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private Ecosystem ecoSystem;
    private Enterprise enterprise;
    private WorkRequest workRequest;

    /**
     * Creates new form AcademicFinanceJPanel
     */
    public AcademicFinanceJPanel(JPanel userProcessContainer, UserAccount userAccount, Organization organization, Enterprise enterprise, Ecosystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.organization = organization;
        this.ecoSystem = ecoSystem;
        this.enterprise = enterprise;
        populateAcademicFinancialTable();

    }

    public void populateAcademicFinInternalTable() {
        DefaultTableModel dtmFinance = (DefaultTableModel) intFinSanctionJTable.getModel();
        dtmFinance.setRowCount(0);
        for (WorkRequest requestFinancial : userAccount.getWorkQueue().getWorkRequestList()) {
            if (requestFinancial instanceof FinancialWorkRequest) {
                Object row[] = new Object[6];
                row[0] = requestFinancial;
                row[1] = ((FinancialWorkRequest) requestFinancial).getRequestDate();
                row[2] = calculateETA(((FinancialWorkRequest) requestFinancial).getRequestDate());
                row[3] = ((FinancialWorkRequest) requestFinancial).getApprovedAmt();
                row[4] = (((FinancialWorkRequest) requestFinancial).getApprovalStatus());
                row[5] = (((FinancialWorkRequest) requestFinancial).getAutoApproved());
                dtmFinance.addRow(row);
            }
        }

    }

    public void populateAcademicFinOrgLevelTable() {
        DefaultTableModel dtmAcademic = (DefaultTableModel) academicFinJTable.getModel();
        dtmAcademic.setRowCount(0);

        for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if ((org instanceof AcademicFinOrganization)) {
                for (WorkRequest academicRequest : org.getWorkQueue().getWorkRequestList()) {
                    if (academicRequest instanceof AppointmentWorkRequest) {
                        Object row[] = new Object[7];
                        row[0] = academicRequest;
                        //row[1]= (academicRequest).getSender().getUsername();
                        row[1] = (academicRequest).getRequestDate();

                        row[2] = calculateETA((academicRequest).getRequestDate());

                        row[3] = ((AppointmentWorkRequest) academicRequest).getAppointmentReq().getFeeOfAppointment();
                        row[4] = ((AppointmentWorkRequest) academicRequest).getSender().getPerson().getGpa();
                        row[5] = ((AppointmentWorkRequest) academicRequest).getAppointmentReq().getResponseStatus();
                        boolean autoApproveEligibity = checkAutoApproveEligibility(((AppointmentWorkRequest) academicRequest).getSender(), ((AppointmentWorkRequest) academicRequest).getSender().getPerson().getGpa());
                        ((AppointmentWorkRequest) academicRequest).getAppointmentReq().setAutoApproved(autoApproveEligibity);
                        row[6] = autoApproveEligibity;

                        if (autoApproveEligibity) {
                            approveAutoEligibleReq(academicRequest);
                        }
                        dtmAcademic.addRow(row);
                    }
                }
            }
        }
    }

    public void populateAcademicFinancialTable() {
        populateAcademicFinInternalTable();
        populateAcademicFinOrgLevelTable();

    }

    public void approveAutoEligibleReq(WorkRequest appointmentWorkRequest) {
        if (((AppointmentWorkRequest) appointmentWorkRequest).getAppointmentReq().getAutoApproved() == null) {
            FinancialWorkRequest finWorkReq = new FinancialWorkRequest();
            finWorkReq.setAppointmentReq(((AppointmentWorkRequest) appointmentWorkRequest).getAppointmentReq());
            finWorkReq.setSender(appointmentWorkRequest.getSender());
            finWorkReq.setReceiver(appointmentWorkRequest.getReceiver());
            finWorkReq.setStatus(appointmentWorkRequest.getStatus());
            finWorkReq.setRequestDate(appointmentWorkRequest.getRequestDate());
            finWorkReq.setResolveDate(appointmentWorkRequest.getResolveDate());
            finWorkReq.setApprovalReqDate(new Date());
            finWorkReq.setApprovalStatus("Approved");
            finWorkReq.setApprovedAmt(Float.valueOf(((AppointmentWorkRequest) appointmentWorkRequest).getAppointmentReq().getTotalFeeOfAppointment()));
            finWorkReq.setApprovedDate(null);
            finWorkReq.setOfficerAccount(userAccount);
            finWorkReq.setCaseStudyDetails("Auto Approve Eligible");
            finWorkReq.setEta(String.valueOf(new Date()));
            finWorkReq.setCaseTypeNeed("Strong");
            finWorkReq.setAutoApproved(true);
            finWorkReq.setMessage(appointmentWorkRequest.getMessage());

            for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                if (org instanceof SanctionManagerFinOrganization) {
                    org.getWorkQueue().getWorkRequestList().add(finWorkReq);
                }
            }
            userAccount.getWorkQueue().getWorkRequestList().add(finWorkReq);
            JOptionPane.showMessageDialog(null, "Request sent to further approval to supervisior!");

            ((AppointmentWorkRequest) appointmentWorkRequest).getAppointmentReq().setAutoApproved(true);
            populateAcademicFinInternalTable();
            // return true;
        }
    }

    public Boolean checkAutoApproveEligibility(UserAccount ua, float gpa) {
        Boolean autoApprove = false;
        for (UserAccount poorListUserAcc : ((FinancialEnterprise) enterprise).getFinancialChallengedDirectory().getUserAccountList()) {
            if ((poorListUserAcc.getUsername().equals(ua.getUsername())) && (poorListUserAcc.getPassword().equals(ua.getPassword())) && (gpa < 2)) {

                autoApprove = true;

            }
        }

        return autoApprove;
    }

    public String calculateETA(Date requestDate) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dt = sdf.format(requestDate);  // Start date
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, 1);  // number of days to add
            dt = sdf.format(c.getTime());  // dt is now the new date
            return dt;
        } catch (Exception e) {
           // System.err.println(e);
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        studentNameJTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        studPhNumJTxt = new javax.swing.JTextField();
        studAddTxtArea1 = new javax.swing.JTextField();
        studZipcodeTxt1 = new javax.swing.JTextField();
        studSchoolJTxt = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jTextField2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        fundCaseStudyJTxt = new javax.swing.JTextArea();
        fundFamilyFundJTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fundFiamilyExpJTxt = new javax.swing.JTextField();
        fundNeedJtxt = new javax.swing.JTextField();
        fundsApprovedReJtxt = new javax.swing.JTextField();
        approveJBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        fundIssueDetailsJTxt = new javax.swing.JTextArea();
        jSlider1 = new javax.swing.JSlider();
        jCheckBox1 = new javax.swing.JCheckBox();
        caseTypeJCombo = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        academicFinJTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        intFinSanctionJTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        teacherName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        teacherphNumJTxt = new javax.swing.JTextField();
        teacheraddTxtArea = new javax.swing.JTextField();
        teacherzipcodeTxt = new javax.swing.JTextField();
        teachAppchargeJTxt = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        teacherAppDetails = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 51))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(0, 51, 51));

        jLabel16.setText("Name");

        jLabel18.setText("Phone Number:");

        jLabel19.setText("Address:");

        jLabel21.setText("Zip code:");

        jLabel22.setText("School:");

        jLabel23.setText("Issue details:");

        studPhNumJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studPhNumJTxtActionPerformed(evt);
            }
        });

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane7.setViewportView(jTextArea6);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel24.setText("GPA:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22))
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentNameJTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(studPhNumJTxt)
                    .addComponent(studAddTxtArea1)
                    .addComponent(studSchoolJTxt)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(studZipcodeTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentNameJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(studPhNumJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(studAddTxtArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(studZipcodeTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studSchoolJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 51));
        jLabel17.setText("Academic Finance Portal");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Case details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 0, 153))); // NOI18N

        fundCaseStudyJTxt.setColumns(20);
        fundCaseStudyJTxt.setRows(5);
        jScrollPane4.setViewportView(fundCaseStudyJTxt);

        fundFamilyFundJTxt.setEditable(false);
        fundFamilyFundJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fundFamilyFundJTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("Total Family funds per month:");

        jLabel2.setText("Case Study for funds:");

        jLabel3.setText("Funds required:");

        jLabel4.setText("Family Expenses per month:");

        jLabel5.setText("Amount approved:");

        fundFiamilyExpJTxt.setEditable(false);

        fundNeedJtxt.setEditable(false);
        fundNeedJtxt.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                fundNeedJtxtInputMethodTextChanged(evt);
            }
        });
        fundNeedJtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fundNeedJtxtActionPerformed(evt);
            }
        });

        approveJBtn.setBackground(new java.awt.Color(204, 255, 204));
        approveJBtn.setText("Approve!");
        approveJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveJBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Description:");

        fundIssueDetailsJTxt.setEditable(false);
        fundIssueDetailsJTxt.setColumns(20);
        fundIssueDetailsJTxt.setRows(5);
        jScrollPane8.setViewportView(fundIssueDetailsJTxt);

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setToolTipText("");
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jCheckBox1.setText("Auto Approve");

        caseTypeJCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "STRONG", "MANAGABLE", "WEAK" }));
        caseTypeJCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseTypeJComboActionPerformed(evt);
            }
        });

        jLabel25.setText("Select Case Type:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fundFamilyFundJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel25)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(approveJBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fundNeedJtxt)
                            .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(fundFiamilyExpJTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fundsApprovedReJtxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(caseTypeJCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
                .addGap(100, 100, 100))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fundFamilyFundJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fundFiamilyExpJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fundNeedJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(approveJBtn)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fundsApprovedReJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(caseTypeJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        backJButton.setText("Back");

        jTabbedPane2.setForeground(new java.awt.Color(0, 102, 102));

        jPanel1.setBackground(new java.awt.Color(248, 241, 247));

        academicFinJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Student Id", "Request date", "ETA", "Amount", "GPA", "Fin Status", "Auto approve"
            }
        ));
        academicFinJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                academicFinJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(academicFinJTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("New Appointment Request", jPanel1);

        jPanel5.setBackground(new java.awt.Color(233, 245, 247));

        intFinSanctionJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Request Id", "Request date", "ETA", "Amount", "Status", "Assigned To", "Auto approve"
            }
        ));
        intFinSanctionJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                intFinSanctionJTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(intFinSanctionJTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Internal Request Status", jPanel5);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Teacher/Mentor details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 51))); // NOI18N

        teacherName.setEditable(false);
        teacherName.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Name:");

        jLabel8.setText("Appointment details");

        jLabel9.setText("Phone Number:");

        jLabel10.setText("Address:");

        jLabel12.setText("Zip code:");

        jLabel13.setText("Charges:");

        jLabel14.setText("Appointment Confirmation:");

        teacherphNumJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherphNumJTxtActionPerformed(evt);
            }
        });

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setEnabled(false);

        teacherAppDetails.setColumns(20);
        teacherAppDetails.setRows(5);
        jScrollPane5.setViewportView(teacherAppDetails);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(teacheraddTxtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(teacherzipcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(teachAppchargeJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(teacherphNumJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(teacherName, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel8)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(teacherphNumJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(teacheraddTxtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teachAppchargeJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(teacherzipcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(646, 646, 646))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(488, 488, 488)
                        .addComponent(jLabel17))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backJButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(17, 17, 17)
                .addComponent(backJButton)
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void teacherphNumJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherphNumJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherphNumJTxtActionPerformed

    private void studPhNumJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studPhNumJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studPhNumJTxtActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void fundFamilyFundJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fundFamilyFundJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fundFamilyFundJTxtActionPerformed

    private void approveJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveJBtnActionPerformed
        // TODO add your handling code here:
        if (!fundsApprovedReJtxt.getText().isEmpty() && Validate.ValidateFloat(fundsApprovedReJtxt.getText(), "Approved funds should numberic")) {
            FinancialWorkRequest finWorkReq = new FinancialWorkRequest();
            finWorkReq.setAppointmentReq(((AppointmentWorkRequest) this.workRequest).getAppointmentReq());
            finWorkReq.setSender(this.workRequest.getSender());
            finWorkReq.setReceiver(this.workRequest.getReceiver());
            finWorkReq.setStatus(this.workRequest.getStatus());
            finWorkReq.setRequestDate(this.workRequest.getRequestDate());
            finWorkReq.setResolveDate(this.workRequest.getResolveDate());
            finWorkReq.setApprovalReqDate(new Date());
            finWorkReq.setApprovalStatus("Assigned");
            finWorkReq.setApprovedAmt(Float.valueOf(fundsApprovedReJtxt.getText()));
            finWorkReq.setApprovedDate(null);
            finWorkReq.setOfficerAccount(userAccount);
            finWorkReq.setCaseStudyDetails(fundCaseStudyJTxt.getText());
            finWorkReq.setEta(calculateETA(new Date()));

            if (caseTypeJCombo.getSelectedItem() != null && (!(caseTypeJCombo.getSelectedItem().toString()).trim().isEmpty())) {
                finWorkReq.setCaseTypeNeed((String) caseTypeJCombo.getSelectedItem());
            }
            for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                if (org instanceof SanctionManagerFinOrganization) {
                    org.getWorkQueue().getWorkRequestList().add(finWorkReq);
                }
            }
            userAccount.getWorkQueue().getWorkRequestList().add(finWorkReq);
            JOptionPane.showMessageDialog(null, "Request sent to further approval to supervisior!");
            populateAcademicFinancialTable();
        } else {
            JOptionPane.showMessageDialog(null, "Please enter correct Approved amt/Case study");
        }
    }//GEN-LAST:event_approveJBtnActionPerformed

    private void academicFinJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_academicFinJTableMouseClicked
        // TODO add your handling code here:

        DefaultTableModel dtmAcademic = (DefaultTableModel) academicFinJTable.getModel();
        WorkRequest acaWorkReq = (WorkRequest) dtmAcademic.getValueAt(academicFinJTable.getSelectedRow(), 0);
        dtmAcademic.setValueAt("Assigned: " + userAccount, academicFinJTable.getSelectedRow(), 5);

        this.workRequest = acaWorkReq;

        populateAppointDetails();
        // approveJBtn.setEnabled(false);
        disableStudentFields();
        disableTeacherFields();
        disableFundsDetails();
    }//GEN-LAST:event_academicFinJTableMouseClicked

    private void intFinSanctionJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_intFinSanctionJTableMouseClicked
        // TODO add your handling code here:

        DefaultTableModel dtmFinance = (DefaultTableModel) intFinSanctionJTable.getModel();
        WorkRequest finWorkReq = (WorkRequest) dtmFinance.getValueAt(intFinSanctionJTable.getSelectedRow(), 0);
        this.workRequest = finWorkReq;
        populateAppointDetails();
        approveJBtn.setEnabled(false);
        disableStudentFields();
        disableTeacherFields();
        disableFundsDetails();
    }//GEN-LAST:event_intFinSanctionJTableMouseClicked

    private void disableStudentFields() {
        studentNameJTxt.setEditable(false);
        studPhNumJTxt.setEditable(false);
        studAddTxtArea1.setEditable(false);
        studZipcodeTxt1.setEditable(false);
        jTextField2.setEditable(false);
        studSchoolJTxt.setEditable(false);
        jTextArea6.setEditable(false);
    }

    private void disableTeacherFields() {
        teacherName.setEditable(false);
        teacheraddTxtArea.setEditable(false);
        teacherphNumJTxt.setEditable(false);
        teacherzipcodeTxt.setEditable(false);
        teacherAppDetails.setEditable(false);
        teachAppchargeJTxt.setEditable(false);
    }

    private void disableFundsDetails() {
        if (this.workRequest instanceof FinancialWorkRequest) {
            fundNeedJtxt.setEditable(false);
            fundIssueDetailsJTxt.setEditable(false);
            jSlider1.setEnabled(false);
            fundsApprovedReJtxt.setEditable(false);
            fundCaseStudyJTxt.setEditable(false);
            caseTypeJCombo.setEnabled(false);
            approveJBtn.setEnabled(false);
        } else if ((this.workRequest instanceof AppointmentWorkRequest) && ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getAutoApproved() == true) {
            fundsApprovedReJtxt.setEditable(false);
            fundCaseStudyJTxt.setEditable(false);
            caseTypeJCombo.setEnabled(false);
            fundNeedJtxt.setEditable(true);
            fundIssueDetailsJTxt.setEditable(true);
            jSlider1.setEnabled(false);
            approveJBtn.setEnabled(false);
        } else {
            fundNeedJtxt.setEditable(false);
            fundIssueDetailsJTxt.setEditable(false);
            fundsApprovedReJtxt.setEditable(true);
            fundCaseStudyJTxt.setEditable(true);
            caseTypeJCombo.setEnabled(true);
            fundsApprovedReJtxt.setEnabled(true);
            fundCaseStudyJTxt.setEnabled(true);
            caseTypeJCombo.setEnabled(true);
            jSlider1.setVisible(true);
            approveJBtn.setEnabled(true);
        }
        fundFamilyFundJTxt.setEditable(false);
        fundFiamilyExpJTxt.setEditable(false);
    }

    private void caseTypeJComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseTypeJComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caseTypeJComboActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        fundsApprovedReJtxt.setEditable(false);
        fundsApprovedReJtxt.setText(String.valueOf(jSlider1.getValue()));

    }//GEN-LAST:event_jSlider1StateChanged

    private void fundNeedJtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fundNeedJtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fundNeedJtxtActionPerformed

    private void fundNeedJtxtInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_fundNeedJtxtInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_fundNeedJtxtInputMethodTextChanged

    public void populateAppointDetails() {
        populateStudentDetails();
        populateTeacherDetails();
        populateFundingDetails();
    }

    public void populateStudentDetails() {
        studentNameJTxt.setText(this.workRequest.getSender().getPerson().getName());
        studPhNumJTxt.setText((String.valueOf(this.workRequest.getSender().getPerson().getPhoneNum())));
        studAddTxtArea1.setText(this.workRequest.getSender().getPerson().getStAddress());
        studZipcodeTxt1.setText(String.valueOf(this.workRequest.getSender().getPerson().getZipCode()));
        jTextField2.setText(String.valueOf(this.workRequest.getSender().getPerson().getGpa()));
        studSchoolJTxt.setText(this.workRequest.getSender().getPerson().getSchoolName());
        if (this.workRequest instanceof FinancialWorkRequest) {
            //   studAppcourseJTxt1.setText(((FinancialWorkRequest)this.workRequest).getMessage());
            jTextArea6.setText(((FinancialWorkRequest) this.workRequest).getAppointmentReq().getReasonOfAppointment());
        } else {
            // studAppcourseJTxt1.setText(((AppointmentWorkRequest)this.workRequest).getMessage());
            jTextArea6.setText(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getReasonOfAppointment());
        }
    }

    public void populateTeacherDetails() {
        teacherName.setText(this.workRequest.getReceiver().getPerson().getName());
        teacheraddTxtArea.setText(this.workRequest.getReceiver().getPerson().getStAddress());
        teacherphNumJTxt.setText(String.valueOf(this.workRequest.getReceiver().getPerson().getPhoneNum()));
        teacherzipcodeTxt.setText((String.valueOf(this.workRequest.getReceiver().getPerson().getZipCode())));
        //teachercourseJTxt.setText(this.workRequest.getReceiver().getPerson().get);
        if (this.workRequest instanceof FinancialWorkRequest) {
            teacherAppDetails.setText("Date: " + ((FinancialWorkRequest) this.workRequest).getAppointmentReq().getDayOfAppointment() + "\n" + "Time: " + ((FinancialWorkRequest) this.workRequest).getAppointmentReq().getTimeOfAppointment());
            teachAppchargeJTxt.setText((((FinancialWorkRequest) this.workRequest).getAppointmentReq().getFeeOfAppointment()));
        } else {
            teacherAppDetails.setText("Date: " + ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getDayOfAppointment() + "\n" + "Time: " + ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getTimeOfAppointment());
            teachAppchargeJTxt.setText((((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getFeeOfAppointment()));
        }
    }

    public void populateFundingDetails() {
        if (this.workRequest instanceof FinancialWorkRequest) {
            fundNeedJtxt.setText((((FinancialWorkRequest) this.workRequest).getAppointmentReq().getTotalFeeOfAppointment()));
            fundIssueDetailsJTxt.setText(((FinancialWorkRequest) this.workRequest).getAppointmentReq().getReasonOfAppointment());
            jSlider1.setMaximum(Integer.parseInt((((FinancialWorkRequest) this.workRequest).getAppointmentReq().getTotalFeeOfAppointment())));
            fundsApprovedReJtxt.setText(String.valueOf(((FinancialWorkRequest) this.workRequest).getApprovedAmt()));
            fundCaseStudyJTxt.setText(((FinancialWorkRequest) this.workRequest).getCaseStudyDetails());
            caseTypeJCombo.setSelectedItem(((FinancialWorkRequest) this.workRequest).getCaseTypeNeed());
        } else {
            fundNeedJtxt.setText((((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getTotalFeeOfAppointment()));
            fundIssueDetailsJTxt.setText(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getReasonOfAppointment());
            jSlider1.setMaximum(Integer.parseInt((((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getTotalFeeOfAppointment())));
            if (((AppointmentWorkRequest) this.workRequest).getAppointmentReq() != null) {
                if (((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getAutoApproved() == true) {
                    fundsApprovedReJtxt.setText(String.valueOf(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getFeeOfAppointment()));
                    fundCaseStudyJTxt.setText("Auto Approve Eligible");
                    caseTypeJCombo.setSelectedItem("STRONG");
                }
            } else {
            }
        }
        float monthlyIncome = this.workRequest.getSender().getPerson().getFundsAvail().getFamilyMonIncome();
        float monthlyExpense = this.workRequest.getSender().getPerson().getFundsAvail().getFamilyMonExpense();
        float appNeedAmt = Float.valueOf(fundNeedJtxt.getText());
        fundFamilyFundJTxt.setText(String.valueOf(monthlyIncome));
        fundFiamilyExpJTxt.setText(String.valueOf(monthlyExpense));

        if ((monthlyIncome < monthlyExpense) || (appNeedAmt > (monthlyIncome - monthlyExpense))) {
            caseTypeJCombo.setSelectedItem("STRONG");
        } else if ((appNeedAmt == (monthlyIncome - monthlyExpense)) || (monthlyIncome == monthlyExpense)) {
            caseTypeJCombo.setSelectedItem("MANAGABLE");
        } else if ((appNeedAmt < (monthlyIncome - monthlyExpense)) || (monthlyIncome > monthlyExpense)) {
            caseTypeJCombo.setSelectedItem("WEAK");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable academicFinJTable;
    private javax.swing.JButton approveJBtn;
    private javax.swing.JButton backJButton;
    private javax.swing.JComboBox caseTypeJCombo;
    private javax.swing.JTextArea fundCaseStudyJTxt;
    private javax.swing.JTextField fundFamilyFundJTxt;
    private javax.swing.JTextField fundFiamilyExpJTxt;
    private javax.swing.JTextArea fundIssueDetailsJTxt;
    private javax.swing.JTextField fundNeedJtxt;
    private javax.swing.JTextField fundsApprovedReJtxt;
    private javax.swing.JTable intFinSanctionJTable;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField studAddTxtArea1;
    private javax.swing.JTextField studPhNumJTxt;
    private javax.swing.JTextField studSchoolJTxt;
    private javax.swing.JTextField studZipcodeTxt1;
    private javax.swing.JTextField studentNameJTxt;
    private javax.swing.JTextField teachAppchargeJTxt;
    private javax.swing.JTextArea teacherAppDetails;
    private javax.swing.JTextField teacherName;
    private javax.swing.JTextField teacheraddTxtArea;
    private javax.swing.JTextField teacherphNumJTxt;
    private javax.swing.JTextField teacherzipcodeTxt;
    // End of variables declaration//GEN-END:variables
}
