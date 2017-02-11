/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.UserAccount;

import business.Role.Role;
import business.WorkQueue.WorkQueue;
import business.person.Person;

/**
 *
 * @author ilanigam17
 */
public class UserAccount {

    private String username;
    private String password;
    private Person person;
    private Role role;
    private WorkQueue workQueue;

    public UserAccount() {
        this.workQueue = new WorkQueue();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Person getPerson() {
        return this.person;
    }

    public WorkQueue getWorkQueue() {
        return this.workQueue;
    }

    public String toString() {
        return this.username;
    }
}
