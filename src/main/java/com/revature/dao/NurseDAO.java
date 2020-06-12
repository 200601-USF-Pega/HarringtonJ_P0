package com.revature.dao;

import com.revature.models.Medication;
import com.revature.models.Nurse;
import com.revature.models.Resident;

import java.util.List;

public interface NurseDAO {

    public List<Nurse> getAllNurses();
    public List<Nurse> getAllNursesNoPrint();
    public boolean addNurse(Nurse nurse);
    public boolean removeNurse(int indexNum);
    public boolean updateNurse(int indexNum);
    public boolean addNurseToResident();




}
