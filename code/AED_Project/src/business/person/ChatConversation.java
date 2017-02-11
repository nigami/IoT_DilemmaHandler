/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.person;

import business.UserAccount.UserAccount;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ilanigam17
 */
public class ChatConversation {

    private String reply;

    private String proposedSoln;

    private Date dateOfReply;

    private Date dateOfproposal;

    private Boolean impMark;// to mark imp conversions

    private UserAccount senderUA;

    private UserAccount replierUA;

    private static int counter;

    private String coversationId;

    private ArrayList<String> teachersScheduleList;

    public ChatConversation() {
        if (senderUA != null) {
            coversationId = senderUA.getUsername() + ++counter;
        } else {
            coversationId = String.valueOf(++counter);
        }
        teachersScheduleList = new ArrayList<>();
    }

    public ArrayList<String> getTeachersScheduleList() {
        return teachersScheduleList;
    }

    public void setTeachersScheduleList(ArrayList<String> teachersScheduleList) {
        this.teachersScheduleList = teachersScheduleList;
    }

    public String getReply() {
        return reply;
    }

    public String getCoversationId() {
        return coversationId;
    }

    public void setCoversationId(String coversationId) {
        this.coversationId = coversationId;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getProposedSoln() {
        return proposedSoln;
    }

    public void setProposedSoln(String proposedSoln) {
        this.proposedSoln = proposedSoln;
    }

    public Date getDateOfReply() {
        return dateOfReply;
    }

    public void setDateOfReply(Date dateOfReply) {
        this.dateOfReply = dateOfReply;
    }

    public Boolean getImpMark() {
        return impMark;
    }

    public void setImpMark(Boolean impMark) {
        this.impMark = impMark;
    }

    public Date getDateOfproposal() {
        return dateOfproposal;
    }

    public void setDateOfproposal(Date dateOfproposal) {
        this.dateOfproposal = dateOfproposal;
    }

    public UserAccount getSenderUA() {
        return senderUA;
    }

    public void setSenderUA(UserAccount senderUA) {
        this.senderUA = senderUA;
    }

    public UserAccount getReplierUA() {
        return replierUA;
    }

    public void setReplierUA(UserAccount replierUA) {
        this.replierUA = replierUA;
    }

    @Override
    public String toString() {
        return coversationId; //To change body of generated methods, choose Tools | Templates.
    }

}
