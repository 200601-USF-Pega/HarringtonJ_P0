package com.revature.models;

import com.revature.services.ConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class Medication implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(Medication.class.getName());

    //This is the name of the Medication
    String medName;

    //This is the ailment that is treated
    String treatedAilment;

    //This is the lethal dosage variable
    int lethalDosage;

    /*------------------------------------------------------------------------------*/
    /* Constructors */
    /*------------------------------------------------------------------------------*/



    public Medication(String medName, String treatedAilment, int lethalDosage) {
        LOGGER.info("New Medication: " + medName + " " + treatedAilment + " " + lethalDosage);
        this.medName = medName;
        this.treatedAilment = treatedAilment;
        this.lethalDosage = lethalDosage;
    }

    /*------------------------------------------------------------------------------*/
    /* ToString */
    /*------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Medication{" +
                "medName='" + medName + '\'' +
                ", treatedAilment='" + treatedAilment + '\'' +
                ", lethalDosage=" + lethalDosage +
                '}';
    }

    /*------------------------------------------------------------------------------*/
    /* Getters and Setters  */
    /*------------------------------------------------------------------------------*/

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getTreatedAilment() {
        return treatedAilment;
    }

    public void setTreatedAilment(String treatedAilment) {
        this.treatedAilment = treatedAilment;
    }

    public int getLethalDosage() {
        return lethalDosage;
    }

    public void setLethalDosage(int lethalDosage) {
        this.lethalDosage = lethalDosage;
    }
}
