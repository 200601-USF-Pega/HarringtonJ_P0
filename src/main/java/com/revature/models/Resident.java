package com.revature.models;

import com.revature.services.ConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class Resident implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger(Resident.class.getName());

    //These two Strings are for the first and last name for the Nurse obj
    private String firstName;
    private String lastName;

    //This String holds what ailment the resident has
    private String ailment;

    //This Int Identifies the nurse by id. For matching with Residents
    private int nurseid;

    /*------------------------------------------------------------------------------*/
    /* Constructors */
    /*------------------------------------------------------------------------------*/

    public Resident(String firstName, String lastName, String ailment) {
        LOGGER.info("New Resident Created: " + firstName + " " + lastName + " " + ailment);
        this.firstName = firstName;
        this.lastName = lastName;
        this.ailment = ailment;
        this.nurseid = 0;
    }
    public Resident(String firstName, String lastName, String ailment, int nurseid) {
        LOGGER.info("New Resident Created: " + firstName + " " + lastName + " " + ailment);
        this.firstName = firstName;
        this.lastName = lastName;
        this.ailment = ailment;
        this.nurseid = nurseid;
    }

    /*------------------------------------------------------------------------------*/
    /* toString */
    /*------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Resident{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ailment='" + ailment + '\'' +
                ", nurseid=" + nurseid +
                '}';
    }

    /*------------------------------------------------------------------------------*/
    /* Getters and Setters  */
    /*------------------------------------------------------------------------------*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.firstName = lastName;
    }

    public String getAilment() {
        return ailment;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }

    public int getNurseid() {
        return nurseid;
    }

    public void setNurseid(int nurseid) {
        this.nurseid = nurseid;
    }
}
