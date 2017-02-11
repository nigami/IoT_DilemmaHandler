/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.doctor;

import business.UserAccount.UserAccount;
import business.WorkQueue.PersonalHealthIssueWorkRequest;
import business.WorkQueue.WorkRequest;
import business.patient.VitalSignInfo;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMNode;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class DoctorProcessWorkRequestJPanel extends javax.swing.JPanel {

    JPanel userProcessContainer;
    PersonalHealthIssueWorkRequest request;
    private static int count = 1;
    private UserAccount acc;
    public static final int MIN_ZOOM = 0;
    public static final int MAX_ZOOM = 21;
    private static int zoomValue = 4;

    /**
     * Creates new form ProcessWorkRequestJPanel
     */
    public DoctorProcessWorkRequestJPanel(JPanel userProcessContainer, PersonalHealthIssueWorkRequest request) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.request = request;
        this.acc = request.getSender();
        populateBoodgrpCombo();
        qrCodelabel.setIcon(null);
        populatePatientInfo();
        // MapsInternalJFrame.setVisible(false);
        vitalInfoJPanel.setVisible(false);
        contactInfoJPanel.setVisible(false);
        populateIssueSummary();
        populateFileTable();
    }

    public void populateBoodgrpCombo() {
        bloodgrpCombo1.addItem("AB");
        bloodgrpCombo1.addItem("+A");
        bloodgrpCombo1.addItem("-A");
        bloodgrpCombo1.addItem("-AB");
        bloodgrpCombo1.addItem("+A");
        bloodgrpCombo1.addItem("-A");
        bloodgrpCombo1.addItem("-O");
        bloodgrpCombo1.addItem("+O");
    }

    public void populatePatientInfo() {
        nameJTextField1.setText(acc.getPerson().getName());
        idJTextField1.setText(request.getId());
        ageJTextField1.setText(String.valueOf(acc.getPerson().getPatient().getAge()));
        bloodgrpCombo1.setSelectedItem(acc.getPerson().getPatient().getBloodGrp());
        phNumJLbl.setText(String.valueOf(acc.getPerson().getPhoneNum()));
        emailInfoJLbl.setText(acc.getPerson().getEmailId());
        populateVitalInfo();

    }

    public void populateIssueSummary() {
        issueDetailsJtxt.setText(request.getIssueDetails());
        // JScrollPane scroll= new JScrollPane(issueDetailsJtxt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageJLbl.setText(request.getMessage());
    }

    public void populateVitalInfo() {
        VitalSignInfo vitalSign = request.getLatestVital();
        respRateJTextField.setText(String.valueOf(vitalSign.getRespRate()));
        heartRateJTextField.setText(String.valueOf(vitalSign.getHeartRate()));
        dateOfTakenJTxt.setText(String.valueOf(vitalSign.getCurrentTime()));
        sysBPJTextField.setText(String.valueOf(vitalSign.getSystolicBP()));
        weightJTextField.setText(String.valueOf(vitalSign.getWeight()));
        // hivStatusJTxt.setText(String.valueOf(vitalSign.getHivStatus()));

    }

    public void populateFileTable() {
      //  System.err.println("inside populateFileTable");
        DefaultTableModel dtm = (DefaultTableModel) fileJtable.getModel();
        dtm.setRowCount(0);

        int fileCount = 1;
        for (File file : request.getUploadList()) {
            Object row[] = new Object[2];
           // System.err.println("inside populateFileTable" + file.getAbsolutePath() + " ::" + file);
            // row[0]="file"+fileCount++;
            row[0] = file.getAbsolutePath();
            row[1] = file;
            dtm.addRow(row);
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

        jLabel1 = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        qrCodelabel = new javax.swing.JLabel();
        contactDetailDisplayJBtn = new javax.swing.JButton();
        vitalInfoDisplayJBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nameJTextField1 = new javax.swing.JTextField();
        idJTextField1 = new javax.swing.JTextField();
        bloodgrpCombo1 = new javax.swing.JComboBox();
        ageJTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        issueDetailsJtxt = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        messageJLbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultJtxtArea = new javax.swing.JTextArea();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        contactInfoJPanel = new javax.swing.JPanel();
        smsJBtn = new javax.swing.JButton();
        emailInfoJLbl = new javax.swing.JLabel();
        phNumJLbl = new javax.swing.JLabel();
        AddMapJBtn = new javax.swing.JButton();
        sendMailJBtn = new javax.swing.JButton();
        vitalInfoJPanel = new javax.swing.JPanel();
        heartRateJTextField = new javax.swing.JTextField();
        sysBPJTextField = new javax.swing.JTextField();
        weightJTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        respRateJTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        dateOfTakenJTxt = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        submitJButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        fileJtable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(2500, 32767));
        setPreferredSize(new java.awt.Dimension(2500, 830));

        jLabel1.setText("Result");

        backJButton.setText("Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Process Request");

        qrCodelabel.setBackground(new java.awt.Color(255, 255, 255));

        contactDetailDisplayJBtn.setText("View Contact Details");
        contactDetailDisplayJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactDetailDisplayJBtnActionPerformed(evt);
            }
        });

        vitalInfoDisplayJBtn.setText("View Vital Details");
        vitalInfoDisplayJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vitalInfoDisplayJBtnActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(239, 239, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel9.setText("ID");

        jLabel10.setText("Age");

        nameJTextField1.setEditable(false);
        nameJTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameJTextField1ActionPerformed(evt);
            }
        });

        idJTextField1.setEditable(false);
        idJTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idJTextField1ActionPerformed(evt);
            }
        });

        bloodgrpCombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodgrpCombo1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Blood Group");

        jLabel12.setText("Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bloodgrpCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ageJTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameJTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idJTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameJTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idJTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ageJTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bloodgrpCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        issueDetailsJtxt.setEditable(false);
        issueDetailsJtxt.setColumns(20);
        issueDetailsJtxt.setRows(5);
        jScrollPane1.setViewportView(issueDetailsJtxt);

        jLabel2.setText("Issue details:");

        messageJLbl.setText("123");

        jLabel6.setText("Issue Summary:");

        resultJtxtArea.setColumns(20);
        resultJtxtArea.setRows(5);
        jScrollPane2.setViewportView(resultJtxtArea);

        contactInfoJPanel.setBackground(new java.awt.Color(255, 255, 255));
        contactInfoJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contact Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        smsJBtn.setBackground(new java.awt.Color(255, 204, 51));
        smsJBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        smsJBtn.setText("sms");
        smsJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smsJBtnActionPerformed(evt);
            }
        });

        emailInfoJLbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emailInfoJLbl.setForeground(new java.awt.Color(51, 51, 255));
        emailInfoJLbl.setText("No Email Address Available");

        phNumJLbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        phNumJLbl.setForeground(new java.awt.Color(0, 0, 255));
        phNumJLbl.setText("No Phone Number Available");

        AddMapJBtn.setBackground(new java.awt.Color(204, 255, 204));
        AddMapJBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        AddMapJBtn.setForeground(new java.awt.Color(0, 102, 51));
        AddMapJBtn.setText("Locate Address");
        AddMapJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMapJBtnActionPerformed(evt);
            }
        });

        sendMailJBtn.setBackground(new java.awt.Color(255, 204, 51));
        sendMailJBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sendMailJBtn.setText("Send mail");
        sendMailJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMailJBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contactInfoJPanelLayout = new javax.swing.GroupLayout(contactInfoJPanel);
        contactInfoJPanel.setLayout(contactInfoJPanelLayout);
        contactInfoJPanelLayout.setHorizontalGroup(
            contactInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactInfoJPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(AddMapJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addGroup(contactInfoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailInfoJLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phNumJLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addGroup(contactInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sendMailJBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(smsJBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addGap(69, 69, 69))
        );
        contactInfoJPanelLayout.setVerticalGroup(
            contactInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactInfoJPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(contactInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailInfoJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendMailJBtn))
                .addGap(18, 18, 18)
                .addGroup(contactInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(smsJBtn)
                    .addComponent(phNumJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(AddMapJBtn)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        vitalInfoJPanel.setBackground(new java.awt.Color(239, 239, 239));
        vitalInfoJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Vital Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 153))); // NOI18N

        heartRateJTextField.setEnabled(false);

        sysBPJTextField.setEnabled(false);

        weightJTextField.setEnabled(false);
        weightJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weightJTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Respiratory Rate");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Heart Rate");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Systolic Blood Pressure");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Weight(In pounds)");

        respRateJTextField.setEnabled(false);
        respRateJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                respRateJTextFieldActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Date of taken");

        dateOfTakenJTxt.setEditable(false);

        jButton3.setText("History");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vitalInfoJPanelLayout = new javax.swing.GroupLayout(vitalInfoJPanel);
        vitalInfoJPanel.setLayout(vitalInfoJPanelLayout);
        vitalInfoJPanelLayout.setHorizontalGroup(
            vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vitalInfoJPanelLayout.createSequentialGroup()
                .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vitalInfoJPanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vitalInfoJPanelLayout.createSequentialGroup()
                                .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel15))
                                .addGap(41, 41, 41))
                            .addGroup(vitalInfoJPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(61, 61, 61)))
                        .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(respRateJTextField)
                            .addComponent(heartRateJTextField)
                            .addComponent(sysBPJTextField)
                            .addComponent(weightJTextField)
                            .addComponent(dateOfTakenJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(vitalInfoJPanelLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        vitalInfoJPanelLayout.setVerticalGroup(
            vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vitalInfoJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(dateOfTakenJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(respRateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(heartRateJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(sysBPJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vitalInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(weightJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jButton3)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(contactInfoJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(vitalInfoJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(contactInfoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 247, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vitalInfoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(239, 239, 239)))
        );
        jLayeredPane1.setLayer(contactInfoJPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(vitalInfoJPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        submitJButton.setText("Submit Result");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });

        fileJtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "File Name", "Uploads"
            }
        ));
        fileJtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fileJtableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(fileJtable);
        if (fileJtable.getColumnModel().getColumnCount() > 0) {
            fileJtable.getColumnModel().getColumn(1).setHeaderValue("Uploads");
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(submitJButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(backJButton, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(vitalInfoDisplayJBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(contactDetailDisplayJBtn))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(messageJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qrCodelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1551, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(qrCodelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contactDetailDisplayJBtn)
                                    .addComponent(vitalInfoDisplayJBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(messageJLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backJButton)
                .addContainerGap(208, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed

        userProcessContainer.remove(this);
        Component[] componentArray = userProcessContainer.getComponents();
        Component component = componentArray[componentArray.length - 1];
        DoctorWorkAreaJPanel dwjp = (DoctorWorkAreaJPanel) component;
        dwjp.populateTable();
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        request.setTestResult(resultJtxtArea.getText());
        request.setStatus("Completed");
        request.setResolveDate(new Date());

        qrGeneratorable(request);
        JOptionPane.showMessageDialog(null, "Result submitted successfully!");

    }//GEN-LAST:event_submitJButtonActionPerformed

    public void qrGeneratorable(WorkRequest request) {
        String details = ("Name: " + ((PersonalHealthIssueWorkRequest) request).getSender().getPerson() + "\n" + "Request Message: " + request.getMessage() + "\n" + "Observation " + ((PersonalHealthIssueWorkRequest) request).getTestResult() + "\n" + "Status: " + request.getStatus() + "\n" + "Request Date: " + request.getRequestDate().toString() + "\n" + "Resolved Date: " + request.getResolveDate().toString());
        ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from(details).to(net.glxn.qrgen.image.ImageType.JPG).stream();

        File f = new File( ((PersonalHealthIssueWorkRequest) request).getSender().getUsername().toString()+"_"+((PersonalHealthIssueWorkRequest) request).getId() + ".jpg");
        try {
            FileOutputStream fos = new FileOutputStream(f);

            fos.write(out.toByteArray());
            fos.flush();
            qrCodelabel.setIcon(new ImageIcon(f.toString()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error while generating QR");
        }
        count++;
    }
    private void nameJTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameJTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameJTextField1ActionPerformed

    private void idJTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idJTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idJTextField1ActionPerformed

    private void bloodgrpCombo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodgrpCombo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bloodgrpCombo1ActionPerformed

    private void smsJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smsJBtnActionPerformed
        // TODO add your handling code here:
        sendSMS.sendMessage("+1" + this.acc.getPerson().getPhoneNum(), "I have checked you vital signs,login to Adolescent Handler App and See results");
        JOptionPane.showMessageDialog(null, "Message sent!");
    }//GEN-LAST:event_smsJBtnActionPerformed

    private void AddMapJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMapJBtnActionPerformed
        // TODO add your handling code here:
//       MapsIFrame mapsJFrame= new MapsIFrame();
//        mapsJFrame.setVisible(true);

        final Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);

        JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (zoomValue < MAX_ZOOM) {
                    browser.executeJavaScript("map.setZoom(" + ++zoomValue + ")");
                }
            }
        });

        JButton zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (zoomValue > MIN_ZOOM) {
                    browser.executeJavaScript("map.setZoom(" + --zoomValue + ")");
                }
            }
        });

        JButton setMarkerButton = new JButton("Set Marker");
        setMarkerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browser.executeJavaScript("var myLatlng = new google.maps.LatLng(48.4431727,23.0488126);\n"
                        + "var marker = new google.maps.Marker({\n"
                        + "    position: myLatlng,\n"
                        + "    map: map,\n"
                        + "    title: 'Hello World!'\n"
                        + "});");
            }
        });

        JPanel toolBar = new JPanel();
        toolBar.add(zoomInButton);
        toolBar.add(zoomOutButton);
        toolBar.add(setMarkerButton);

        JFrame frame = new JFrame("newhtml.html");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(toolBar, BorderLayout.SOUTH);
        frame.add(browserView, BorderLayout.CENTER);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    DOMDocument document = event.getBrowser().getDocument();
