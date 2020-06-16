package com.revature.models;

import com.revature.services.ConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class Nurse implements Serializable, Comparable<Nurse> {

    private static final Logger LOGGER = LogManager.getLogger(Nurse.class.getName());

    //These two Strings are for the first and last name for the Nurse obj
    private String firstname;
    private String lastname;

    //This boolean asserts whether the Nurse is Certified to handle Medication
    private boolean isMedCert;

    //This is the max number of residents a nurse can handle
    private int assignments;

    private int nurseid;

    /*------------------------------------------------------------------------------*/
                                 /* Constructors */
    /*------------------------------------------------------------------------------*/

    public Nurse(String firstname, String lastname, boolean isMedCert, int assignments) {
        LOGGER.info("New Nurse Created: " + firstname + " " + lastname + " " + isMedCert);
        this.firstname = firstname;
        this.lastname = lastname;
        this.isMedCert = isMedCert;
        this.assignments = assignments;
        this.nurseid = 0;
    }

    public Nurse(String firstname, String lastname, boolean isMedCert, int assignments, int nurseId) {
        LOGGER.info("New Nurse Created: " + firstname + " " + lastname + " " + isMedCert);
        this.firstname = firstname;
        this.lastname = lastname;
        this.isMedCert = isMedCert;
        this.assignments = assignments;
        this.nurseid = nurseId;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isMedCert=" + isMedCert +
                ", assignments=" + assignments +
                '}';
    }

    public String toStringWId() {
        return "Nurse{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isMedCert=" + isMedCert +
                ", assignments=" + assignments +
                ", nurseid=" + nurseid +
                '}';
    }

@Override
public int compareTo(Nurse o){

        if(this.assignments > o.assignments) {
            return 1;
        }
        if(this.assignments < o.assignments) {
            return -1;
        }
        else
            return 0;
}



    /*------------------------------------------------------------------------------*/
                            /* Getters and Setters  */
    /*------------------------------------------------------------------------------*/
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean getMedCert() {
        return isMedCert;
    }

    public void setMedCert(boolean medCert) {
        isMedCert = medCert;
    }

    public int getAssignments() {
        return assignments;
    }

    public void setAssignments(int assignments) {
        this.assignments = assignments;
    }
}
