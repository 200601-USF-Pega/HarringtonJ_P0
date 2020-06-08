package com.revature.dao;

import com.revature.models.Resident;

import java.util.List;

public interface ResidentDAO {

    public List<Resident> getAllResidents();
    public boolean addResident(Resident resident);
    public boolean removeResident(String firstName, String lastName);


}
