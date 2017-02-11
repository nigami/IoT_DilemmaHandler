/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.appointments;

import business.Enterprise.Enterprise;
import business.Enterprise.FinancialEnterprise;
import business.Network.Network;
import business.Organization.Community.TeacherOrganization;
import business.Organization.Finance.AcademicFinOrganization;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import business.WorkQueue.AppointmentWorkRequest;
import business.WorkQueue.FinancialWorkRequest;
import business.WorkQueue.WorkRequest;
import business.appointments.AppointmentHistory;
import business.ecosystem.Ecosystem;
import business.person.ProfessionalsProfile;
import java.awt.Color;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import userInterface.badMoodDay.StudentSanctionFinanceJPanel;
import userInterface.badMoodDay.StudentTeacherMentorPortalJPanel;
import userInterface.healthCare.ViewHealthReqResultJPanel;
import userInterface.teacher.TeacherHelpOfferingPortalJPanel;

/**
 *
 * @author ilanigam17
 */
public class AppointmentSchdTeacherResJFrame extends javax.swing.JFrame {

    private UserAccount professionalUserAcc;
    private JPanel sendingJPanelObj;
    private AppointmentHistory appHistory;
    private Ecosystem ecosystem;
    private UserAccount senderUserAcc;
    private Enterprise ent;
    private ProfessionalsProfile tp;
    private WorkRequest workRequest;

    /**
     * Creates new form AppointmentSchdComReqJFrame
     */
    public AppointmentSchdTeacherResJFrame() {
        initComponents();
    }

    public AppointmentSchdTeacherResJFrame(WorkRequest workRequest, UserAccount senderUserAcc, UserAccount professionalUserAcc, Enterprise ent, Ecosystem ecosystem, JPanel sendingJPanelObj) {
        initComponents();
        // replyCommentJtxt.setVisible(false);
        this.senderUserAcc = senderUserAcc;
        this.professionalUserAcc = professionalUserAcc;
        this.ecosystem = ecosystem;
        this.workRequest = workRequest;
        this.ent = ent;
        if (sendingJPanelObj instanceof StudentTeacherMentorPortalJPanel) {
            this.sendingJPanelObj = (StudentTeacherMentorPortalJPanel) sendingJPanelObj;
        } else if (sendingJPanelObj instanceof ViewHealthReqResultJPanel) {
            this.sendingJPanelObj = (ViewHealthReqResultJPanel) sendingJPanelObj;
        } else if (sendingJPanelObj instanceof TeacherHelpOfferingPortalJPanel) {
            this.sendingJPanelObj = (TeacherHelpOfferingPortalJPanel) sendingJPanelObj;
        } else if (sendingJPanelObj instanceof StudentSanctionFinanceJPanel) {
            this.sendingJPanelObj = (StudentSanctionFinanceJPanel) sendingJPanelObj;
        }//else if(sendingJPanelObj instanceof C){
        // this.sendingJPanelObj= (StudentTeacherMentorPortalJPanel)sendingJPanelObj;
        // }
        //determineFinancialOrgProfile();
        this.appHistory = professionalUserAcc.getPerson().getAppointHistoryList();
        populateNeccessaryDetails();

    }

