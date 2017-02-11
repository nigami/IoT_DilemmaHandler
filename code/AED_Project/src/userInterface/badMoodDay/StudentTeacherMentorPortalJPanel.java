/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.badMoodDay;

import business.Enterprise.Enterprise;
import business.Organization.Organization;
import business.Organization.Community.TeacherOrganization;
import business.UserAccount.UserAccount;
import business.WorkQueue.AppointmentWorkRequest;
import business.WorkQueue.FinancialWorkRequest;
import business.person.ChatConversation;
import business.WorkQueue.StudentPortalWorkRequest;
import business.WorkQueue.WorkRequest;
import business.ecosystem.Ecosystem;
import java.awt.CardLayout;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import userInterface.appointments.AppointmentSchdComReqJFrame;
import userInterface.appointments.AppointmentSchdTeacherResJFrame;

/**
 *
 * @author ilanigam17
 */
public class StudentTeacherMentorPortalJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount acc;
    private Enterprise enterprise;
    private Ecosystem ecosystem;
    private WorkRequest request;
    private ChatConversation selConversation;

    /**
     * Creates new form NewJPanel
     */
    public StudentTeacherMentorPortalJPanel(JPanel userProcessContainer, UserAccount acc, Enterprise enterprise, Ecosystem ecosystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.acc = acc;
        this.enterprise = enterprise;
        this.ecosystem = ecosystem;

        populatePortalTable();
        populateStatusCombo();
        postQueryJPanel.setVisible(false);
        //postSolPanel.setVisible(false);
        viewProposedSolPanel.setVisible(false);
        this.request = null;
        this.selConversation = null;
        //viewSolScrollPane.setVisible(false);
        // postQueryScrollPane.setVisible(false);
        // postSolScrollPane.setVisible(false);
    }

    public void populateStatusCombo() {
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) inputStatusJCombo.getModel();
        inputStatusJCombo.addItem("Need Help!");
        inputStatusJCombo.addItem("Closed!");
        inputStatusJCombo.setSelectedItem(0);
    }

    public void populatePortalTable() {
        DefaultTableModel dtm = (DefaultTableModel) studentPortalJTable.getModel();
        dtm.setRowCount(0);

        // for(Organization org : enterprise.getOrganizationDirectory().getOrganizationList()){
        //if((org instanceof StudentOrganization)){
        for (WorkRequest request : acc.getWorkQueue().getWorkRequestList()) {
            if (request instanceof StudentPortalWorkRequest) {
                Object row[] = new Object[7];
                row[0] = request;
                row[1] = ((StudentPortalWorkRequest) request).getReceiver();
                row[2] = ((StudentPortalWorkRequest) request).getRequestDate();
                row[3] = request.getMessage();
                row[4] = ((StudentPortalWorkRequest) request).getQuery();
                row[5] = (((StudentPortalWorkRequest) request).getSolDiscussionList().size() > 0) ? ("Solutions available: #" + ((StudentPortalWorkRequest) request).getSolDiscussionList().size()) : "False";
                row[6] = ((StudentPortalWorkRequest) request).getStatus();
                dtm.addRow(row);
            }
            if (request instanceof AppointmentWorkRequest) {
                Object row[] = new Object[7];
                row[0] = request;
                row[1] = (request).getReceiver();
                row[2] = (request).getRequestDate();
                row[3] = request.getMessage();
                row[4] = ((AppointmentWorkRequest) request).getAppointmentReq().getReasonOfAppointment();
                row[5] = request.getStatus();
                row[6] = ((AppointmentWorkRequest) request).getAppointmentReq().getResponseStatus();
                dtm.addRow(row);
            }
            if (request instanceof FinancialWorkRequest) {
                Object row[] = new Object[7];
                row[0] = request;
                row[1] = ((FinancialWorkRequest) request).getFinalApprover();
                row[2] = (request).getRequestDate();
                row[3] = ((FinancialWorkRequest) request).getApprovalStatus();
                row[4] = ((FinancialWorkRequest) request).getMessage();
                row[5] = "Amount Sanctioned: $" + ((FinancialWorkRequest) request).getApprovedAmt();
                row[6] = "Finance for " + ((FinancialWorkRequest) request).getAppointmentReq().getResponseStatus();
                dtm.addRow(row);
            }

        }
        // }
        //}

    }

    public void returnFromAppointment() {
        populatePortalTable();
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
        studentPortalJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        dspPostQueryPanelJBtn = new javax.swing.JButton();
        viewSolnJBtn = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        viewProposedSolPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        proposedSolJList = new javax.swing.JList();
        jScrollPane9 = new javax.swing.JScrollPane();
        evalPropSolJTxtArea = new javax.swing.JTextArea();
        evalSubjectJText = new javax.swing.JTextField();
        datePostJLbl = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        evalReplyJTxt = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        evalReplyJButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        evalAvailabiltyJPanel = new javax.swing.JPanel();
        repliersJCombo = new javax.swing.JComboBox();
        day2JCheckBox = new javax.swing.JCheckBox();
        day1JCheckBox = new javax.swing.JCheckBox();
        day3JCheckBox = new javax.swing.JCheckBox();
        day4JCheckBox = new javax.swing.JCheckBox();
        day7JCheckBox = new javax.swing.JCheckBox();
        day5JCheckBox = new javax.swing.JCheckBox();
        day6JCheckBox = new javax.swing.JCheckBox();
        appointmentJBtn = new javax.swing.JButton();
        teacherNameJLbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        checkAvailabiltyJBtn = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        evalQueryTxtArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        statusCombo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        postQueryJPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        inputQueryJTxt = new javax.swing.JTextArea();
        postQueryJBtn = new javax.swing.JButton();
        inputSubjectJtxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        inputStatusJCombo = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        courseCOmboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        otherTextArea = new javax.swing.JTextArea();
        backJButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        studentPortalJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Request ID", "Sender's Name", "Date Posted", "Subject", "Query", "Solution Available", "Status"
            }
        ));
        jScrollPane1.setViewportView(studentPortalJTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Interactive Teacher/Mentor Portal");

        dspPostQueryPanelJBtn.setText("Post Query");
        dspPostQueryPanelJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dspPostQueryPanelJBtnActionPerformed(evt);
            }
        });

        viewSolnJBtn.setText("Solutions");
        viewSolnJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewSolnJBtnActionPerformed(evt);
            }
        });

        proposedSolJList.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        proposedSolJList.setForeground(new java.awt.Color(0, 102, 102));
        proposedSolJList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                proposedSolJListMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(proposedSolJList);

        evalPropSolJTxtArea.setEditable(false);
        evalPropSolJTxtArea.setColumns(20);
        evalPropSolJTxtArea.setRows(5);
        jScrollPane9.setViewportView(evalPropSolJTxtArea);

        evalReplyJTxt.setColumns(20);
        evalReplyJTxt.setRows(5);
        jScrollPane10.setViewportView(evalReplyJTxt);

        jLabel12.setText("Subject");

        jLabel13.setText("Solution History");

        jLabel14.setText("Reply");

        evalReplyJButton.setText("Reply");
        evalReplyJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evalReplyJButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel15.setText("Select whose reply you wanna see:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Evaluate Reponse");

        evalAvailabiltyJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Availability", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 12))); // NOI18N

        repliersJCombo.setForeground(new java.awt.Color(0, 51, 51));
        repliersJCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repliersJComboActionPerformed(evt);
            }
        });

        day2JCheckBox.setText("Tuesday");
        day2JCheckBox.setEnabled(false);

        day1JCheckBox.setText("Monday");
        day1JCheckBox.setEnabled(false);
        day1JCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                day1JCheckBoxActionPerformed(evt);
            }
        });

        day3JCheckBox.setText("Wednesday");
        day3JCheckBox.setEnabled(false);

        day4JCheckBox.setText("Thursday");
        day4JCheckBox.setEnabled(false);

        day7JCheckBox.setText("Sunday");
        day7JCheckBox.setEnabled(false);

        day5JCheckBox.setText("Friday");
        day5JCheckBox.setEnabled(false);
        day5JCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                day5JCheckBoxActionPerformed(evt);
            }
        });

        day6JCheckBox.setText("Saturday");
        day6JCheckBox.setEnabled(false);

        appointmentJBtn.setText("Schedule Appointment");
        appointmentJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentJBtnActionPerformed(evt);
            }
        });

        teacherNameJLbl.setText("Teacher Name:");

        jLabel5.setText("Please select conversation:");

        javax.swing.GroupLayout evalAvailabiltyJPanelLayout = new javax.swing.GroupLayout(evalAvailabiltyJPanel);
        evalAvailabiltyJPanel.setLayout(evalAvailabiltyJPanelLayout);
        evalAvailabiltyJPanelLayout.setHorizontalGroup(
            evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appointmentJBtn))
                    .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                        .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(teacherNameJLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                                .addComponent(day1JCheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(day3JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                                .addComponent(day7JCheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(day2JCheckBox))
                            .addComponent(repliersJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(day5JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                        .addComponent(day4JCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(day6JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        evalAvailabiltyJPanelLayout.setVerticalGroup(
            evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(repliersJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(day7JCheckBox)
                    .addComponent(day4JCheckBox)
                    .addComponent(day6JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teacherNameJLbl)
                    .addComponent(day2JCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(day1JCheckBox)
                    .addComponent(day3JCheckBox)
                    .addComponent(day5JCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(appointmentJBtn))
                .addGap(24, 24, 24))
        );

        checkAvailabiltyJBtn.setText("Check Availability");
        checkAvailabiltyJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAvailabiltyJBtnActionPerformed(evt);
            }
        });

        backJButton.setText("Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        evalQueryTxtArea.setEditable(false);
        evalQueryTxtArea.setColumns(20);
        evalQueryTxtArea.setRows(5);
        jScrollPane3.setViewportView(evalQueryTxtArea);

        jLabel2.setText("Query");

        statusCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Completed", "In Progress" }));
        statusCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboActionPerformed(evt);
            }
        });

        jLabel6.setText("Status:");

        javax.swing.GroupLayout viewProposedSolPanelLayout = new javax.swing.GroupLayout(viewProposedSolPanel);
        viewProposedSolPanel.setLayout(viewProposedSolPanelLayout);
        viewProposedSolPanelLayout.setHorizontalGroup(
            viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                        .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel12))
                            .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewProposedSolPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)))))
                        .addGap(18, 18, 18)
                        .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                                .addComponent(checkAvailabiltyJBtn)
                                .addGap(18, 18, 18)
                                .addComponent(evalReplyJButton))
                            .addComponent(evalSubjectJText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)))
                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(backJButton))
                .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(datePostJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(evalAvailabiltyJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        viewProposedSolPanelLayout.setVerticalGroup(
            viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8))
                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                        .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(statusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(evalSubjectJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(datePostJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                                .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel2))
                                .addGap(8, 8, 8)
                                .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))
                            .addComponent(evalAvailabiltyJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(viewProposedSolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(evalReplyJButton)
                            .addComponent(checkAvailabiltyJBtn)))
                    .addGroup(viewProposedSolPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(backJButton)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        inputQueryJTxt.setColumns(20);
        inputQueryJTxt.setRows(5);
        jScrollPane6.setViewportView(inputQueryJTxt);

        postQueryJBtn.setText("Post Query");
        postQueryJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postQueryJBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Subject:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Add a query");

        inputStatusJCombo.setEnabled(false);

        jLabel17.setText("Status:");

        jLabel18.setText("Describe your query:");

        courseCOmboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Java", "Python", "C++", "Scala", "Others" }));
        courseCOmboBox.setToolTipText("Please select course related to query");
        courseCOmboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseCOmboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Course:");

        otherTextArea.setColumns(20);
        otherTextArea.setRows(5);
        otherTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                otherTextAreaKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(otherTextArea);

        backJButton1.setText("Back");
        backJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout postQueryJPanelLayout = new javax.swing.GroupLayout(postQueryJPanel);
        postQueryJPanel.setLayout(postQueryJPanelLayout);
        postQueryJPanelLayout.setHorizontalGroup(
            postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(postQueryJPanelLayout.createSequentialGroup()
                .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(postQueryJPanelLayout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputStatusJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, postQueryJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(backJButton1)
                            .addComponent(jLabel18))
                        .addGap(12, 12, 12)
                        .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(postQueryJPanelLayout.createSequentialGroup()
                                    .addComponent(courseCOmboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane2))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inputSubjectJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(postQueryJBtn))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(680, Short.MAX_VALUE))
        );
        postQueryJPanelLayout.setVerticalGroup(
            postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(postQueryJPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(inputStatusJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(37, 37, 37)
                .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(postQueryJPanelLayout.createSequentialGroup()
                        .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(courseCOmboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputSubjectJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(postQueryJPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(postQueryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backJButton1)
                            .addComponent(postQueryJBtn)))
                    .addComponent(jLabel18))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewProposedSolPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(postQueryJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewProposedSolPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(postQueryJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(viewProposedSolPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(postQueryJPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLayeredPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dspPostQueryPanelJBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(viewSolnJBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dspPostQueryPanelJBtn)
                    .addComponent(viewSolnJBtn))
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dspPostQueryPanelJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dspPostQueryPanelJBtnActionPerformed
        // TODO add your handling code here:
        this.request = null;
        postQueryJPanel.setVisible(true);
        //postSolPanel.setVisible(false);
        viewProposedSolPanel.setVisible(false);
        otherTextArea.setVisible(false);

    }//GEN-LAST:event_dspPostQueryPanelJBtnActionPerformed

    private void postQueryJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postQueryJBtnActionPerformed
        // TODO add your handling code here:
        if (!inputSubjectJtxt.getText().isEmpty()) {
            StudentPortalWorkRequest studentPortalWorkRequest = new StudentPortalWorkRequest();
            studentPortalWorkRequest.setSender(acc);
            studentPortalWorkRequest.setRequestDate(new Date());
            studentPortalWorkRequest.setMessage(inputSubjectJtxt.getText());
            studentPortalWorkRequest.setQuery(inputQueryJTxt.getText());
            studentPortalWorkRequest.setStatus((String) inputStatusJCombo.getSelectedItem());
            if (courseCOmboBox.getSelectedItem().equals("Others")) {
                studentPortalWorkRequest.setCourseId((otherTextArea.getText()).toUpperCase());
            } else {
                studentPortalWorkRequest.setCourseId((courseCOmboBox.getSelectedItem().toString()).toUpperCase());
            }

            acc.getWorkQueue().getWorkRequestList().add(studentPortalWorkRequest);
            //JOptionPane.showMessageDialog(null, "Query posted!");

            for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                if ((org instanceof TeacherOrganization)) {
                    org.getWorkQueue().getWorkRequestList().add(studentPortalWorkRequest);
                    JOptionPane.showMessageDialog(null, "Query posted!");
                    populatePortalTable();
                    resetPostQueryField();
                }
            }//end of for
        }//end of if
    }//GEN-LAST:event_postQueryJBtnActionPerformed

    public void resetPostQueryField() {
        otherTextArea.setText("");
        inputSubjectJtxt.setText("");
        inputQueryJTxt.setText("");
        courseCOmboBox.setSelectedItem("Java");
    }

    private void viewSolnJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSolnJBtnActionPerformed
        this.request = null;
        this.selConversation = null;

        //postSolPanel.setVisible(false);
        if (studentPortalJTable.getSelectedRow() > -1) {

            DefaultTableModel dtm = (DefaultTableModel) studentPortalJTable.getModel();
            int selectedRow = studentPortalJTable.getSelectedRow();
            WorkRequest workReq = (WorkRequest) dtm.getValueAt(selectedRow, 0);

            if (workReq instanceof StudentPortalWorkRequest) {
                if (((StudentPortalWorkRequest) workReq).getSolDiscussionList().size() > 0) {
                    this.request = (StudentPortalWorkRequest) workReq;
                    populateEvaluationWindow();
                    viewProposedSolPanel.setVisible(true);
                    evalAvailabiltyJPanel.setVisible(false);
                    postQueryJPanel.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry! No Solution available");
                }

            } else if (workReq instanceof AppointmentWorkRequest) {
                this.request = (AppointmentWorkRequest) workReq;
                //tabbedPane.setVisible(false);
                AppointmentSchdTeacherResJFrame appointmentSchdTeacherResJFrame = new AppointmentSchdTeacherResJFrame(this.request, this.request.getSender(), acc, enterprise, ecosystem, this);
                appointmentSchdTeacherResJFrame.setVisible(true);
            } else if (workReq instanceof FinancialWorkRequest) {
                this.request = (FinancialWorkRequest) workReq;

                StudentSanctionFinanceJPanel studentSanctionFinanceJPanel = new StudentSanctionFinanceJPanel(userProcessContainer, acc, enterprise, ecosystem);
                CardLayout layout = (CardLayout) userProcessContainer.getLayout();
                userProcessContainer.add("studentSanctionFinanceJPanel", studentSanctionFinanceJPanel);
                layout.next(userProcessContainer);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select query to view solution!");
        }
    }//GEN-LAST:event_viewSolnJBtnActionPerformed

    public void populateEvaluationWindow() {

        DefaultListModel dlm = new DefaultListModel();
        Boolean newNotification = false;
        for (ChatConversation conversation : ((StudentPortalWorkRequest) this.request).getSolDiscussionList()) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String reportDate = df.format(conversation.getDateOfproposal());
            dlm.addElement(conversation.getCoversationId() + " " + reportDate);
            newNotification = true;
        }

        if (newNotification.equals(true)) {
            proposedSolJList.setModel(dlm);
        }

    }

    private void proposedSolJListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proposedSolJListMouseClicked
        this.selConversation = null;
        if (proposedSolJList.getSelectedValue() != null) {

            String[] part = String.valueOf(proposedSolJList.getSelectedValue()).split(" ");

            for (ChatConversation conversation : ((StudentPortalWorkRequest) this.request).getSolDiscussionList()) {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String reportDate = df.format(conversation.getDateOfproposal());

                if ((conversation.getCoversationId().equals(part[0])) && (reportDate.equals(part[1] + " " + part[2]))) {
                    this.selConversation = new ChatConversation();
                    this.selConversation = conversation;

                    evalSubjectJText.setText(this.request.getMessage());
                    evalQueryTxtArea.setText(((StudentPortalWorkRequest) this.request).getQuery());
                    if (conversation.getReply().isEmpty()) {
                        evalPropSolJTxtArea.setText(conversation.getReplierUA() + "\t" + conversation.getDateOfproposal() + "\n" + conversation.getProposedSoln());
                    } else {
                        String newSolution = conversation.getProposedSoln();
                        newSolution = newSolution + "\n\n" + conversation.getReplierUA().getUsername() + "\t" + conversation.getDateOfReply() + "\n" + conversation.getReply();
                        evalPropSolJTxtArea.setText(newSolution);
                    }
                    repliersJCombo.addItem(conversation);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "No panel match");
                }
            }
        }

    }//GEN-LAST:event_proposedSolJListMouseClicked

    private void evalReplyJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evalReplyJButtonActionPerformed
        // TODO add your handling code here:
        if (this.selConversation != null) {
            if (!evalReplyJTxt.getText().isEmpty()) {
                this.request.setStatus(String.valueOf(statusCombo.getSelectedItem()));
                this.selConversation.setDateOfReply(new Date());
                this.selConversation.setReply(evalReplyJTxt.getText());
                this.selConversation.setProposedSoln(evalPropSolJTxtArea.getText());
                this.selConversation.setReplierUA(acc);
                if (this.selConversation.getSenderUA() == null) {
                    this.selConversation.setSenderUA(acc);
                }
                populatePortalTable();
                JOptionPane.showMessageDialog(null, "Response recorded!");
                resetEvaluateFields();
            } else {
                JOptionPane.showMessageDialog(null, "No response provided!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No conversation selected!");
        }
    }//GEN-LAST:event_evalReplyJButtonActionPerformed

    private void courseCOmboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseCOmboBoxActionPerformed
        if (courseCOmboBox.getSelectedItem().equals("Others")) {
            otherTextArea.setVisible(true);
        }
    }//GEN-LAST:event_courseCOmboBoxActionPerformed

    private void checkAvailabiltyJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAvailabiltyJBtnActionPerformed
        // TODO add your handling code here:
        evalAvailabiltyJPanel.setVisible(true);
        //retrieveTeacherCombo();      
    }//GEN-LAST:event_checkAvailabiltyJBtnActionPerformed

    private void otherTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otherTextAreaKeyTyped
        // TODO add your handling code here:
        otherTextArea.setText(otherTextArea.getText().toUpperCase());
    }//GEN-LAST:event_otherTextAreaKeyTyped

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void appointmentJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentJBtnActionPerformed
        // TODO add your handling code here:
        //UserAccount teacherUA= (UserAccount) repliersJCombo.getSelectedItem();
        String appointmentDays = null;
        if (day1JCheckBox.isSelected()) {
            appointmentDays = day1JCheckBox.getText();
        }
        if (day2JCheckBox.isSelected()) {
            appointmentDays = " " + appointmentDays + " " + day2JCheckBox.getText();
        }
        if (day3JCheckBox.isSelected()) {
            appointmentDays = " " + appointmentDays + " " + day3JCheckBox.getText();
        }
        if (day4JCheckBox.isSelected()) {
            appointmentDays = " " + appointmentDays + " " + day4JCheckBox.getText();
        }
        if (day5JCheckBox.isSelected()) {
            appointmentDays = " " + appointmentDays + " " + day5JCheckBox.getText();
        }
        if (day6JCheckBox.isSelected()) {
            appointmentDays = " " + appointmentDays + " " + day6JCheckBox.getText();
        }
        if (day7JCheckBox.isSelected()) {
            appointmentDays = " " + appointmentDays + " " + day7JCheckBox.getText();
        }
        if (((ChatConversation) repliersJCombo.getSelectedItem()).getReplierUA() != acc) {
            AppointmentSchdComReqJFrame appointmentSchdComReqJFrame = new AppointmentSchdComReqJFrame(appointmentDays, acc, ((ChatConversation) repliersJCombo.getSelectedItem()).getReplierUA(), enterprise, ecosystem, this);
            appointmentSchdComReqJFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "There is no mentor available to take appointment!");
        }

    }//GEN-LAST:event_appointmentJBtnActionPerformed

    private void day5JCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day5JCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_day5JCheckBoxActionPerformed

    private void day1JCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day1JCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_day1JCheckBoxActionPerformed

