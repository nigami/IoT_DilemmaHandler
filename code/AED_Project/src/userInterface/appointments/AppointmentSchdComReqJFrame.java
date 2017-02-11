/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.appointments;

import business.Enterprise.Enterprise;
import business.Organization.Community.TeacherOrganization;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import business.WorkQueue.AppointmentWorkRequest;
import business.WorkQueue.WorkRequest;
import business.appointments.Appointment;
import business.appointments.AppointmentHistory;
import business.ecosystem.Ecosystem;
import business.person.ProfessionalsProfile;
import business.utils.Validate;
import java.awt.Color;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import userInterface.badMoodDay.StudentTeacherMentorPortalJPanel;
import userInterface.healthCare.ViewHealthReqResultJPanel;

/**
 *
 * @author ilanigam17
 */
public class AppointmentSchdComReqJFrame extends javax.swing.JFrame {

    private UserAccount professionalUserAcc;
    private JPanel sendingJPanelObj;
    private AppointmentHistory appHistory;
    private Ecosystem ecosystem;
    private UserAccount ownUserAcc;
    private Enterprise ent;
    private ProfessionalsProfile tp;
    private String appointmentDays;

    /**
     * Creates new form AppointmentSchdComReqJFrame
     */
    public AppointmentSchdComReqJFrame() {
        initComponents();
    }

    public AppointmentSchdComReqJFrame(String appointmentDays, UserAccount ownUserAcc, UserAccount professionalUserAcc, Enterprise ent, Ecosystem ecosystem, JPanel sendingJPanelObj) {
        initComponents();
        this.ownUserAcc = ownUserAcc;
        this.appointmentDays = appointmentDays;
        this.professionalUserAcc = professionalUserAcc;
        this.ecosystem = ecosystem;
        this.ent = ent;
        profSAppJProgressBar.setValue(25);
        if (sendingJPanelObj instanceof StudentTeacherMentorPortalJPanel) {
            this.sendingJPanelObj = (StudentTeacherMentorPortalJPanel) sendingJPanelObj;
        } else if (sendingJPanelObj instanceof ViewHealthReqResultJPanel) {
            this.sendingJPanelObj = (ViewHealthReqResultJPanel) sendingJPanelObj;
        }
        determineTeacherProfile();
        this.appHistory = professionalUserAcc.getPerson().getAppointHistoryList();
        populateNeccessaryDetails();

    }

