/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.teacher;

import userInterface.appointments.AppointmentSchdTeacherResJFrame;
import business.Enterprise.Enterprise;
import business.Organization.Organization;
import business.Organization.Community.TeacherOrganization;
import business.UserAccount.UserAccount;
import business.WorkQueue.AppointmentWorkRequest;
import business.person.ChatConversation;
import business.WorkQueue.StudentPortalWorkRequest;
import business.WorkQueue.WorkRequest;
import business.ecosystem.Ecosystem;
import business.person.ProfessionalsProfile;
import java.awt.CardLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import userInterface.appointments.AppointmentSchdComReqJFrame;

/**
 *
 * @author ilanigam17
 */
public class TeacherHelpOfferingPortalJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount acc;
    private Enterprise enterprise;
    private Ecosystem ecosystem;
    private ProfessionalsProfile tp;
    private WorkRequest workReq;
    private ChatConversation selConversation;

    /**
     * Creates new form TeacherHelpOfferingPortal
     */
    public TeacherHelpOfferingPortalJPanel(JPanel userProcessContainer, ProfessionalsProfile tp, UserAccount acc, Enterprise enterprise, Ecosystem ecosystem) {
        initComponents();
        this.acc = acc;
        this.userProcessContainer = userProcessContainer;
        this.ecosystem = ecosystem;
        this.enterprise = enterprise;
        this.tp = tp;
        nameJLabel.setText("Hi " + acc.getPerson().getName());
        populateTeacherSpecificDetails();
        this.selConversation = null;
        tabbedPane.setVisible(false);
    }

    public void populateTeacherSpecificDetails() {

        String allCoursesExpertise = this.tp.getCourseExpertiseList().toString();
        allCoursesExpertise = allCoursesExpertise.trim();
        //JOptionPane.showMessageDialog(null, " allCoursesExpertise.."+ allCoursesExpertise);
     //   System.err.println(" allCoursesExpertise.." + allCoursesExpertise);

        DefaultTableModel dtm = (DefaultTableModel) TeacherHelpPortalJTable.getModel();
        dtm.setRowCount(0);
        Object row[] = new Object[8];
        if (acc.getWorkQueue().getWorkRequestList().size() > 0) {
            for (WorkRequest workRequest : acc.getWorkQueue().getWorkRequestList()) {
                if (workRequest instanceof AppointmentWorkRequest) {
                    row[0] = workRequest;
                    row[1] = workRequest.getSender().getUsername();
                    row[2] = (workRequest).getMessage();
                    row[3] = ((AppointmentWorkRequest) workRequest).getAppointmentReq().getReasonOfAppointment();
                    row[4] = (workRequest).getRequestDate();
                    row[5] = (workRequest).getStatus();
                    row[6] = (workRequest).getResolveDate();
                    row[7] = ((AppointmentWorkRequest) workRequest).getAppointmentReq().getResponseStatus();
                    dtm.addRow(row);
                }
            }
        }

        for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (org instanceof TeacherOrganization) {
                for (WorkRequest workRequest : org.getWorkQueue().getWorkRequestList()) {
                    if (workRequest instanceof StudentPortalWorkRequest) {
                        String courseRelated = ((StudentPortalWorkRequest) workRequest).getCourseId();
                  //      System.err.println("courseRelated.." + courseRelated);
                        String[] courseRelatedWords = courseRelated.split(" ");
                        Boolean courseMatchCheck = false;
                        for (int i = 0; i < courseRelatedWords.length; i++) {
                            courseMatchCheck = allCoursesExpertise.contains(courseRelatedWords[i]);
                        }
                        if (courseMatchCheck) {//check if course expertise matches with request
                            row[0] = workRequest;
                            row[1] = workRequest.getSender().getUsername();
                            row[2] = "Technology: " + ((StudentPortalWorkRequest) workRequest).getCourseId();
                            row[3] = ((StudentPortalWorkRequest) workRequest).getMessage();
                            row[4] = (workRequest).getRequestDate();
                            row[5] = (workRequest).getStatus();
                            row[6] = (workRequest).getResolveDate();
                            row[7] = "";
                            dtm.addRow(row);
                        }//end if course matched
                    }
                }//for workReq
            }//if org
        }//for org
    }

    public void returnFromAppointment(WorkRequest updatedWorkRequest) {
        populateTeacherSpecificDetails();
    }

    public void showHidePanel() {
        //proposeSolJTabPane.setVisible(true);
        if (this.workReq instanceof StudentPortalWorkRequest) {
            populateProposeSolPane();
            if (((StudentPortalWorkRequest) this.workReq).getSolDiscussionList().size() > 0) {
                proposeSolJTabPane.setVisible(false);
                viewSolnHistJTabPane.setVisible(true);
                populateViewSolnHistPane();
            }
        } else if (this.workReq instanceof AppointmentWorkRequest) {
            ((AppointmentWorkRequest) this.workReq).getAppointmentReq().setProgressBarStatus(50);
            tabbedPane.setVisible(false);
            AppointmentSchdTeacherResJFrame appointmentSchdTeacherResJFrame = new AppointmentSchdTeacherResJFrame(this.workReq, this.workReq.getSender(), acc, enterprise, ecosystem, this);
            appointmentSchdTeacherResJFrame.setVisible(true);
        }
    }

    public void populateProposeSolPane() {
        todayDateJLbl.setText(formatDate(new Date()));
        solnSenderIDJLbl.setText(this.workReq.getSender().getUsername());
        solSubjectJLbl.setText(this.workReq.getMessage());
        solnCourseJLbl.setText(((StudentPortalWorkRequest) this.workReq).getCourseId());
        solnQueryDescJTxtArea.setText(((StudentPortalWorkRequest) this.workReq).getQuery());
    }

    public void populateViewSolnHistPane() {
        populateNotificationList();
        chatSubjectJLbl.setText(this.workReq.getMessage());
        chatPostedDateJLbl.setText(formatDate(this.workReq.getRequestDate()));
        chatCourseJLbl.setText(((StudentPortalWorkRequest) this.workReq).getCourseId());

    }

    public void populateNotificationList() {
        DefaultListModel DLM = new DefaultListModel();
        Boolean newNotification = false;
        for (ChatConversation conversation : ((StudentPortalWorkRequest) this.workReq).getSolDiscussionList()) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String reportDate = df.format(conversation.getDateOfproposal());
            DLM.addElement(conversation.getCoversationId() + ". " + conversation.getSenderUA().getUsername() + " " + reportDate);
            newNotification = true;
        }
        if (newNotification.equals(true)) {
            chatnotificationJList.setModel(DLM);

        }
    }

    public String formatDate(Date inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String StringDate = sdf.format(inputDate);
        return StringDate;
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
        TeacherHelpPortalJTable = new javax.swing.JTable();
        tabbedPane = new javax.swing.JTabbedPane();
        proposeSolJTabPane = new javax.swing.JPanel();
        todayDateJLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        solnSenderIDJLbl = new javax.swing.JLabel();
        solSubjectJLbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        solnCourseJLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        solCommentJTxtArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        submitJBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        solnQueryDescJTxtArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        viewSolnHistJTabPane = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        chatnotificationJList = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatHistoryJTxtArea = new javax.swing.JTextArea();
        chatReplyJBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        chatReplyJTxtArea = new javax.swing.JTextArea();
        chatPostedDateJLbl = new javax.swing.JLabel();
        chatSubjectJLbl = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        chatLastDateJLbl = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        chatCourseJLbl = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        selectRowJBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        nameJLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        TeacherHelpPortalJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Issue Id", "Student ID", "Subject", "Query", "Date Posted", "Query Status", "Date of Response", "Appointment Status"
            }
        ));
        jScrollPane1.setViewportView(TeacherHelpPortalJTable);

        tabbedPane.setBackground(new java.awt.Color(204, 204, 204));
        tabbedPane.setForeground(new java.awt.Color(0, 102, 102));
        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabbedPane.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        proposeSolJTabPane.setBackground(new java.awt.Color(253, 243, 252));

        todayDateJLbl.setText("Today's Date");

        jLabel2.setText("Sender ID:");

        solSubjectJLbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel4.setText("Course related:");

        solCommentJTxtArea.setColumns(20);
        solCommentJTxtArea.setRows(5);
        jScrollPane2.setViewportView(solCommentJTxtArea);

        jLabel6.setText("Comments:");

        submitJBtn.setText("Submit!");
        submitJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJBtnActionPerformed(evt);
            }
        });

        solnQueryDescJTxtArea.setEditable(false);
        solnQueryDescJTxtArea.setColumns(20);
        solnQueryDescJTxtArea.setRows(5);
        jScrollPane6.setViewportView(solnQueryDescJTxtArea);

        jLabel1.setText("Query");

        jLabel5.setText("description:");

        javax.swing.GroupLayout proposeSolJTabPaneLayout = new javax.swing.GroupLayout(proposeSolJTabPane);
        proposeSolJTabPane.setLayout(proposeSolJTabPaneLayout);
        proposeSolJTabPaneLayout.setHorizontalGroup(
            proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(solSubjectJLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(solnSenderIDJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(solnCourseJLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                    .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                        .addGroup(proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proposeSolJTabPaneLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                                .addComponent(submitJBtn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proposeSolJTabPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(todayDateJLbl)
                .addGap(48, 48, 48))
        );
        proposeSolJTabPaneLayout.setVerticalGroup(
            proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                        .addComponent(todayDateJLbl)
                        .addGroup(proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4))
                            .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(solnCourseJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(solnSenderIDJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(solSubjectJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(proposeSolJTabPaneLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(proposeSolJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitJBtn)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Propose a Solution", proposeSolJTabPane);

        viewSolnHistJTabPane.setBackground(new java.awt.Color(238, 238, 243));

        chatnotificationJList.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        chatnotificationJList.setForeground(new java.awt.Color(0, 102, 102));
        chatnotificationJList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatnotificationJListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chatnotificationJListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chatnotificationJListMouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(chatnotificationJList);

        chatHistoryJTxtArea.setEditable(false);
        chatHistoryJTxtArea.setColumns(20);
        chatHistoryJTxtArea.setRows(5);
        jScrollPane4.setViewportView(chatHistoryJTxtArea);

        chatReplyJBtn.setText("Reply");
        chatReplyJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatReplyJBtnActionPerformed(evt);
            }
        });

        chatReplyJTxtArea.setColumns(20);
        chatReplyJTxtArea.setRows(5);
        jScrollPane5.setViewportView(chatReplyJTxtArea);

        chatSubjectJLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chatSubjectJLbl.setForeground(new java.awt.Color(204, 0, 0));

        jLabel7.setText("Last Update date:");

        chatLastDateJLbl.setText(" ");

        jLabel8.setText("View Proposal History:");

        jLabel9.setText("Course related:");

        chatCourseJLbl.setText(" ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel10.setText("Provide our suggestions:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel11.setText("Please select proposed solution history:");

        javax.swing.GroupLayout viewSolnHistJTabPaneLayout = new javax.swing.GroupLayout(viewSolnHistJTabPane);
        viewSolnHistJTabPane.setLayout(viewSolnHistJTabPaneLayout);
        viewSolnHistJTabPaneLayout.setHorizontalGroup(
            viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                        .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewSolnHistJTabPaneLayout.createSequentialGroup()
                                .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4)
                                    .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5)
                                    .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                                        .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                                                .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel9))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(chatLastDateJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(chatCourseJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel10))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewSolnHistJTabPaneLayout.createSequentialGroup()
                                .addComponent(chatSubjectJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chatPostedDateJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewSolnHistJTabPaneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(chatReplyJBtn)))
                        .addGap(38, 38, 38))
                    .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        viewSolnHistJTabPaneLayout.setVerticalGroup(
            viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(chatPostedDateJLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(chatSubjectJLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(chatLastDateJLbl)
                    .addComponent(jLabel8))
                .addGap(11, 11, 11)
                .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewSolnHistJTabPaneLayout.createSequentialGroup()
                        .addGroup(viewSolnHistJTabPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(chatCourseJLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chatReplyJBtn)
                .addGap(55, 55, 55))
        );

        tabbedPane.addTab("View Solutions History", viewSolnHistJTabPane);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Teacher's Helping Portal");

        selectRowJBtn.setText("Select row");
        selectRowJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectRowJBtnActionPerformed(evt);
            }
        });

        jButton1.setText("<<Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        nameJLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameJLabel.setForeground(new java.awt.Color(0, 153, 153));
        nameJLabel.setText("jLabel12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(342, 342, 342)
                        .addComponent(selectRowJBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameJLabel)
                .addGap(51, 51, 51))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nameJLabel)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(selectRowJBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectRowJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectRowJBtnActionPerformed
        // TODO add your handling code here:
        if (TeacherHelpPortalJTable.getSelectedRow() > -1) {
            DefaultTableModel dtm = (DefaultTableModel) TeacherHelpPortalJTable.getModel();
            this.workReq = (WorkRequest) dtm.getValueAt(TeacherHelpPortalJTable.getSelectedRow(), 0);
            if (this.workReq instanceof StudentPortalWorkRequest) {
                tabbedPane.setVisible(true);
                this.workReq = (StudentPortalWorkRequest) dtm.getValueAt(TeacherHelpPortalJTable.getSelectedRow(), 0);
            } else if (this.workReq instanceof AppointmentWorkRequest) {
                this.workReq = (AppointmentWorkRequest) dtm.getValueAt(TeacherHelpPortalJTable.getSelectedRow(), 0);
            }
            showHidePanel();
        }

    }//GEN-LAST:event_selectRowJBtnActionPerformed

    private void chatnotificationJListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatnotificationJListMouseClicked
        // TODO add your handling code here:
        this.selConversation = null;
        if (chatnotificationJList.getSelectedValue() != null) {
            String[] part = String.valueOf(chatnotificationJList.getSelectedValue()).split(" ");

            for (ChatConversation conversation : ((StudentPortalWorkRequest) this.workReq).getSolDiscussionList()) {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String reportDate = df.format(conversation.getDateOfproposal());
                if ((conversation.getCoversationId().equals(part[0].substring(0, part[0].indexOf('.')))) && (reportDate.equals(part[2] + " " + part[3]))) {
                    this.selConversation = new ChatConversation();
                    this.selConversation = conversation;
                    if (conversation.getReply().isEmpty()) {
                        chatHistoryJTxtArea.setText(conversation.getProposedSoln());
                    } else {
                        String newSolution = conversation.getProposedSoln();
                        newSolution = newSolution + "\n\n" + conversation.getReplierUA().getUsername() + "\t" + conversation.getDateOfReply() + "\n" + conversation.getReply();
                        chatHistoryJTxtArea.setText(newSolution);
                    }
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "No panel match");
                }
            }
        }

    }//GEN-LAST:event_chatnotificationJListMouseClicked

    private void chatnotificationJListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatnotificationJListMouseEntered
        // TODO add your handling code here:
        chatnotificationJList.getSelectionBackground().darker();
    }//GEN-LAST:event_chatnotificationJListMouseEntered

    private void chatnotificationJListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatnotificationJListMouseExited
        // TODO add your handling code here:
        chatnotificationJList.getSelectionBackground().brighter();
    }//GEN-LAST:event_chatnotificationJListMouseExited

    private void submitJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJBtnActionPerformed
        if (!solCommentJTxtArea.getText().isEmpty()) {
            this.workReq.setCheckedNotification(false);
            this.workReq.setReceiver(acc);
            this.workReq.setResolveDate(new Date());
            this.workReq.setStatus("Solution provided");
            ChatConversation conversation = new ChatConversation();
            conversation.setProposedSoln(solCommentJTxtArea.getText());
            conversation.setReplierUA(acc);
            conversation.setSenderUA(this.workReq.getSender());
            conversation.setDateOfReply(null);
            conversation.setReply("");
            conversation.setImpMark(false);
            conversation.setDateOfproposal(new Date());
            conversation.setTeachersScheduleList(this.tp.getScheduleList());

            ((StudentPortalWorkRequest) this.workReq).getSolDiscussionList().add(conversation);
            JOptionPane.showMessageDialog(null, "Solution Proposed successfully!");

            solnQueryDescJTxtArea.setText("");
            solCommentJTxtArea.setText("");
            solSubjectJLbl.setText("");
            solnCourseJLbl.setText("");
            solnSenderIDJLbl.setText("");
            populateTeacherSpecificDetails();

        } else {
            JOptionPane.showMessageDialog(null, "Please enter comment!");
        }
    }//GEN-LAST:event_submitJBtnActionPerformed

    private void chatReplyJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatReplyJBtnActionPerformed
        // TODO add your handling code here:
        if (this.selConversation != null) {
            if (!chatReplyJTxtArea.getText().isEmpty()) {
                this.workReq.setCheckedNotification(false);
                this.workReq.setStatus("Answered");
                this.selConversation.setReplierUA(acc);
                // this.selConversation.setImpMark(evalImpCheckBox.isSelected());
                this.selConversation.setDateOfReply(new Date());
                this.selConversation.setReply(chatReplyJTxtArea.getText());
                this.selConversation.setProposedSoln(chatHistoryJTxtArea.getText());
                // this.selConversation.set
                populateTeacherSpecificDetails();
                JOptionPane.showMessageDialog(null, "Response recorded!");
                chatReplyJTxtArea.setText("");
                chatHistoryJTxtArea.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "No response provided!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No conversation selected!");
        }
    }//GEN-LAST:event_chatReplyJBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TeacherHelpPortalJTable;
    private javax.swing.JLabel chatCourseJLbl;
    private javax.swing.JTextArea chatHistoryJTxtArea;
    private javax.swing.JLabel chatLastDateJLbl;
    private javax.swing.JLabel chatPostedDateJLbl;
    private javax.swing.JButton chatReplyJBtn;
    private javax.swing.JTextArea chatReplyJTxtArea;
    private javax.swing.JLabel chatSubjectJLbl;
    private javax.swing.JList chatnotificationJList;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JPanel proposeSolJTabPane;
    private javax.swing.JButton selectRowJBtn;
    private javax.swing.JTextArea solCommentJTxtArea;
    private javax.swing.JLabel solSubjectJLbl;
    private javax.swing.JLabel solnCourseJLbl;
    private javax.swing.JTextArea solnQueryDescJTxtArea;
    private javax.swing.JLabel solnSenderIDJLbl;
    private javax.swing.JButton submitJBtn;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel todayDateJLbl;
    private javax.swing.JPanel viewSolnHistJTabPane;
    // End of variables declaration//GEN-END:variables
}
