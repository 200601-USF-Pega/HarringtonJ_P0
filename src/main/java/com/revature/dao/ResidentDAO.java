package com.revature.dao;

import com.revature.models.Resident;

import java.util.List;

public interface ResidentDAO {

    public List<Resident> getAllResidents();
    public List<Resident> getAllResidentsNoPrint();
    public boolean addResident(Resident resident);
    public boolean removeResident(int indexNum);
    public boolean updateResident(int indexNum);
    public List<Resident> getAllResidentsWithMeds();


}
