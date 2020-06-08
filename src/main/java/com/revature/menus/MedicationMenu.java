package com.revature.menus;

import com.revature.dao.MedicationDAOImpl;
import com.revature.models.Medication;
import com.revature.models.Medication;

import java.io.IOException;
import java.util.Scanner;

public class MedicationMenu implements IMenu {

    @Override
    public void menuStart() {

        MenuFactory menuFactory = new MenuFactory();
        IMenu newMenu;

        //This Scanner manages and recieves our user Input
        Scanner sc = new Scanner(System.in);

        MedicationDAOImpl medicationDAO = null;

        try {
            medicationDAO = new MedicationDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //Welcome Message
        System.out.println("This is the Medication Management Menu. Please Enter an option below");

        while (true) {
            //Options
            //Option UI
            System.out.println("|=================================================|");
            System.out.println("|  Press [1] :  Get a List of all Medication      |");
            System.out.println("|  Press [2] :  Add Medication                    |");
            System.out.println("|  Press [3] :  Remove Medication                 |");
            System.out.println("|  Press [0] :  Return to Main Menu               |");
            System.out.println("|=================================================|");


            //Variables to create a new Medication
            String medName;
            String treatedAilment;
            int lethalDosage;

            //Switching Menus based on User Input
            int nextMenu = sc.nextInt();

            switch (nextMenu) {

                case 1:
                    medicationDAO.getAllMedications();
                    break;

                case 2:

                    System.out.println("Please Enter Medication name: ");
                    medName = sc.next();


                    System.out.println("What ailment does this Medication treat?");
                    treatedAilment = sc.next();

                    System.out.println("What is the lethal dosage for this Medication?");
                    lethalDosage = sc.nextInt();

                    Medication medication = new Medication(medName, treatedAilment, lethalDosage);

                    System.out.println(medication.toString());
                    medicationDAO.addNewMedication(medication);

                    break;

                case 3:

                    System.out.println("Please Enter Medication name: ");
                    medName = sc.next();


                    boolean b = medicationDAO.removeMedication(medName);

                    if (b){
                        System.out.println("Successfully Removed: " + medName);
                    }else{
                        System.out.println("There is no Medication with that name in stock.");
                    }

                    break;

                case 0:
                    newMenu = menuFactory.getMenu("MainMenu");
                    newMenu.menuStart();
                    break;

                default:
                    System.out.println("Please Enter one of the options");
                    continue;

            }
        }


    }
}
