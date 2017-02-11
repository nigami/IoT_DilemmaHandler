/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.WorkQueue;

import business.appointments.Appointment;
import business.person.ChatConversation;
import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class StudentPortalWorkRequest extends WorkRequest {

    private String courseId;
    private ArrayList<ChatConversation> solDiscussionList;
    private String query;
    private static int count = 1;

    public StudentPortalWorkRequest() {
        solDiscussionList = new ArrayList<>();
    }

    public ArrayList<ChatConversation> getSolDiscussionList() {
        return solDiscussionList;
    }

    public void setSolDiscussionList(ArrayList<ChatConversation> solDiscussionList) {
        this.solDiscussionList = solDiscussionList;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

}