    public void determineFinancialOrgProfile() {
        for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
            if (org instanceof TeacherOrganization) {
                for (ProfessionalsProfile teacherProfile : ((TeacherOrganization) org).getTeacherDirectory().getTeacherList()) {
                    if (professionalUserAcc == teacherProfile.getUserAccount()) {
                        this.tp = teacherProfile;
                        break;
                    }//
                }//
            }//end if org
            if (this.tp != null) {
                break;
            }
        }//end for org
    }

    public void populateNeccessaryDetails() {
        if (this.workRequest instanceof FinancialWorkRequest) {
            confirmAppJBtn.setVisible(false);
            cancelAppJBtn.setVisible(false);
            profNameJTxt.setText(senderUserAcc.getPerson().getName());
            profSchoolJTxt.setText(senderUserAcc.getPerson().getSchoolName());
            currentGPAJTxt.setText(String.valueOf(senderUserAcc.getPerson().getGpa()));
            profReasonJTxtArea.setText(((FinancialWorkRequest) this.workRequest).getAppointmentReq().getReasonOfAppointment());
            ProfModeOfPayJCombo.setSelectedItem(((FinancialWorkRequest) this.workRequest).getAppointmentReq().getModeOfAppointment());
            String scheduleList = ((FinancialWorkRequest) this.workRequest).getAppointmentReq().getDayOfAppointment();
            populateAvailabilityCheckBox(scheduleList);
            String rate = ((FinancialWorkRequest) this.workRequest).getAppointmentReq().getFeeOfAppointment();
            String totalMins = ((FinancialWorkRequest) this.workRequest).getAppointmentReq().getTimeOfAppointment();

            profTotalFeeJTxt.setText(String.valueOf(Integer.valueOf(rate) * Integer.valueOf(totalMins)));
            ((FinancialWorkRequest) this.workRequest).getAppointmentReq().setTotalFeeOfAppointment(String.valueOf(Integer.valueOf(rate) * Integer.valueOf(totalMins)));
            dateofAppJtxt.setText(String.valueOf(((FinancialWorkRequest) this.workRequest).getAppointmentReq().getDateOfAppointment()));
            timeOfAppJTxt.setText(((FinancialWorkRequest) this.workRequest).getAppointmentReq().getTimeOfAppointment());

            ((FinancialWorkRequest) this.workRequest).getAppointmentReq().setProgressBarStatus(100);
            profSAppJProgressBar.setValue(100);

        } else if (sendingJPanelObj instanceof StudentTeacherMentorPortalJPanel) {
            confirmAppJBtn.setVisible(false);
            cancelAppJBtn.setVisible(false);
            profNameJTxt.setText(senderUserAcc.getPerson().getName());
            profSchoolJTxt.setText(senderUserAcc.getPerson().getSchoolName());
            currentGPAJTxt.setText(String.valueOf(senderUserAcc.getPerson().getGpa()));
            profReasonJTxtArea.setText(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getReasonOfAppointment());
            ProfModeOfPayJCombo.setSelectedItem(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getModeOfAppointment());
            String scheduleList = ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getDayOfAppointment();
            populateAvailabilityCheckBox(scheduleList);
            String rate = ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getFeeOfAppointment();
            String totalMins = ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getTimeOfAppointment();

            profTotalFeeJTxt.setText(String.valueOf(Float.valueOf(rate) * Float.valueOf(totalMins)));
            ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setTotalFeeOfAppointment(String.valueOf(Integer.valueOf(rate) * Integer.valueOf(totalMins)));
            dateofAppJtxt.setText(String.valueOf(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getDateOfAppointment()));
            timeOfAppJTxt.setText(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getTimeOfAppointment());

            //  ((AppointmentWorkRequest)this.workRequest).getAppointmentReq().setProgressBarStatus(String.valueOf(75));
            profSAppJProgressBar.setValue(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getProgressBarStatus());

        } else if (this.workRequest instanceof AppointmentWorkRequest) {
            profNameJTxt.setText(senderUserAcc.getPerson().getName());
            profSchoolJTxt.setText(senderUserAcc.getPerson().getSchoolName());
            currentGPAJTxt.setText(String.valueOf(senderUserAcc.getPerson().getGpa()));
            profReasonJTxtArea.setText(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getReasonOfAppointment());
            ProfModeOfPayJCombo.setSelectedItem(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getChargesTypeOfAppointment());
            String scheduleList = ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getDayOfAppointment();
            populateAvailabilityCheckBox(scheduleList);
            String rate = ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getFeeOfAppointment();
            String totalMins = ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getTimeOfAppointment();
            profTotalFeeJTxt.setText(String.valueOf(Float.valueOf(rate) * Float.valueOf(totalMins)));
            ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setTotalFeeOfAppointment(String.valueOf(Integer.valueOf(rate) * Integer.valueOf(totalMins)));
            dateofAppJtxt.setText(String.valueOf(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getDateOfAppointment()));
            timeOfAppJTxt.setText(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getTimeOfAppointment());

            ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setProgressBarStatus(100);
            profSAppJProgressBar.setValue(Integer.valueOf(((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getProgressBarStatus()));
        }

    }

    public void populateAvailabilityCheckBox(String scheduleList) {
        if (scheduleList != null) {
            //  for(Conversion conversation : ((StudentPortalWorkRequest)this.request).getSolDiscussionList()){

            if (scheduleList.contains("Monday")) {
                day1JCheckBox.setBackground(Color.green);
                day1JCheckBox.doClick();
            }
            if (scheduleList.contains("Tuesday")) {
                day2JCheckBox.setBackground(Color.green);
                day2JCheckBox.doClick();
            }
            if (scheduleList.contains("Wednesday")) {
                day3JCheckBox.setBackground(Color.green);
                day3JCheckBox.doClick();
            }
            if (scheduleList.contains("Thursday")) {
                day4JCheckBox.setBackground(Color.green);
                day4JCheckBox.doClick();
            }
            if (scheduleList.contains("Friday")) {
                day5JCheckBox.setBackground(Color.green);
                day5JCheckBox.doClick();
            }
            if (scheduleList.contains("Saturday")) {
                day6JCheckBox.setBackground(Color.green);
                day6JCheckBox.doClick();
            }
            if (scheduleList.contains("Sunday")) {
                day7JCheckBox.setBackground(Color.green);
                day7JCheckBox.doClick();
            }
            //  }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        profReasonJTxtArea = new javax.swing.JTextArea();
        profNameJTxt = new javax.swing.JTextField();
        profSchoolJTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        confirmAppJBtn = new javax.swing.JButton();
        profSAppJProgressBar = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        evalAvailabiltyJPanel = new javax.swing.JPanel();
        day2JCheckBox = new javax.swing.JCheckBox();
        day1JCheckBox = new javax.swing.JCheckBox();
        day3JCheckBox = new javax.swing.JCheckBox();
        day4JCheckBox = new javax.swing.JCheckBox();
        day7JCheckBox = new javax.swing.JCheckBox();
        day5JCheckBox = new javax.swing.JCheckBox();
        day6JCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ProfModeOfPayJCombo = new javax.swing.JComboBox();
        profTotalFeeJTxt = new javax.swing.JTextField();
        timeOfAppJTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dateofAppJtxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        currentGPAJTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cancelAppJBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(234, 243, 250));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Appointment Schedule");

        jLabel6.setText("Name Of Sender:");

        jLabel7.setText("School:");

        profReasonJTxtArea.setColumns(20);
        profReasonJTxtArea.setRows(5);
        jScrollPane2.setViewportView(profReasonJTxtArea);

        profNameJTxt.setEditable(false);
        profNameJTxt.setBackground(new java.awt.Color(255, 255, 255));
        profNameJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profNameJTxtActionPerformed(evt);
            }
        });

        jLabel12.setText("Reason for Appointment:");

        confirmAppJBtn.setBackground(new java.awt.Color(204, 255, 204));
        confirmAppJBtn.setText("Confirm Appointment");
        confirmAppJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmAppJBtnActionPerformed(evt);
            }
        });

        profSAppJProgressBar.setStringPainted(true);

        jLabel3.setText("Progress Bar:");

        evalAvailabiltyJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Availability", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 12))); // NOI18N

        day2JCheckBox.setText("Tuesday");

        day1JCheckBox.setText("Monday");
        day1JCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                day1JCheckBoxActionPerformed(evt);
            }
        });

        day3JCheckBox.setText("Wednesday");

        day4JCheckBox.setText("Thursday");

        day7JCheckBox.setText("Sunday");

        day5JCheckBox.setText("Friday");
        day5JCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                day5JCheckBoxActionPerformed(evt);
            }
        });

        day6JCheckBox.setText("Saturday");

        jLabel4.setText("Days:");

        jLabel8.setText("Charges:");

        ProfModeOfPayJCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Complimentary", "Paid" }));
        ProfModeOfPayJCombo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProfModeOfPayJCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfModeOfPayJComboActionPerformed(evt);
            }
        });

        timeOfAppJTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeOfAppJTxtActionPerformed(evt);
            }
        });

        jLabel10.setText("Appointment date:");

        dateofAppJtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateofAppJtxtActionPerformed(evt);
            }
        });

        jLabel13.setText("Total Fee:");

        javax.swing.GroupLayout evalAvailabiltyJPanelLayout = new javax.swing.GroupLayout(evalAvailabiltyJPanel);
        evalAvailabiltyJPanel.setLayout(evalAvailabiltyJPanelLayout);
        evalAvailabiltyJPanelLayout.setHorizontalGroup(
            evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                        .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(77, 77, 77)
                        .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                                        .addComponent(dateofAppJtxt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(timeOfAppJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(169, 169, 169))
                                    .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                                        .addComponent(day7JCheckBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(day1JCheckBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(day2JCheckBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(day3JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(day4JCheckBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(day5JCheckBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(day6JCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(27, 27, 27))
                            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                                .addComponent(ProfModeOfPayJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(profTotalFeeJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        evalAvailabiltyJPanelLayout.setVerticalGroup(
            evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(day7JCheckBox)
                    .addComponent(day1JCheckBox)
                    .addComponent(day2JCheckBox)
                    .addComponent(day3JCheckBox)
                    .addComponent(day4JCheckBox)
                    .addComponent(day5JCheckBox)
                    .addComponent(day6JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ProfModeOfPayJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profTotalFeeJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateofAppJtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeOfAppJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jLabel5.setText("Current GPA:");

        cancelAppJBtn.setBackground(new java.awt.Color(255, 153, 153));
        cancelAppJBtn.setText("Cancel Appointment");
        cancelAppJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAppJBtnActionPerformed(evt);
            }
        });

        jButton2.setText("<<Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(272, 272, 272)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cancelAppJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(confirmAppJBtn))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel6))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(308, 308, 308)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel3)
                                                            .addComponent(profSAppJProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(currentGPAJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(profSchoolJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(profNameJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(16, 16, 16))))
                                            .addComponent(jButton2))
                                        .addGap(36, 36, 36)))))
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addComponent(evalAvailabiltyJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profSAppJProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profNameJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(profSchoolJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentGPAJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(evalAvailabiltyJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmAppJBtn)
                    .addComponent(cancelAppJBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profNameJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profNameJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profNameJTxtActionPerformed

    private void confirmAppJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmAppJBtnActionPerformed

        ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setProgressBarStatus(100);
        profSAppJProgressBar.setValue(100);
        ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setStatus("Request Acknowledged");
        ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setResponseStatus("Appointment Confirmed");
        //professionalUserAcc.getPerson().getAppointHistoryList().getAppointmentList().add(app);
        this.workRequest.setResolveDate(new Date());
        for (Network nw : ecosystem.getNetworkList()) {
            for (Enterprise ent : nw.getEnterpriseDirectory().getEnterpriseList()) {
                if ((ent instanceof FinancialEnterprise) || (((AppointmentWorkRequest) this.workRequest).getAppointmentReq().getChargesTypeOfAppointment().equals("Paid"))) {
                    for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                        if (org instanceof AcademicFinOrganization) {
                            org.getWorkQueue().getWorkRequestList().add(this.workRequest);
                        }
                    }
                }
            }
        }
        if (sendingJPanelObj instanceof TeacherHelpOfferingPortalJPanel) {
            ((TeacherHelpOfferingPortalJPanel) sendingJPanelObj).returnFromAppointment(this.workRequest);
        }//else if(sendingJPanelObj instanceof ViewHealthReqResultJPanel){
//            ((ViewHealthReqResultJPanel)sendingJPanelObj)..returnFromAppointment();;
//       }
        JOptionPane.showMessageDialog(null, "Request Acknowledged!");
        this.dispose();
    }//GEN-LAST:event_confirmAppJBtnActionPerformed

    public String retrieveScheduleDay() {

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
        return appointmentDays;
    }

    private void cancelAppJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAppJBtnActionPerformed
        // TODO add your handling code here:

        profSAppJProgressBar.setValue(100);
        ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setResponseStatus("Appointment Cancelled");
        ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setStatus("Request Acknowledged");
        ((AppointmentWorkRequest) this.workRequest).getAppointmentReq().setProgressBarStatus(100);

        (this.workRequest).setResolveDate(new Date());
        if (sendingJPanelObj instanceof TeacherHelpOfferingPortalJPanel) {
            ((TeacherHelpOfferingPortalJPanel) sendingJPanelObj).returnFromAppointment(this.workRequest);
        }
        JOptionPane.showMessageDialog(null, "Request Acknowledged!");
        this.dispose();

    }//GEN-LAST:event_cancelAppJBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (sendingJPanelObj instanceof StudentTeacherMentorPortalJPanel) {
            ((StudentTeacherMentorPortalJPanel) sendingJPanelObj).populatePortalTable();
            this.dispose();
        } else if (sendingJPanelObj instanceof ViewHealthReqResultJPanel) {
            ((ViewHealthReqResultJPanel) sendingJPanelObj).populatePatientInfo();
            this.dispose();
        } else if (sendingJPanelObj instanceof TeacherHelpOfferingPortalJPanel) {
            ((TeacherHelpOfferingPortalJPanel) sendingJPanelObj).populateTeacherSpecificDetails();
            this.dispose();
        } else if (sendingJPanelObj instanceof StudentSanctionFinanceJPanel) {
            ((StudentSanctionFinanceJPanel) sendingJPanelObj).populateSanctionOrgReqTable();
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dateofAppJtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateofAppJtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateofAppJtxtActionPerformed

    private void timeOfAppJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeOfAppJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeOfAppJTxtActionPerformed

    private void ProfModeOfPayJComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfModeOfPayJComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfModeOfPayJComboActionPerformed

    private void day5JCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day5JCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_day5JCheckBoxActionPerformed

    private void day1JCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day1JCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_day1JCheckBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppointmentSchdTeacherResJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentSchdTeacherResJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentSchdTeacherResJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentSchdTeacherResJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentSchdTeacherResJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ProfModeOfPayJCombo;
    private javax.swing.JButton cancelAppJBtn;
    private javax.swing.JButton confirmAppJBtn;
    private javax.swing.JTextField currentGPAJTxt;
    private javax.swing.JTextField dateofAppJtxt;
    private javax.swing.JCheckBox day1JCheckBox;
    private javax.swing.JCheckBox day2JCheckBox;
    private javax.swing.JCheckBox day3JCheckBox;
    private javax.swing.JCheckBox day4JCheckBox;
    private javax.swing.JCheckBox day5JCheckBox;
    private javax.swing.JCheckBox day6JCheckBox;
    private javax.swing.JCheckBox day7JCheckBox;
    private javax.swing.JPanel evalAvailabiltyJPanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField profNameJTxt;
    private javax.swing.JTextArea profReasonJTxtArea;
    private javax.swing.JProgressBar profSAppJProgressBar;
    private javax.swing.JTextField profSchoolJTxt;
    private javax.swing.JTextField profTotalFeeJTxt;
    private javax.swing.JTextField timeOfAppJTxt;
    // End of variables declaration//GEN-END:variables
}
