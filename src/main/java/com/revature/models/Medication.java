package com.revature.models;

import java.io.Serializable;

public class Medication implements Serializable {

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
