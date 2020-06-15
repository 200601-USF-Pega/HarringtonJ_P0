package com.revature.dao;

import com.revature.models.*;
import com.revature.models.Medication;
import com.revature.models.Medication;
import com.revature.models.Medication;
import com.revature.services.ConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MedicationDAO_OnlineImpl implements MedicationDAO{

    ConnectionService connectionService = ConnectionService.getInstance();

    private static final Logger LOGGER = LogManager.getLogger(MedicationDAO_OnlineImpl.class.getName());


    @Override
    public List<Medication> getAllMedications() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Medications
        List<Medication> medicationList = new ArrayList<Medication>();
        LOGGER.info("Attempting to get a list of all Medications.");


        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM medications;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Medication
                Medication medication = new Medication(rs.getString("name"), rs.getString("ailment"), rs.getInt("lethaldosage"));
                //Adds the new medication to the medicationList Array
                medicationList.add(medication);
            }

            System.out.println("Currently are: " + medicationList.size() + " Medications.");



            for (Medication medication : medicationList) {

                System.out.println("["+indexNum+ "] " + medication.toString());
                ++indexNum;
            }
            LOGGER.info("Successfully returned a list of all Medications.");
            return medicationList;
        }catch(SQLException e){
            LOGGER.error("Error getting a list of all Medications.");
            e.printStackTrace();

        } catch (Exception e){
            LOGGER.error("Error Returning a list of all Medications.");

        }
        LOGGER.error("Returning a null list of all Medications.");

        return null;
    }


    private List<Medication> getAllMedicationsNoPrint() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Medications
        List<Medication> medicationList = new ArrayList<Medication>();

        try {
            LOGGER.info("Attempting to get a list of all Medications.");
            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM medications;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Medication
                Medication medication = new Medication(rs.getString("name"), rs.getString("ailment"), rs.getInt("lethaldosage"));
                //Adds the new medication to the medicationList Array
                medicationList.add(medication);
            }
            LOGGER.info("Successfully returned a list of all Medications.");

            return medicationList;
        }catch(SQLException e){
            LOGGER.error("Error getting a list of all Medications.");

            e.printStackTrace();

        } catch (Exception e){
            LOGGER.error("Error getting a list of all Medications.");

        }
        LOGGER.error("Returning a null list of all Medications.");

        return null;
    }




    @Override
    public boolean addNewMedication(Medication medication) {
        LOGGER.info("Attempting to add a Medication.");

        medication.toString();
        try {

            List<Medication> medicationList = this.getAllMedicationsNoPrint();

            medicationList.add(medication);

            try{
                LOGGER.info("Inserting Medication into database.");

                PreparedStatement ps = connectionService.getConnection().prepareStatement("INSERT INTO medications (name, ailment, lethaldosage) VALUES (?,?,?);");
                ps.setString(1, medication.getMedName());
                ps.setString(2, medication.getTreatedAilment());
                ps.setInt(3, medication.getLethalDosage());
                boolean didWork = ps.execute();
                LOGGER.info("Successfully Added a Medication.");

                return didWork;


            }catch (SQLException e){
                LOGGER.error("Error Adding Medication.");

                e.printStackTrace();
            }


            return true;

        } catch(Exception e){
            System.out.println(e.getStackTrace());
            LOGGER.error("Error Adding Medication.");

            System.out.println("Error Adding Medication. Please Check your inputs.");
            return false;
        }

    }
    

    @Override
    public boolean removeMedication(int indexNum) {
        LOGGER.info("Attempting to remove a Medication.");

        try {
            LOGGER.info("Deleting Medication from the database.");

            List<Medication> medicationList = this.getAllMedicationsNoPrint();
            Medication medication = medicationList.get(indexNum - 1);

            //If both the Medication's First and Last Name equals to what was inputted the Medication is deleted.
            PreparedStatement ps = connectionService.getConnection().prepareStatement("DELETE FROM medications as r WHERE r.name = ? AND r.ailment = ? AND r.lethaldosage = ?;");
            ps.setString(1, medication.getMedName());
            ps.setString(2, medication.getTreatedAilment());
            ps.setInt(3, medication.getLethalDosage());
            boolean didWork = ps.execute();
            System.out.println("Deleted: " + medication.toString());
            LOGGER.info("Successfully Removed a Medication.");

            return didWork;




        } catch(Exception e){
            LOGGER.info("Error Removed a Medication.");

            System.out.println("No Medication exists with that name.");
            return false;
        }



    }

    @Override
    public boolean updateMedication(int indexNum) {
        LOGGER.info("Attempting to Update a Medication.");

        List<Medication> medicationList = this.getAllMedicationsNoPrint();
        Medication medication = medicationList.get(indexNum - 1);
        Scanner sc = new Scanner(System.in);

        String name;
        int lethaldosage;
        String ailment = null;

        System.out.println("Please Enter Medication name: ");
        name = sc.next();


        System.out.println("Please Enter What ailment this medication treats: ");

        ailment = sc.next();

        System.out.println("Please Enter Medication's lethaldosage: ");
        lethaldosage = sc.nextInt();


        try {
            //If both the Medication's First and Last Name equals to what was inputted the Medication is deleted.
            PreparedStatement ps = connectionService.getConnection().prepareStatement("UPDATE medications as r SET name = ?, ailment = ?, lethaldosage = ? WHERE r.name = ? AND r.ailment = ? AND r.lethaldosage = ?;");
            ps.setString(1, name);
            ps.setString(2, ailment);
            ps.setInt(3, lethaldosage);
            ps.setString(4, medication.getMedName());
            ps.setString(5, medication.getTreatedAilment());
            ps.setInt(6, medication.getLethalDosage());

            boolean didWork = ps.execute();
            System.out.println("UPDATED: " + medication.toString());
            LOGGER.info("Successful Updating a Medication.");

            return didWork;

        }catch(SQLException e){
            LOGGER.info("Error Updating Medications from the database.");

        }
        LOGGER.info("Error Updating Medications from the database.");

        return false;

    }

    @Override
    public List<Medication> getListOfNeededMedication() {
        LOGGER.info("Attempting to get a List of ailments that need Medication to treat them.");

        int indexNum = 1;

        //Instantiate a new ArrayLists of Residents
        List<Resident> residentList = new ArrayList<Resident>();

        //Instantiate a new ArrayLists of Medications
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
                    //Medication medication = new Medication(rs.getString("medname"), rs.getString("ailment"), rs.getInt("maximumdosage"));

                    //medicationList.add(medication);
                }else if(rs.getString("ailment") == null) {

                }else{
                    System.out.println("[WARNING] There is no Medication in Stock to treat " +resident.getFirstName()+" "+ resident.getLastName()+"'s condition: ["+resident.getAilment()+"]");
                }
                //Adds the new resident to the residentList Array

            }

        }catch(SQLException e){
            LOGGER.error("Error with Attempting to get a list of needed Medication.");

            e.printStackTrace();

        } catch (Exception e){
            LOGGER.error("Error with Attempting to get a list of needed Medication.");

        }

        return null;
    }

}
