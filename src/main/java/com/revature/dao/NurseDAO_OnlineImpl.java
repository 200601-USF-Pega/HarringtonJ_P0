package com.revature.dao;

import com.revature.models.Nurse;
import com.revature.models.Resident;
import com.revature.services.ConnectionService;


import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class NurseDAO_OnlineImpl implements NurseDAO {

    ConnectionService connectionService = ConnectionService.getInstance();

    //Setting Up Connection to our DataBase
    public NurseDAO_OnlineImpl(){

    }

    @Override
    public List<Nurse> getAllNurses() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Nurses
        List<Nurse> nurseList = new ArrayList<Nurse>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM nurses;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Nurse
                Nurse nurse = new Nurse(rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("iscert"), rs.getInt("assignments"));
                //Adds the new nurse to the nurseList Array
                nurseList.add(nurse);
            }

            System.out.println("Currently are: " + nurseList.size() + " Nurses.");



            for (Nurse nurse : nurseList) {

                System.out.println("["+indexNum+ "] " + nurse.toString());
                ++indexNum;
            }
            return nurseList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }


    public List<Nurse> getAllNursesNoPrint() {

        //Instantiate a new ArrayLists of Nurses
        List<Nurse> nurseList = new ArrayList<Nurse>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM nurses;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Nurse
                Nurse nurse = new Nurse(rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("iscert"), rs.getInt("assignments"));
                //Adds the new nurse to the nurseList Array
                nurseList.add(nurse);
            }
            
            return nurseList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }

    
    
    
    
    @Override
    public boolean addNurse(Nurse nurse) {

        nurse.toString();
        try {

            List<Nurse> nurseList = this.getAllNursesNoPrint();

            nurseList.add(nurse);

            try{

                PreparedStatement ps = connectionService.getConnection().prepareStatement("INSERT INTO nurses (firstname, lastname, iscert, assignments) VALUES (?,?,?,?);");
                ps.setString(1, nurse.getFirstname());
                ps.setString(2, nurse.getLastname());
                ps.setBoolean(3, nurse.getMedCert());
                ps.setInt(4, nurse.getAssignments());
                boolean didWork = ps.execute();

                return didWork;


            }catch (SQLException e){
                e.printStackTrace();
            }


            return true;

        } catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Nurse. Please Check your inputs.");
            return false;
        }

    }

    @Override
    public boolean removeNurse(int indexNum) {

        try {
            List<Nurse> nurseList = this.getAllNursesNoPrint();
            Nurse nurse = nurseList.get(indexNum - 1);

            //If both the Nurse's First and Last Name equals to what was inputted the Nurse is deleted.
            PreparedStatement ps = connectionService.getConnection().prepareStatement("DELETE FROM nurses as r WHERE r.firstname = ? AND r.lastname = ? AND iscert = ? AND assignments = ?;");
            ps.setString(1, nurse.getFirstname());
            ps.setString(2, nurse.getLastname());
            ps.setBoolean(3, nurse.getMedCert());
            ps.setInt(4, nurse.getAssignments());
            boolean didWork = ps.execute();
            System.out.println("Deleted: " + nurse.toString());
            return didWork;




        } catch(Exception e){
            System.out.println("No Nurse exists with that name.");
            return false;
        }



    }

    @Override
    public boolean updateNurse(int indexNum) {
        List<Nurse> nurseList = this.getAllNursesNoPrint();
        Nurse nurse = nurseList.get(indexNum - 1);
        Scanner sc = new Scanner(System.in);

        String firstName;
        String lastName;
        boolean iscert;
        int assignments;

        System.out.println("Please Enter Nurse's first name: ");
        firstName = sc.next();


        System.out.println("Please Enter Nurse's last name: ");

        lastName = sc.next();

        System.out.println("True or False? Is the Nurse Certified to handle Medication?");
        while (true) {
            try {
                iscert = sc.nextBoolean();

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

            //If both the Nurse's First and Last Name equals to what was inputted the Nurse is deleted.
            PreparedStatement ps = connectionService.getConnection().prepareStatement("UPDATE nurses as r SET firstname = ?, lastname = ?, iscert = ? WHERE r.firstname = ? AND r.lastname = ? AND r.iscert = ?;");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setBoolean(3, iscert);
            ps.setString(4, nurse.getFirstname());
            ps.setString(5, nurse.getLastname());
            ps.setBoolean(6, nurse.getMedCert());

            boolean didWork = ps.execute();
            System.out.println("UPDATED: " + nurse.toString());
            return didWork;

        }catch(SQLException e){

        }
        return false;    }

    @Override
    public boolean addNurseToResident() {

        ResidentDAO residentDAO = new ResidentDAO_OnlineImpl();
        List<Resident> residentList = residentDAO.getAllResidentsNoPrint();
        List<Nurse> nurseList = this.getAllNursesNoPrint();

        int nurseIndex = 0;
        int assignments = 0;
        int noCertassignments = 0;

        try{
            Statement mps = connectionService.getConnection().createStatement();
            mps.executeUpdate("UPDATE nurses SET assignments = 0");
            mps.executeUpdate("UPDATE residents SET nurseid = 0;");



        }catch (SQLException e) {
            e.printStackTrace();
        }


        for(Resident resident : residentList){

            if(resident.getAilment() != null){

                for(Nurse nurse : nurseList) {


                    if (assignments > nurse.getAssignments() || assignments == 0) {
                        if (nurse.getMedCert() == true) {
                            assignments = assignments + 1;
                            try {
                                PreparedStatement ps = connectionService.getConnection().prepareStatement("UPDATE nurses SET assignments = ? WHERE firstname = ? AND lastname = ? AND iscert = ?;");
                                ps.setInt(1, (nurse.getAssignments() + 1));
                                ps.setString(2, nurse.getFirstname());
                                ps.setString(3, nurse.getLastname());
                                ps.setBoolean(4, nurse.getMedCert());

                                nurse.setAssignments((nurse.getAssignments() + 1));
                                ps.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                PreparedStatement nps = connectionService.getConnection().prepareStatement("SELECT nurses.id FROM nurses WHERE firstname = ? AND lastname = ? AND iscert = ?;");
                                nps.setString(1, nurse.getFirstname());
                                nps.setString(2, nurse.getLastname());
                                nps.setBoolean(3, nurse.getMedCert());

                                ResultSet nrs = nps.executeQuery();


                                while (nrs.next()) {
                                    nurseIndex = nrs.getInt("id");
                                    System.out.println("Nurse id: " + nurseIndex);
                                }


                                PreparedStatement ps = connectionService.getConnection().prepareStatement("UPDATE residents SET nurseid = ? WHERE firstname = ? AND lastname = ? AND ailment = ?;");
                                ps.setInt(1, nurseIndex);
                                ps.setString(2, resident.getFirstName());
                                ps.setString(3, resident.getLastName());
                                ps.setString(4, resident.getAilment());
                                ps.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            resident.setNurseid(nurseIndex);
                            System.out.println(nurse.toString() + " Assigned to: " + resident.toString());
                            break;
                        }
                    }
                }

            }else{

                for(Nurse nurse : nurseList){



                    if(noCertassignments > nurse.getAssignments()  || noCertassignments == 0) {
                        if (nurse.getMedCert() == false) {
                            noCertassignments = noCertassignments + 1;

                            try {
                                PreparedStatement ps = connectionService.getConnection().prepareStatement("UPDATE nurses SET assignments = ? WHERE firstname = ? AND lastname = ? AND iscert = ?;");
                                ps.setInt(1, (nurse.getAssignments() + 1));
                                ps.setString(2, nurse.getFirstname());
                                ps.setString(3, nurse.getLastname());
                                ps.setBoolean(4, nurse.getMedCert());
                                nurse.setAssignments((nurse.getAssignments() + 1));
                                ps.executeUpdate();


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                PreparedStatement nps = connectionService.getConnection().prepareStatement("SELECT nurses.id FROM nurses WHERE firstname = ? AND lastname = ? AND iscert = ?;");
                                nps.setString(1, nurse.getFirstname());
                                nps.setString(2, nurse.getLastname());
                                nps.setBoolean(3, nurse.getMedCert());


                                ResultSet nrs = nps.executeQuery();

                                while (nrs.next()) {
                                    nurseIndex = nrs.getInt("id");
                                    System.out.println("Nurse id: " + nurseIndex);
                                }


                                PreparedStatement ps = connectionService.getConnection().prepareStatement("UPDATE residents SET nurseid = ? WHERE firstname = ? AND lastname = ?");
                                ps.setInt(1, nurseIndex);
                                ps.setString(2, resident.getFirstName());
                                ps.setString(3, resident.getLastName());
                                ps.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            resident.setNurseid(nurseIndex);
                            System.out.println(nurse.toString() + " Assigned to: " + resident.toString());
                            break;
                        }
                    }
                }
            }

        }


        return false;
    }


}
