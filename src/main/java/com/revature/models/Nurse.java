package com.revature.models;

import java.io.Serializable;

public class Nurse implements Serializable {

    //These two Strings are for the first and last name for the Nurse obj
    private String firstname;
    private String lastname;

    //This boolean asserts whether the Nurse is Certified to handle Medication
    private boolean isMedCert;


    /*------------------------------------------------------------------------------*/
                                 /* Constructors */
    /*------------------------------------------------------------------------------*/

    public Nurse(String firstname, String lastname, boolean isMedCert) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.isMedCert = isMedCert;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isMedCert=" + isMedCert +
                '}';
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

    public boolean isMedCert() {
        return isMedCert;
    }

    public void setMedCert(boolean medCert) {
        isMedCert = medCert;
    }
}
