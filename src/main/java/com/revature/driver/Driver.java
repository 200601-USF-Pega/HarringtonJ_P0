package com.revature.driver;

import com.revature.menus.IMenu;
import com.revature.menus.MainMenu;
import com.revature.services.ConnectionService;

public class Driver {



    public static void main(String[] args) {

        ConnectionService connectionService = ConnectionService.getInstance();

        IMenu mainMenu = new MainMenu();

        mainMenu.menuStart();


    }

}
