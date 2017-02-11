/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.person;

import java.util.ArrayList;

/**
 *
 * @author ilanigam17
 */
public class PersonDirectory {

    private ArrayList<Person> personList;

    public PersonDirectory() {
        personList = new ArrayList<>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public Person addPerson() {
        Person p = new Person();
        this.personList.add(p);
        return p;
    }

    public Person searchPerson(String name) {
        for (Person p : personList) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }
}
