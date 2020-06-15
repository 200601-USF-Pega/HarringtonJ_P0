package com.revature.menus;


import com.revature.models.Medication;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class MedicationMenuTest extends TestCase {

    @Before public void initialize() {

    }



    @Test
    public void testmenuStart() {



        String medName;
        String treatedAilment;
        int lethalDosage;
        int indexNum;


        //MenuFactory menuFactory = new MenuFactory();
        //IMenu newMenu;

        //This Scanner manages and recieves our user Input
        Scanner sc = new Scanner(System.in);

//        MedicationDAO_OnlineImpl medicationDAO = null;
//
//        try {
//            medicationDAO = new MedicationDAO_OnlineImpl();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //Welcome Message
        System.out.println("This is the Medication Management Menu. Please Enter an option below");

        while (true) {
            //Options
            //Option UI
            System.out.println("|===================================================================|");
            System.out.println("|  Press [1] :  Get a List of all Medications                       |");
            System.out.println("|  Press [2] :  Add a Medication                                    |");
            System.out.println("|  Press [3] :  Remove a Medication                                 |");
            System.out.println("|  Press [4] :  Update a Medication                                 |");
            System.out.println("|  Press [5] :  Get a List of all Needed Medication                 |");
            System.out.println("|  Press [0] :  Return to Main Menu                                 |");
            System.out.println("|===================================================================|");

            //Variables to create a new Medication

            //Switching Menus based on User Input
            int nextMenu = sc.nextInt();

            switch (nextMenu) {

                case 1:
//                    medicationDAO.getAllMedications();
                    System.out.println("We call medicationDAO's getAllMedication() method!");

                    break;

                case 2:

                    System.out.println("Please Enter Medication name: ");
                    medName = sc.next();
                    assertFalse(medName.isEmpty());

                    System.out.println("What ailment does this Medication treat?");
                    treatedAilment = sc.next();

                    System.out.println("What is the lethal dosage for this Medication?");
                    lethalDosage = sc.nextInt();

                    Medication medication = new Medication(medName, treatedAilment, lethalDosage);

                    System.out.println(medication.toString());

//                    medicationDAO.addNewMedication(medication);
                    System.out.println("We call medicationDAO's addNewMedication() method!");
                    break;

                case 3:
//                    medicationDAO.getAllMedications();
                    System.out.println("We call medicationDAO's getAllMedication() method!");

                    System.out.println("Please Enter Medication's index number: ");
                    indexNum = sc.nextInt();


                    System.out.println("Please Enter Medication name: ");
                    medName = sc.next();
//
//
//                    boolean b = medicationDAO.removeMedication(medName);
//
//                    if (b){
//                        System.out.println("Successfully Removed: " + medName);
//                    }else{
//                        System.out.println("There is no Medication with that name in stock.");
//                    }

//                    medicationDAO.removeMedication(indexNum);
                    System.out.println("We call medicationDAO's removeMedication(indexNum) method!");
                    break;

                case 4:
//                    medicationDAO.getAllMedications();
                    System.out.println("We call medicationDAO's getAllMedication() method!");

                    System.out.println("Please Enter Medication's index number: ");
                    indexNum = sc.nextInt();

//                    medicationDAO.updateMedication(indexNum);
                    System.out.println("We call medicationDAO's updateMedication(indexNum) method!");


                    break;


                case 5:
//                    medicationDAO.getListOfNeededMedication();
                    System.out.println("We call medicationDAO's getListOfNeededMedication() method!");


                    break;


                case 0:
                    //newMenu = menuFactory.getMenu("MainMenu");
                    //newMenu.menuStart();
                    System.out.println("We instantiate a newMenu as a = menuFactory.getMenu('MainMenu')");
                    System.out.println("We call the newMenu's menuStart() method");

                    break;

                default:
                    System.out.println("Please Enter one of the options");
                    continue;

            }
        }


    }





}