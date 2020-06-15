package com.revature.driver;

import com.revature.menus.IMenu;
import com.revature.menus.LoginMenu;
import com.revature.menus.MainMenu;
import com.revature.services.ConnectionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Driver {

    //Creates a Logger for the Driver Class
    private static final Logger LOGGER = LogManager.getLogger(Driver.class.getName());

    public static void main(String[] args) {

        ConnectionService connectionService = ConnectionService.getInstance();

        IMenu loginMenu = new LoginMenu();

        loginMenu.menuStart();


    }

}
