package com.revature.dao;

import com.revature.models.Nurse;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NurseDAO_OnlineImplTest extends TestCase {

    @Mock
    ResultSet resultSet;
    @Mock
    NurseDAO nurseDAO;
    @Mock
    NurseDAO_OnlineImpl nurseDAO_online;
    @Mock
    PreparedStatement preparedStatement;

    private Nurse nurse;

    @Before public void initialize() throws Exception {

        nurseDAO = nurseDAO_online;
        nurse = new Nurse("Klye", "Noid", false, 5);


        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getString(1)).thenReturn(nurse.getFirstname());
        when(resultSet.getString(2)).thenReturn(nurse.getLastname());
        when(resultSet.getBoolean(3)).thenReturn(nurse.getMedCert());
        when(resultSet.getInt(4)).thenReturn(nurse.getAssignments());
        when(resultSet.next()).thenReturn(true);





    }




@Test
    public void testGetAllNurses() throws SQLException {

    List<Nurse> nurseList = new ArrayList<Nurse>();
    resultSet = preparedStatement.executeQuery();
    if (resultSet.next()){
        Nurse nurse1 = new Nurse(resultSet.getString(1), resultSet.getString(2), resultSet.getBoolean(3), resultSet.getInt(4));
        nurseList.add(nurse1);
    }
    for(Nurse nurse3: nurseList)
        System.out.println(nurse3.toString());

    assertEquals(1, nurseList.size());

    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAllNursesNoPrint() throws SQLException {

        List<Nurse> nurseList = new ArrayList<Nurse>();
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            Nurse nurse1 = new Nurse(resultSet.getString(1), resultSet.getString(2), resultSet.getBoolean(3), resultSet.getInt(4));
            nurseList.add(nurse1);
        }

        assertEquals(nurse, nurseList.get(1).toString());

    }

@Test
    public void testAddNurse() {
        nurseDAO.addNurse(null);
    }

    @Test
    public void testRemoveNurse() {
        nurseDAO.removeNurse(-80);
    }
@Test
    public void testUpdateNurse() {
nurseDAO.updateNurse(-89854);
    }


}