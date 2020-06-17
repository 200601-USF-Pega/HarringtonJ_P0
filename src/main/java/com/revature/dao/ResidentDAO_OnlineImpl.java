package com.revature.dao;

import  com.revature.models.Medication;
import com.revature.models.Nurse;
import com.revature.models.Resident;
import com.revature.services.ConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ResidentDAO_OnlineImpl implements ResidentDAO{

    ConnectionService connectionService = ConnectionService.getInstance();

    private static final Logger LOGGER = LogManager.getLogger(ResidentDAO_OnlineImpl.class.getName());

    //Setting Up Connection to our DataBase
    public ResidentDAO_OnlineImpl(){


    }

    @Override
    public List<Resident> getAllResidents() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Residents
        List<Resident> residentList = new ArrayList<Resident>();

    try {
        LOGGER.info("Attempting to get a list of all Residents.");
        PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM residents;");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            //Gets the data from the specified columns and uses them as parameters to create a new Resident
            Resident resident = new Resident(rs.getString("firstname"), rs.getString("lastname"), rs.getString("ailment"));
            //Adds the new resident to the residentList Array
            residentList.add(resident);
        }

        System.out.println("Currently are: " + residentList.size() + " Residents.");



        for (Resident resident : residentList) {

            System.out.println("["+indexNum+ "] " + resident.toString());
            ++indexNum;
        }
        LOGGER.info("Successfully returned a list of all Residents.");
        return residentList;
    }catch(SQLException e){
        LOGGER.error("Error getting a list of all Residents.");
        e.printStackTrace();

    } catch (Exception e){
        LOGGER.error("Error getting a list of all Residents.");
    }
        LOGGER.error("Returning a null list of all Residents.");
        return null;

    }

    @Override
    public List<Resident> getAllResidentsNoPrint() {

        //Instantiate a new ArrayLists of Residents
        List<Resident> residentList = new ArrayList<Resident>();

        try {
            LOGGER.info("Attempting to get a list of all Residents.");
            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM residents;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Resident
                Resident resident = new Resident(rs.getString("firstname"), rs.getString("lastname"), rs.getString("ailment"));
                //Adds the new resident to the residentList Array
                residentList.add(resident);
            }

            LOGGER.info("Successfully returned a list of all Residents.");
            return residentList;

        }catch(SQLException e){
            LOGGER.error("Error getting a list of all Residents.");
            e.printStackTrace();

        } catch (Exception e){
            LOGGER.error("Error getting a list of all Residents.");
        }
        LOGGER.error("Returning a null list of all Residents.");
        return null;

    }
    
    
    
    
    @Override
    public boolean addResident(Resident resident) {
        resident.toString();
        LOGGER.info("Attempting to add a Resident.");
       try {

            List<Resident> residentList = this.getAllResidentsNoPrint();

            residentList.add(resident);

            try{
                LOGGER.info("Inserting Resident into database.");
                PreparedStatement ps = connectionService.getConnection().prepareStatement("INSERT INTO residents (firstname, lastname, ailment) VALUES (?,?,?);");
                ps.setString(1, resident.getFirstName());
                ps.setString(2, resident.getLastName());
                ps.setString(3, resident.getAilment());
                boolean didWork = ps.execute();
                LOGGER.info("Successfully Added a Resident.");
                return didWork;


            }catch (SQLException e){
                e.printStackTrace();
            }


            return true;

        } catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Resident. Please Check your inputs.");
            LOGGER.error("Error Adding Resident.");
            return false;
        }

    }

    @Override
    public boolean removeResident(int indexNum) {

        LOGGER.info("Attempting to remove a Resident.");
        try {

            List<Resident> residentList = this.getAllResidentsNoPrint();

            Resident resident = residentList.get(indexNum - 1);


                 LOGGER.info("Deleting Resident from the database.");
                //If both the Resident's First and Last Name equals to what was inputted the Resident is deleted.
                    PreparedStatement ps = connectionService.getConnection().prepareStatement("DELETE FROM residents as r WHERE r.firstname = ? AND r.lastname = ? AND ailment = ?;");
                    ps.setString(1, resident.getFirstName());
                    ps.setString(2, resident.getLastName());
                    ps.setString(3, resident.getAilment());
                    boolean didWork = ps.execute();
                    System.out.println("Deleted: " + resident.toString());

                    LOGGER.info("Successfully Removed a Resident.");
                    return didWork;




        } catch(Exception e){
            LOGGER.info("Error Removed a Resident.");
            System.out.println("No Resident exists with that name.");
            return false;
        }


    }

    @Override
    public boolean updateResident(int indexNum) {
        LOGGER.info("Attempting to Update a Resident.");
        List<Resident> residentList = this.getAllResidentsNoPrint();
        Resident resident = residentList.get(indexNum - 1);
        Scanner sc = new Scanner(System.in);

        String firstName;
        String lastName;
        String ailment = null;

        System.out.println("Please Enter Resident's first name: ");
        firstName = sc.next();


        System.out.println("Please Enter Resident's last name: ");

        lastName = sc.next();

        System.out.println("True or False? Is the Resident have a condition which needs Medication?");
        while (true) {
            try {
                boolean hasAilment = sc.nextBoolean();
                if(hasAilment){
                    LOGGER.info("Updated Resident has a new ailment.");
                    System.out.println("Please Enter Resident's ailment: ");
                    ailment = sc.next();

                }

                break;
            } catch (InputMismatchException e) {
                                /*If Someone inputs something other than True or False we will tell Scanner
                                and repeat the while loop until True or False is entered */
                System.out.println("Please enter: True or False.");
                sc.nextLine();
                continue;
            }
        }
try {
    LOGGER.info("Updating Resident from the database.");
    //If both the Resident's First and Last Name equals to what was inputted the Resident is deleted.
    PreparedStatement ps = connectionService.getConnection().prepareStatement("UPDATE residents as r SET firstname = ?, lastname = ?, ailment = ? WHERE r.firstname = ? AND r.lastname = ? AND r.ailment = ?;");
    ps.setString(1, firstName);
    ps.setString(2, lastName);
    ps.setString(3, ailment);
    ps.setString(4, resident.getFirstName());
    ps.setString(5, resident.getLastName());
    ps.setString(6, resident.getAilment());

    boolean didWork = ps.execute();
    System.out.println("UPDATED: " + resident.toString());
    return didWork;

}catch(SQLException e){
    LOGGER.info("Error Updating Resident from the database.");
}
        LOGGER.info("Error Updating Resident.");
        return false;
    }

    @Override
    public List<Resident> getAllResidentsWithMeds() {
        int indexNum = 0;
        LOGGER.info("Trying to get all Residents with Medication Needs.");
        //Instantiate a new ArrayLists of Residents
        List<Resident> residentList = new ArrayList<Resident>();

        //Instantiate a new ArrayLists of Residents
        List<Medication> medicationList = new ArrayList<Medication>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM residents_medication;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Resident
                Resident resident = new Resident(rs.getString("firstname"), rs.getString("lastname"), rs.getString("ailment"));
                //Gets the data from the specified columns and uses them as parameters to create a new Medication
                residentList.add(resident);
                if(rs.getString("medname") != null) {
                    Medication medication = new Medication(rs.getString("medname"), rs.getString("ailment"), rs.getInt("maximumdosage"));

                    medicationList.add(medication);
                }else if(rs.getString("ailment") == null) {

                }else{
                    System.out.println("[WARNING] There is no Medication in Stock to treat " +resident.getFirstName()+" "+ resident.getLastName()+"'s condition: ["+resident.getAilment()+"]");
                }
                //Adds the new resident to the residentList Array

            }

            System.out.println("===========================================================================================");

            for (Resident resident : residentList) {

                if(resident.getAilment() != null) {
                    System.out.println("[" + indexNum + "] " + resident.toString());
                }
                if(medicationList.get(indexNum) != null) {
                    System.out.println(medicationList.get(indexNum).toString());
                }else {
                    System.out.println("There is no Medication in Stock to treat this Resident's condition.");
                }

                System.out.println("-------------------------------------------------------------------------------------------");
                ++indexNum;
            }

            System.out.println("===========================================================================================");
            LOGGER.info("Successful in getting all Residents with Medication Needs.");
            return residentList;

        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }

    @Override
    public List<Resident> getAllResidentsWithNurses() {
        int indexNum = 0;
        LOGGER.info("Trying to get all Residents with their assigned Nurse.");
        //Instantiate a new ArrayLists of Residents
        List<Resident> residentList = new ArrayList<Resident>();

        //Instantiate a new ArrayLists of Residents
        List<Nurse> nurseList = new ArrayList<Nurse>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM residents_nurses;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Resident
                Resident resident = new Resident(rs.getString("firstname"), rs.getString("lastname"), rs.getString("ailment"), rs.getInt("nurseid"));
                //Gets the data from the specified columns and uses them as parameters to create a new Nurse
                residentList.add(resident);
                if(rs.getString("nurseid") != null) {
                    Nurse nurse = new Nurse(rs.getString("nurse_firstname"),rs.getString("nurse_lastname"), rs.getBoolean("iscert"), rs.getInt("assignments"), rs.getInt("nurseid"));

                    nurseList.add(nurse);
                }
                //Adds the new resident to the residentList Array

            }

            System.out.println("===========================================================================================");

            for (Resident resident : residentList) {

                System.out.println("["+indexNum+ "] " + resident.toString());

                if(nurseList.get(indexNum) != null) {
                    System.out.println("["+indexNum+ "] " + nurseList.get(indexNum).toStringWId());
                }else {
                    System.out.println("There is no Nurse assigned to this Resident.");
                }

                System.out.println("-------------------------------------------------------------------------------------------");
                ++indexNum;
            }

            LOGGER.info("Successful in getting all Residents with their Nurses.");
            return residentList;

        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }
}