//    public void retrieveTeacherCombo(){
//        repliersJCombo.removeAllItems();
//        for(Organization organization:enterprise.getOrganizationDirectory().getOrganizationList()){
//            if(organization instanceof TeacherOrganization){
//                for (UserAccount teacherUA: organization.getUserAccountDirectory().getUserAccountList())
//                    repliersJCombo.addItem(teacherUA);
//            }
//        }
//    }
    private void repliersJComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repliersJComboActionPerformed
        // TODO add your handling code here:
        if (repliersJCombo.getSelectedItem() != null) {
            //  for(Conversion conversation : ((StudentPortalWorkRequest)this.request).getSolDiscussionList()){
            if (((ChatConversation) repliersJCombo.getSelectedItem()).getReplierUA() != acc) {
                teacherNameJLbl.setText(((ChatConversation) repliersJCombo.getSelectedItem()).getReplierUA().getUsername());

                if (((ChatConversation) repliersJCombo.getSelectedItem()).getTeachersScheduleList().toString().contains("Monday")) {
                    day1JCheckBox.setBackground(Color.green);
                    day1JCheckBox.setEnabled(true);
                }
                if (((ChatConversation) repliersJCombo.getSelectedItem()).getTeachersScheduleList().toString().contains("Tuesday")) {
                    day2JCheckBox.setBackground(Color.green);
                    day2JCheckBox.setEnabled(true);
                }
                if (((ChatConversation) repliersJCombo.getSelectedItem()).getTeachersScheduleList().toString().contains("Wednesday")) {
                    day3JCheckBox.setBackground(Color.green);
                    day3JCheckBox.setEnabled(true);
                }
                if (((ChatConversation) repliersJCombo.getSelectedItem()).getTeachersScheduleList().toString().contains("Thursday")) {
                    day4JCheckBox.setBackground(Color.green);
                    day4JCheckBox.setEnabled(true);
                }
                if (((ChatConversation) repliersJCombo.getSelectedItem()).getTeachersScheduleList().toString().contains("Friday")) {
                    day5JCheckBox.setBackground(Color.green);
                    day5JCheckBox.setEnabled(true);
                }
                if (((ChatConversation) repliersJCombo.getSelectedItem()).getTeachersScheduleList().toString().contains("Saturday")) {
                    day6JCheckBox.setBackground(Color.green);
                    day6JCheckBox.setEnabled(true);
                }
                if (((ChatConversation) repliersJCombo.getSelectedItem()).getTeachersScheduleList().toString().contains("Sunday")) {
                    day7JCheckBox.setBackground(Color.green);
                    day7JCheckBox.setEnabled(true);
                }
            } else {
                teacherNameJLbl.setText("No Teacher available");
            }
        }
    }//GEN-LAST:event_repliersJComboActionPerformed

    private void backJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton1ActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButton1ActionPerformed

    private void statusComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusComboActionPerformed

    public void resetEvaluateFields() {

        evalPropSolJTxtArea.setText("");
        evalReplyJTxt.setText("");
        evalSubjectJText.setText("");
        evalQueryTxtArea.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appointmentJBtn;
    private javax.swing.JButton backJButton;
    private javax.swing.JButton backJButton1;
    private javax.swing.JButton checkAvailabiltyJBtn;
    private javax.swing.JComboBox courseCOmboBox;
    private javax.swing.JLabel datePostJLbl;
    private javax.swing.JCheckBox day1JCheckBox;
    private javax.swing.JCheckBox day2JCheckBox;
    private javax.swing.JCheckBox day3JCheckBox;
    private javax.swing.JCheckBox day4JCheckBox;
    private javax.swing.JCheckBox day5JCheckBox;
    private javax.swing.JCheckBox day6JCheckBox;
    private javax.swing.JCheckBox day7JCheckBox;
    private javax.swing.JButton dspPostQueryPanelJBtn;
    private javax.swing.JPanel evalAvailabiltyJPanel;
    private javax.swing.JTextArea evalPropSolJTxtArea;
    private javax.swing.JTextArea evalQueryTxtArea;
    private javax.swing.JButton evalReplyJButton;
    private javax.swing.JTextArea evalReplyJTxt;
    private javax.swing.JTextField evalSubjectJText;
    private javax.swing.JTextArea inputQueryJTxt;
    private javax.swing.JComboBox inputStatusJCombo;
    private javax.swing.JTextField inputSubjectJtxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea otherTextArea;
    private javax.swing.JButton postQueryJBtn;
    private javax.swing.JPanel postQueryJPanel;
    private javax.swing.JList proposedSolJList;
    private javax.swing.JComboBox repliersJCombo;
    private javax.swing.JComboBox statusCombo;
    private javax.swing.JTable studentPortalJTable;
    private javax.swing.JLabel teacherNameJLbl;
    private javax.swing.JPanel viewProposedSolPanel;
    private javax.swing.JButton viewSolnJBtn;
    // End of variables declaration//GEN-END:variables
}
