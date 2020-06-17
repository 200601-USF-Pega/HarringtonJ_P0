package com.revature.menus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class LoginMenu implements IMenu {

public static final Logger LOGGER = LogManager.getLogger(LoginMenu.class.getName());

    @Override
    public void menuStart() {

        MenuFactory menuFactory = new MenuFactory();
        IMenu newMenu;

        Scanner sc = new Scanner(System.in);
        String userName;
        String password;

        String adminUserName = "admin";
        String adminPassword = "password";


            while (true) {
                try{
                //Options
                //Option UI
                System.out.println("|=====================================|");
                System.out.println("|  Press [1] : Admin Login.           |");
                System.out.println("|  Press [2] : Nurse Login            |");
                System.out.println("|  Press [0] : To exit the program.   |");
                System.out.println("|=====================================|");


                int check = 0;
                check = sc.nextInt();

                switch (check){

                    case 1:
                        System.out.println("Please Enter Administrator User Name");
                        userName = sc.next();

                        System.out.println("Please Enter Administrator Password");
                        password = sc.next();

                        if(userName.contentEquals(adminUserName)){

                            if(password.contentEquals(adminPassword)){

                                System.out.println("Successful Login...");
                                LOGGER.info("Administrator Login Successfully");
                                newMenu = new MainMenu();
                                newMenu.menuStart();

                            }else{
                                System.out.println("Password incorrect!");
                                continue;
                            }

                        }else{
                            System.out.println("UserName incorrect!");
                            continue;
                        }
                        break;


                    case 2:
                        newMenu = new MainMenu_Nurse();
                        newMenu.menuStart();
                        break;

                    case 0:
                        System.out.println("Exiting the Program...");
                        LOGGER.info("Exiting the Program.");
                        System.exit(1);

                    default:
                        System.out.println("Please Enter one of the above options");
                        continue;

                }

            }catch (Exception e) {
                    System.out.println("Please Enter one of the above options");
                    continue;
                }
            }








    }
}
