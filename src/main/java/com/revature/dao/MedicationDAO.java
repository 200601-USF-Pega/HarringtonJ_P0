package com.revature.dao;

import com.revature.models.Medication;

import java.util.List;

public interface MedicationDAO {

    public List<Medication> getAllMedications();
    public boolean addNewMedication(Medication medication);
    public boolean removeMedication(String medicationName);



}
