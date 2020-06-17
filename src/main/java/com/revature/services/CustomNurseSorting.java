package com.revature.services;

import com.revature.dao.NurseDAO_OnlineImpl;
import com.revature.models.Nurse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class CustomNurseSorting {



    public static void main(String[] args) {

        Properties properties = new Properties();

        try(OutputStream output = new FileOutputStream("src/main/java/com/revature/resources/config.properties")) {

            properties.setProperty("db.username","admin");
            properties.setProperty("db.password", "password");
            properties.setProperty("username","admin");
            properties.setProperty("password", "password");

            properties.store(output, null);

            System.out.println(properties);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





//        NurseDAO_OnlineImpl nurseDAO_online = new NurseDAO_OnlineImpl();
//
//        List<Nurse> nurseList = nurseDAO_online.getAllNursesNoPrint();
//
//Collections.sort(nurseList);
//
//        for (Nurse nurse: nurseList
//             ) {
//            System.out.println(nurse.toString());
//        }



    }



}
