package com.revature.services;

import com.revature.dao.NurseDAO_OnlineImpl;
import com.revature.models.Nurse;

import java.util.Collections;

import java.util.Comparator;
import java.util.List;

public class CustomNurseSorting {

    public static void main(String[] args) {

        NurseDAO_OnlineImpl nurseDAO_online = new NurseDAO_OnlineImpl();

        List<Nurse> nurseList = nurseDAO_online.getAllNursesNoPrint();

Collections.sort(nurseList);

        for (Nurse nurse: nurseList
             ) {
            System.out.println(nurse.toString());
        }



    }



}
