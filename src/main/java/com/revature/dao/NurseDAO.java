package com.revature.dao;

import com.revature.models.Nurse;

import java.util.List;

public interface NurseDAO {

    public List<Nurse> getAllNurses();
    public boolean addNurse(Nurse nurse);
    public boolean removeNurse(String firstName, String lastName);



}
