package com.revature.dao;

import com.revature.models.*;
import com.revature.models.Medication;
import com.revature.models.Medication;
import com.revature.models.Medication;
import com.revature.services.ConnectionService;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MedicationDAO_OnlineImpl implements MedicationDAO{

    ConnectionService connectionService = ConnectionService.getInstance();


/*    Connection connection;*/

    //Setting Up Connection to our DataBase
    public MedicationDAO_OnlineImpl(){
/*
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ruby.db.elephantsql.com:5432/yrngucii/", "yrngucii", "1FM_VybxeviYjdHIPgTGcB3nXwlndbh6" );
            System.out.println("Successful Connection to Database!");
        }catch (SQLException e){
            System.out.println("Could not connect to Database!");
            e.printStackTrace();
        }*/

    }


    @Override
    public List<Medication> getAllMedications() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Medications
        List<Medication> medicationList = new ArrayList<Medication>();

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
            return medicationList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }


    private List<Medication> getAllMedicationsNoPrint() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Medications
        List<Medication> medicationList = new ArrayList<Medication>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM medications;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Medication
                Medication medication = new Medication(rs.getString("name"), rs.getString("ailment"), rs.getInt("lethaldosage"));
                //Adds the new medication to the medicationList Array
                medicationList.add(medication);
            }

            return medicationList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }




    @Override
    public boolean addNewMedication(Medication medication) {
        medication.toString();
        try {

            List<Medication> medicationList = this.getAllMedicationsNoPrint();

            medicationList.add(medication);

            try{

                PreparedStatement ps = connectionService.getConnection().prepareStatement("INSERT INTO medications (name, ailment, lethaldosage) VALUES (?,?,?);");
                ps.setString(1, medication.getMedName());
                ps.setString(2, medication.getTreatedAilment());
                ps.setInt(3, medication.getLethalDosage());
                boolean didWork = ps.execute();

                return didWork;


            }catch (SQLException e){
                e.printStackTrace();
            }


            return true;

        } catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Medication. Please Check your inputs.");
            return false;
        }

    }
    

    @Override
    public boolean removeMedication(int indexNum) {

        try {
            List<Medication> medicationList = this.getAllMedicationsNoPrint();
            Medication medication = medicationList.get(indexNum - 1);

            //If both the Medication's First and Last Name equals to what was inputted the Medication is deleted.
            PreparedStatement ps = connectionService.getConnection().prepareStatement("DELETE FROM medications as r WHERE r.name = ? AND r.ailment = ? AND r.lethaldosage = ?;");
            ps.setString(1, medication.getMedName());
            ps.setString(2, medication.getTreatedAilment());
            ps.setInt(3, medication.getLethalDosage());
            boolean didWork = ps.execute();
            System.out.println("Deleted: " + medication.toString());
            return didWork;




        } catch(Exception e){
            System.out.println("No Medication exists with that name.");
            return false;
        }



    }

    @Override
    public boolean updateMedication(int indexNum) {
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
            return didWork;

        }catch(SQLException e){

        }
        return false;

    }

    @Override
    public List<Medication> getListOfNeededMedication() {
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
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }

}