//                    DOMElement link = document.findElement(By.id("address"));
//                    Map<String, String> attributes = link.getAttributes();
//                    for (String attrName : attributes.keySet()) {
//                        System.out.println(attrName + " ===================================================== " + attributes.get(attrName));
//                    }
//                       DOMElement button = document.findElement(By.id("address"));
//                    button.getChildren().get(0).setNodeValue("452009");

//                        DOMNode root = document.findElement(By.id("floating-panel"));
//                        
//                        DOMNode textNode = document.createTextNode("02120");
//                        DOMElement paragraph = document.createElement("p");
//                        paragraph.appendChild(textNode);
//                        root.appendChild(paragraph);
                    String attrName = "address" + 1;
                    DOMElement firstName = document.findElement(By.name(attrName));
                    firstName.setAttribute("value", "0" + String.valueOf(acc.getPerson().getZipCode()));

                    DOMNode link = document.findElement(By.name("button"));
                    if (link != null) {
                        link.click();
                    }

//                    DOMElement lastName = document.findElement(By.name("address2"));
//                    lastName.setAttribute("value", "452001");
                }
            }
        });

        browser.loadURL("//C:/Users/ilanigam17/Documents/NetBeansProjects/AED_Project_1/src/userInterface/doctor/newhtml.html");
        //browser.loadURL("http://maps.google.com");

    }//GEN-LAST:event_AddMapJBtnActionPerformed

    private void weightJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weightJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weightJTextFieldActionPerformed

    private void respRateJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_respRateJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_respRateJTextFieldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ReportingPatientVitalHistJPanel reportingPatientVitalHistJPanel = new ReportingPatientVitalHistJPanel(acc.getPerson().getPatient(), userProcessContainer);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        userProcessContainer.add("reportingPatientVitalHistJPanel", reportingPatientVitalHistJPanel);
        layout.next(userProcessContainer);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void contactDetailDisplayJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactDetailDisplayJBtnActionPerformed
        // TODO add your handling code here:
        vitalInfoJPanel.setVisible(false);
        contactInfoJPanel.setVisible(true);
    }//GEN-LAST:event_contactDetailDisplayJBtnActionPerformed

    private void vitalInfoDisplayJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vitalInfoDisplayJBtnActionPerformed
        // TODO add your handling code here:
        vitalInfoJPanel.setVisible(true);
        contactInfoJPanel.setVisible(false);
    }//GEN-LAST:event_vitalInfoDisplayJBtnActionPerformed

    private void sendMailJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMailJBtnActionPerformed
        // TODO add your handling code here:
        DoctorSendMailJFrame doctorSendMailJFrame = new DoctorSendMailJFrame(emailInfoJLbl.getText(), this);
      //  System.out.println("inside login");
        doctorSendMailJFrame.setVisible(true);
    }//GEN-LAST:event_sendMailJBtnActionPerformed

    private void fileJtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileJtableMouseClicked
        // TODO add your handling code here:
        int row = fileJtable.getSelectedRow();
        String currentLine;
        String value = (String) (fileJtable.getModel().getValueAt(row, 0));
        try {
            for (File file : request.getUploadList()) {
                if (fileJtable.getModel().getValueAt(row, 0).equals(file.getAbsolutePath())) {
                    BufferedReader br = new BufferedReader(new FileReader((File) fileJtable.getModel().getValueAt(row, 1)));
                    //System.err.println("ila.."+request.getSender().getPerson().getId()+"_"+file.getAbsolutePath().substring((file.getAbsolutePath().lastIndexOf("\'")),(file.getAbsolutePath().length()-1)));
                    FileWriter writer = new FileWriter("parent\\" +  request.getSender().getUsername().toString()+"_"+request.getId() + "_Myfile.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    //writer = Files.newBufferedWriter(writer, StandardCharsets.UTF_8);
                    while ((currentLine = br.readLine()) != null) {
                        bufferedWriter.write(currentLine);
                        // must do this: .readLine() will have stripped line endings
                        bufferedWriter.newLine();

                    }
                    bufferedWriter.close();
                    JOptionPane.showMessageDialog(null, "File Saved at location: " + "\\parent\\" + request.getSender().getPerson().getId() + "_Myfile.txt");
                    // readFile(request.getSender().getPerson().getId()+"_Myfile.txt");

                    break;
                }
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_fileJtableMouseClicked

    /**
     * Open and read a file, and return the lines in the file as a list of
     * Strings. (Demonstrates Java FileReader, BufferedReader, and Java5.)
     */
    private void readFile(String filename) {
        List<String> records = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }

            //System.out.println("records.........."+records);
            reader.close();

        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMapJBtn;
    private javax.swing.JTextField ageJTextField1;
    private javax.swing.JButton backJButton;
    private javax.swing.JComboBox bloodgrpCombo1;
    private javax.swing.JButton contactDetailDisplayJBtn;
    private javax.swing.JPanel contactInfoJPanel;
    private javax.swing.JTextField dateOfTakenJTxt;
    private javax.swing.JLabel emailInfoJLbl;
    private javax.swing.JTable fileJtable;
    private javax.swing.JTextField heartRateJTextField;
    private javax.swing.JTextField idJTextField1;
    private javax.swing.JTextArea issueDetailsJtxt;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel messageJLbl;
    private javax.swing.JTextField nameJTextField1;
    private javax.swing.JLabel phNumJLbl;
    private javax.swing.JLabel qrCodelabel;
    private javax.swing.JTextField respRateJTextField;
    private javax.swing.JTextArea resultJtxtArea;
    private javax.swing.JButton sendMailJBtn;
    private javax.swing.JButton smsJBtn;
    private javax.swing.JButton submitJButton;
    private javax.swing.JTextField sysBPJTextField;
    private javax.swing.JButton vitalInfoDisplayJBtn;
    private javax.swing.JPanel vitalInfoJPanel;
    private javax.swing.JTextField weightJTextField;
    // End of variables declaration//GEN-END:variables
}
