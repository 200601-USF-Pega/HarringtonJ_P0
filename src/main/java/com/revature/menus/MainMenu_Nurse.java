package com.revature.menus;


import com.revature.dao.MedicationDAO_OnlineImpl;
import com.revature.dao.NurseDAO_OnlineImpl;
import com.revature.dao.ResidentDAO;
import com.revature.dao.ResidentDAO_OnlineImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Scanner;


public class MainMenu_Nurse implements IMenu {

    public static final Logger LOGGER = (Logger) LogManager.getLogger(MainMenu_Nurse.class.getName());


    //We are overriding the IMenu's method tag
    @Override
    public void menuStart() {


        MenuFactory menuFactory = new MenuFactory();
        IMenu newMenu;

        //This Scanner manages and recieves our user Input
        Scanner sc = new Scanner(System.in);

        //Making a MedicationDAO
        MedicationDAO_OnlineImpl medicationDAO = null;

        try {
            LOGGER.info("Created new MedicationDAO_OnlineImpl object.");
            medicationDAO = new MedicationDAO_OnlineImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //This creates the Nurse Database Access Object
        NurseDAO_OnlineImpl nurseDAO = null;

        try {
            //We put this in a try catch block as this will be attempting to connect to an Online database
            LOGGER.info("Created new NurseDAO_OnlineImpl object.");
            nurseDAO = new NurseDAO_OnlineImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //This creates the Resident Database Access Object
        ResidentDAO residentDAO = null;

        try {
            //We put this in a try catch block as this will be attempting to connect to an Online database
            LOGGER.info("Created new ResidentDAO_OnlineImpl object.");
            residentDAO = new ResidentDAO_OnlineImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }




        //Welcome Message
        System.out.println("Welcome Nurse! Please Enter an option below");

        while (true) {
        //Options
            //Option UI
        System.out.println("|=================================================================|");
        System.out.println("|  Press [1] : To see a list of Nurses                            |");
        System.out.println("|  Press [2] : To see Each assigned Resident for each Nurse.      |");
        System.out.println("|  Press [3] : Check What Medication is in stock                  |");
        System.out.println("|  Press [0] : To go back to the login menu                       |");
        System.out.println("|=================================================================|");




        //Switching Menus based on User Input
        int nextMenu = sc.nextInt();

        switch (nextMenu) {

            case 1:
                nurseDAO.getAllNurses();
                break;

            case 2:
                residentDAO.getAllResidentsWithNurses();
                break;

            case 3:
                medicationDAO.getAllMedications();
                break;

            case 0:
                newMenu = new LoginMenu();
                newMenu.menuStart();
                break;

            default:

                System.out.println("Please Enter one of the options");
                continue;

            }
        }



    }


}
