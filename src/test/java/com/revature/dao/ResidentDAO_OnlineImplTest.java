package com.revature.dao;

import com.revature.models.Resident;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResidentDAO_OnlineImplTest extends TestCase {

    private ResidentDAO_OnlineImpl mockedResidentDAO_online;
    @Mock
    ResidentDAO mockResidentDAO;

    @Mock
    PreparedStatement mps;
    @Mock
    ResultSet mrs;

    private Resident resident;

    @Before public void initialize() throws Exception {

    mockResidentDAO = mockedResidentDAO_online;
    resident = new Resident("George", "Noid", null);

    when(mps.executeQuery()).thenReturn(mrs);
    when(mrs.first()).thenReturn(true);
    when(mrs.getString(2)).thenReturn(resident.getFirstName());
    when(mrs.getString(3)).thenReturn(resident.getLastName());
    when(mrs.getString(4)).thenReturn(resident.getAilment());
    when(mrs.getInt(5)).thenReturn(resident.getNurseid());
    //when(mps.executeQuery()).thenReturn(mrs);
    }

    @Test
    public void testGetAllResidents() throws SQLException {
        List<Resident> residentList = new ArrayList<Resident>();
        resident = null;
        mrs = mps.executeQuery();
        if (mrs.first()){
            Resident resident1 = new Resident(mrs.getString(2), mrs.getString(3), mrs.getString(4));
            residentList.add(resident1);
        }
        for(Resident resident3: residentList)
        System.out.println(resident3.toString());

        assertEquals(1, residentList.size());


    }

    @Test
    public void testGetAllResidentsNoPrint() throws SQLException {
        List<Resident> residentList = new ArrayList<Resident>();

        Resident resident1 = null;
        mrs = mps.executeQuery();

        resident1 = new Resident(mrs.getString(2), mrs.getString(3), mrs.getString(4));
        residentList.add(resident1);

        assertEquals(resident1.toString(), residentList.get(0).toString());


    }

    @Test(expected = NullPointerException.class)
    public void testAddResident() {
        mockResidentDAO.addResident(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveResident() {
        mockResidentDAO.removeResident(-1);
    }
    @Test(expected = NullPointerException.class)
    public void testUpdateResident() {
        mockResidentDAO.updateResident(-10000);
    }

}