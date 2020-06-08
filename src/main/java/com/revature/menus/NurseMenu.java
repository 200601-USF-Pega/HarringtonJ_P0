package com.revature.menus;

import com.revature.dao.NurseDAOImpl;
import com.revature.models.Nurse;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NurseMenu implements IMenu {

    @Override
    public void menuStart() {

        //These manage our menus and the ability to return to the main menu
        MenuFactory menuFactory = new MenuFactory();
        IMenu newMenu;

        //This Scanner manages and receives our user Input
        Scanner sc = new Scanner(System.in);

        //This creates the Nurse Database Access Object
        NurseDAOImpl nurseDAO = null;

        try {
            //We put this in a try catch block as this will be attempting to connect to an Online database
            nurseDAO = new NurseDAOImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Welcome Message
        System.out.println("This is the Nurse Management Menu. Please Enter an option below");

        while (true) {
            //Options
            //Option UI
            System.out.println("|=================================================|");
            System.out.println("|  Press [1] :  Get a List of all Nurses          |");
            System.out.println("|  Press [2] :  Add a Nurse                       |");
            System.out.println("|  Press [3] :  Remove a Nurse                    |");
            System.out.println("|  Press [0] :  Return to Main Menu               |");
            System.out.println("|=================================================|");

            //These Variables are for our Nurse Objects
            String firstName;
            String lastName;
            Boolean isMedCert;

            //Switching Menus based on User Input
            int nextMenu = sc.nextInt();

            switch (nextMenu) {

                case 1:
                    nurseDAO.getAllNurses();
                    break;

                case 2:
                    System.out.println("Please Enter Nurse's first name: ");
                    firstName = sc.next();


                    System.out.println("Please Enter Nurse's last name: ");
                    lastName = sc.next();

                    System.out.println("True or False? Is the Nurse Certified to handle Medication?");
                        while (true) {
                            try {
                                isMedCert = sc.nextBoolean();
                                break;
                            } catch (InputMismatchException e) {
                                /*If Someone inputs something other than True or False we will tell Scanner
                                and repeat the while loop until True or False is entered */
                                System.out.println("Please enter: True or False.");
                                sc.nextLine();
                                continue;
                            }
                        }
                    System.out.println(firstName + lastName + isMedCert);
                    Nurse nurse = new Nurse(firstName, lastName, isMedCert);

                    nurseDAO.addNurse(nurse);

                    break;

                case 3:
                    System.out.println("Please Enter Nurse's first name: ");
                    firstName = sc.next();


                    System.out.println("Please Enter Nurse's last name: ");
                    lastName = sc.next();

                    boolean b = nurseDAO.removeNurse(firstName, lastName);
                    if(b){
                        System.out.println("Removed Successfully: " + firstName + " " + lastName);
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
