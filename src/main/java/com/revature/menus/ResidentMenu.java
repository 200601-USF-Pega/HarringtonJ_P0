package com.revature.menus;

import com.revature.dao.ResidentDAO;
import com.revature.dao.ResidentDAOImpl;
import com.revature.models.Resident;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ResidentMenu implements IMenu {

    @Override
    public void menuStart() {

        MenuFactory menuFactory = new MenuFactory();
        IMenu newMenu;

        //This Scanner manages and receives our user Input
        Scanner sc = new Scanner(System.in);

        //This creates the Resident Database Access Object
        ResidentDAO residentDAO = null;

        try {
            //We put this in a try catch block as this will be attempting to connect to an Online database
            residentDAO = new ResidentDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Welcome Message
        System.out.println("This is the Resident Management Menu. Please Enter an option below");

        while (true) {
            //Options
            //Option UI
            System.out.println("|=================================================|");
            System.out.println("|  Press [1] :  Get a List of all Residents       |");
            System.out.println("|  Press [2] :  Add a Resident                    |");
            System.out.println("|  Press [3] :  Remove a Resident                 |");
            System.out.println("|  Press [0] :  Return to Main Menu               |");
            System.out.println("|=================================================|");

            //These Variables are for our Resident Objects
            String firstName;
            String lastName;
            String condition = null;

            //Switching Menus based on User Input
            int nextMenu = sc.nextInt();

            switch (nextMenu) {

                case 1:
                    residentDAO.getAllResidents();
                    break;

                case 2:
                    boolean hasCondition = false;

                    System.out.println("Please Enter Resident's first name: ");
                    firstName = sc.next();


                    System.out.println("Please Enter Resident's last name: ");

                    lastName = sc.next();

                    System.out.println("True or False? Is the Resident have a condition which needs Medication?");
                    while (true) {
                        try {
                            hasCondition = sc.nextBoolean();
                            if(hasCondition){
                                System.out.println("Please Enter Resident's condition: ");
                                condition = sc.next();

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




                    if(hasCondition) {
                        Resident newResident = new Resident(firstName, lastName, condition);
                        System.out.println(newResident.toString());
                        residentDAO.addResident(newResident);
                    }

                    if(hasCondition == false){
                    Resident newResident = new Resident(firstName, lastName, null);
                        System.out.println(newResident.toString());
                    residentDAO.addResident(newResident);

                }

                    break;

                case 3:
                    System.out.println("Please Enter Resident's first name: ");
                    firstName = sc.next();


                    System.out.println("Please Enter Resident's last name: ");
                    lastName = sc.next();

                    boolean b = residentDAO.removeResident(firstName, lastName);
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
