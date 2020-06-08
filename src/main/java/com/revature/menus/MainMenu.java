package com.revature.menus;

import java.util.Scanner;

public class MainMenu implements IMenu {



    //We are overriding the IMenu's method tag
    @Override
    public void menuStart() {


        MenuFactory menuFactory = new MenuFactory();
        IMenu newMenu;

        //This Scanner manages and recieves our user Input
        Scanner sc = new Scanner(System.in);

        //Welcome Message
        System.out.println("Welcome Manager! Please Enter an option below");

        while (true) {
        //Options
            //Option UI
        System.out.println("|=====================================|");
        System.out.println("|  Press [1] : To manage Nurses.      |");
        System.out.println("|  Press [2] : To manage Residents    |");
        System.out.println("|  Press [3] : To manage Medications. |");
        System.out.println("|  Press [0] : To exit the program.   |");
        System.out.println("|=====================================|");




        //Switching Menus based on User Input
        int nextMenu = sc.nextInt();

        switch (nextMenu) {

            case 1:
                newMenu = menuFactory.getMenu("Nurses");
                newMenu.menuStart();
                break;

            case 2:
                newMenu = menuFactory.getMenu("Residents");
                newMenu.menuStart();
                break;

            case 3:
                newMenu = menuFactory.getMenu("Medications");
                newMenu.menuStart();
                break;

            case 0:
                System.out.println("Exiting program...");
                System.exit(0);
                break;

            default:
                System.out.println("Please Enter one of the options");
                continue;

            }
        }



    }


}