    public void determineTeacherProfile() {
        for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
            if (org instanceof TeacherOrganization) {
                for (ProfessionalsProfile teacherProfile : ((TeacherOrganization) org).getTeacherDirectory().getTeacherList()) {
                    if (teacherProfile.getUserAccount() == professionalUserAcc) {
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
        profNameJTxt.setText(professionalUserAcc.getPerson().getName());
        String scheduleList = this.tp.retrieveScheduleList();
        populateAvailabilityCheckBox(scheduleList);
        profHourlyFeeJTxt.setText(String.valueOf(this.tp.getHourlyWage()));
        //profSAppJProgressBar.setValue(professionalUserAcc.getPerson().);
        if ((this.tp.getOnlyPaidMode() != null) && this.tp.getOnlyPaidMode() == true) {
            ProfModeOfPayJCombo.removeAllItems();
            ProfModeOfPayJCombo.addItem("Paid");
        }
    }

    public void populateAvailabilityCheckBox(String scheduleList) {
        if (scheduleList != null) {

            if (scheduleList.contains("Monday")) {
                day1JCheckBox.setBackground(Color.green);
                day1JCheckBox.setEnabled(true);
                if (this.appointmentDays != null && this.appointmentDays.contains("Monday")) {
                    day1JCheckBox.doClick();
                }
            }
            if (scheduleList.contains("Tuesday")) {
                day2JCheckBox.setBackground(Color.green);
                day2JCheckBox.setEnabled(true);
                if (this.appointmentDays != null && this.appointmentDays.contains("Tuesday")) {
                    day2JCheckBox.doClick();
                }
            }
            if (scheduleList.contains("Wednesday")) {
                day3JCheckBox.setBackground(Color.green);
                day3JCheckBox.setEnabled(true);
                if (this.appointmentDays != null && this.appointmentDays.contains("Wednesday")) {
                    day3JCheckBox.doClick();
                }
            }
            if (scheduleList.contains("Thursday")) {
                day4JCheckBox.setBackground(Color.green);
                day4JCheckBox.setEnabled(true);
                if (this.appointmentDays != null && this.appointmentDays.contains("Thursday")) {
                    day4JCheckBox.doClick();
                }
            }
            if (scheduleList.contains("Friday")) {
                day5JCheckBox.setBackground(Color.green);
                day5JCheckBox.setEnabled(true);
                if (this.appointmentDays != null && this.appointmentDays.contains("Friday")) {
                    day5JCheckBox.doClick();
                }
            }
            if (scheduleList.contains("Saturday")) {
                day6JCheckBox.setBackground(Color.green);
                day6JCheckBox.setEnabled(true);
                if (this.appointmentDays != null && this.appointmentDays.contains("Saturday")) {
                    day6JCheckBox.doClick();
                }
            }
            if (scheduleList.contains("Sunday")) {
                day7JCheckBox.setBackground(Color.green);
                day7JCheckBox.setEnabled(true);
                if (this.appointmentDays != null && this.appointmentDays.contains("Sunday")) {
                    day7JCheckBox.doClick();
                }
            }
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
        reqCategoryCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        profReasonJTxtArea = new javax.swing.JTextArea();
        profNameJTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
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
        profHourlyFeeJTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        profAppjDateChooser = new com.toedter.calendar.JDateChooser();
        totalMinsTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(234, 243, 250));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Appointment Schedule");

        reqCategoryCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Healthcare", "Counselling", "Academics" }));
        reqCategoryCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqCategoryComboActionPerformed(evt);
            }
        });

        jLabel5.setText("Request Category:");

        jLabel6.setText("Name Of Professional:");

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

        jButton2.setText("Schedule Appointment");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        profSAppJProgressBar.setStringPainted(true);

        jLabel3.setText("Progress Bar:");

        evalAvailabiltyJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Availability", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 12))); // NOI18N

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

        jLabel4.setText("Days:");

        jLabel8.setText("Charges:");

        ProfModeOfPayJCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Complimentary", "Paid" }));
        ProfModeOfPayJCombo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProfModeOfPayJCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfModeOfPayJComboActionPerformed(evt);
            }
        });

        jLabel9.setText("per hour");

        jLabel11.setText("Total time in mins:");

        totalMinsTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMinsTxtActionPerformed(evt);
            }
        });

        jLabel10.setText("Appointment date:");

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
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(77, 77, 77)
                        .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(evalAvailabiltyJPanelLayout.createSequentialGroup()
                                        .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ProfModeOfPayJCombo, 0, 131, Short.MAX_VALUE)
                                            .addComponent(totalMinsTxt))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(profHourlyFeeJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, Short.MAX_VALUE))
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
                                .addComponent(profAppjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(profHourlyFeeJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(totalMinsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(evalAvailabiltyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(profAppjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButton1.setText("<<Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(profNameJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(reqCategoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(profSAppJProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                                .addComponent(jScrollPane2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton1)
                        .addGap(171, 171, 171)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(evalAvailabiltyJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(reqCategoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel3))
                    .addComponent(profSAppJProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profNameJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(evalAvailabiltyJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addContainerGap(33, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void day1JCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day1JCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_day1JCheckBoxActionPerformed

    private void day5JCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day5JCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_day5JCheckBoxActionPerformed

    private void reqCategoryComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqCategoryComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reqCategoryComboActionPerformed

    private void profNameJTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profNameJTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profNameJTxtActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:7
        if(!profReasonJTxtArea.getText().isEmpty() && !profHourlyFeeJTxt.getText().isEmpty() && !totalMinsTxt.getText().isEmpty() && (profAppjDateChooser.getDate()!=null)){
            Appointment app = new Appointment();
            app.setReasonOfAppointment(profReasonJTxtArea.getText());
            app.setDateOfAppointment(profAppjDateChooser.getDate());
            app.setChargesTypeOfAppointment((String) ProfModeOfPayJCombo.getSelectedItem());
            app.setTimeOfAppointment(totalMinsTxt.getText());
            app.setFeeOfAppointment(profHourlyFeeJTxt.getText());
            app.setModeOfAppointment((String) ProfModeOfPayJCombo.getSelectedItem());
            app.setDayOfAppointment(retrieveScheduleDay());
            if (!retrieveScheduleDay().isEmpty()) {
                app.setDayOfAppointment(retrieveScheduleDay().trim());
            } else {
                app.setDayOfAppointment("");
            }
            app.setProgressBarStatus(50);

            //professionalUserAcc.getPerson().getAppointHistoryList().getAppointmentList().add(app);
            WorkRequest workReq = new AppointmentWorkRequest();
            workReq.setCheckedNotification(false);
            ((AppointmentWorkRequest) workReq).setAppointmentReq(app);
            workReq.setMessage("Appointment with " + professionalUserAcc.getPerson().getName());
            workReq.setRequestDate(new Date());
            workReq.setSender(ownUserAcc);
            workReq.setStatus("Appointment Requested");
            workReq.setReceiver(professionalUserAcc);
            workReq.setResolveDate(null);
            //workReq.setQuery("Need Appointment");
            professionalUserAcc.getWorkQueue().getWorkRequestList().add(workReq);//for community enterprise,in profs' work queue
            ownUserAcc.getWorkQueue().getWorkRequestList().add(workReq);//for community enterprise,in once own work queue

            if (sendingJPanelObj instanceof StudentTeacherMentorPortalJPanel) {
                ((StudentTeacherMentorPortalJPanel) sendingJPanelObj).returnFromAppointment();
            }
            JOptionPane.showMessageDialog(null, "Request sent to concerned professional");
            this.dispose();
          
        }else{
            JOptionPane.showMessageDialog(null,"Appointment request not correct,all the fields are mandatory and in proper format, do you want to continue?");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void ProfModeOfPayJComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfModeOfPayJComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfModeOfPayJComboActionPerformed

    private void totalMinsTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalMinsTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMinsTxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (sendingJPanelObj instanceof StudentTeacherMentorPortalJPanel) {
            ((StudentTeacherMentorPortalJPanel) this.sendingJPanelObj).populatePortalTable();
            this.dispose();
        } else if (sendingJPanelObj instanceof ViewHealthReqResultJPanel) {
            ((ViewHealthReqResultJPanel) sendingJPanelObj).populatePatientInfo();
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AppointmentSchdComReqJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentSchdComReqJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentSchdComReqJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentSchdComReqJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentSchdComReqJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ProfModeOfPayJCombo;
    private javax.swing.JCheckBox day1JCheckBox;
    private javax.swing.JCheckBox day2JCheckBox;
    private javax.swing.JCheckBox day3JCheckBox;
    private javax.swing.JCheckBox day4JCheckBox;
    private javax.swing.JCheckBox day5JCheckBox;
    private javax.swing.JCheckBox day6JCheckBox;
    private javax.swing.JCheckBox day7JCheckBox;
    private javax.swing.JPanel evalAvailabiltyJPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser profAppjDateChooser;
    private javax.swing.JTextField profHourlyFeeJTxt;
    private javax.swing.JTextField profNameJTxt;
    private javax.swing.JTextArea profReasonJTxtArea;
    private javax.swing.JProgressBar profSAppJProgressBar;
    private javax.swing.JComboBox reqCategoryCombo;
    private javax.swing.JTextField totalMinsTxt;
    // End of variables declaration//GEN-END:variables
}
