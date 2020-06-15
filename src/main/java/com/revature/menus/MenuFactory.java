package com.revature.menus;


public class MenuFactory {


    public IMenu getMenu(String menuName) {
        IMenu currentMenu;
        switch (menuName) {

            case "MainMenu":
                return new MainMenu();

            case "Nurses":
                return new NurseMenu();

            case "Residents":
                return new ResidentMenu();

            case "Medications":
                return new MedicationMenu();

            case "MainMenu_Nurse":
                return  new MainMenu_Nurse();


        }


        return null;
    }


}
