package com.revature.menus;

import com.revature.dao.ResidentDAO;
import com.revature.dao.ResidentDAO_OnlineImpl;
import com.revature.models.Resident;
import com.revature.services.ConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;



public class ResidentMenu implements IMenu {

    private static final Logger LOGGER = LogManager.getLogger(ResidentMenu.class.getName());


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
            LOGGER.info("Created new ResidentDAO_OnlineImpl object.");
            residentDAO = new ResidentDAO_OnlineImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Welcome Message
        System.out.println("This is the Resident Management Menu. Please Enter an option below");

        while (true) {
            //Options
            //Option UI
            System.out.println("|===================================================================|");
            System.out.println("|  Press [1] :  Get a List of all Residents                         |");
            System.out.println("|  Press [2] :  Add a Resident                                      |");
            System.out.println("|  Press [3] :  Remove a Resident                                   |");
            System.out.println("|  Press [4] :  Update a Resident                                   |");
            System.out.println("|  Press [5] :  Get a List of all Residents who need Medication     |");
            System.out.println("|  Press [6] :  To see Each assigned Resident for each Nurse.       |");
            System.out.println("|  Press [0] :  Return to Main Menu                                 |");
            System.out.println("|===================================================================|");

            //These Variables are for our Resident Objects
            String firstName;
            String lastName;
            String condition = null;

            //Switching Menus based on User Input
            int nextMenu = sc.nextInt();

            switch (nextMenu) {

                case 1:
                    LOGGER.info("Called to get a list of all Residents.");
                    residentDAO.getAllResidents();
                    break;

                case 2:
                    boolean hasCondition = false;
                    residentDAO.getAllResidents();

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
                        LOGGER.info("Added a new Resident that HAS a ailment.");
                        Resident newResident = new Resident(firstName, lastName, condition);
                        System.out.println(newResident.toString());
                        residentDAO.addResident(newResident);

                    }

                    if(hasCondition == false){
                        LOGGER.info("Added a new Resident that does NOT have an ailment.");
                        Resident newResident = new Resident(firstName, lastName, null);
                        System.out.println(newResident.toString());
                        residentDAO.addResident(newResident);

                }

                    break;

                case 3:
                    residentDAO.getAllResidents();
                    int indexNum = sc.nextInt();
                    residentDAO.removeResident(indexNum);

                    break;

                case 4:
                    residentDAO.getAllResidents();
                    System.out.println("Please Enter Resident's index number: ");
                    indexNum = sc.nextInt();

                    residentDAO.updateResident(indexNum);
                    break;


                case 5:
                    residentDAO.getAllResidentsWithMeds();
                    break;

                case 6:
                    residentDAO.getAllResidentsWithNurses();
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
