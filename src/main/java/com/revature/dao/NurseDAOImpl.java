package com.revature.dao;

import com.revature.models.Nurse;
//import com.sun.org.apache.xpath.internal.operations.Bool;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class NurseDAOImpl implements NurseDAO {

    List<Nurse> nurseList = new ArrayList<Nurse>();

    String filepath = "src/main/java/com/revature/resources/NurseData.txt";

/*
//Instantiating these will clear Nurse.txt No Idea why. Must ask a question soon.

FileWriter fileWriter = new FileWriter(filepath);
FileReader fileReader = new FileReader(filepath);
BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
BufferedReader bufferedReader = new BufferedReader(fileReader);*/

    public NurseDAOImpl() throws IOException {
    }

    @Override
    public List<Nurse> getAllNurses() {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Nurse> nurseList = (List<Nurse>)objectInputStream.readObject();
            System.out.println("Got Array List");
            objectInputStream.close();

            System.out.println("Currently are: " + nurseList.size() + " Nurses.");
            for (Nurse nurse: nurseList) {

                System.out.println(nurse.toString());
            }

            return nurseList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        }


        return null;
    }


    private List<Nurse> getAllNursesNoPrint() {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Nurse> nurseList = (List<Nurse>)objectInputStream.readObject();
            objectInputStream.close();

            return nurseList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        }


        return null;
    }




    @Override
    public boolean addNurse(Nurse nurse) {

        try {
            try {
                nurseList = this.getAllNursesNoPrint();
            }catch (Exception e){

            }

            nurseList.add(nurse);

            try{
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath));
                objectOutputStream.writeObject(nurseList);
                System.out.println("Added Nurse Successfully.");
                objectOutputStream.close();

            }catch (IOException e){
                System.out.println(e.getStackTrace());
            }






            return true;

        } catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Nurse. Please Check your inputs.");
            return false;
        }

    }

    @Override
    public boolean removeNurse(String firstName, String lastName) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Nurse> nurseList = (List<Nurse>)objectInputStream.readObject();
            objectInputStream.close();

            //ForEach Loop to iterate through the List of Nurses
            for (Nurse nurse: nurseList) {

                //If both the Nurse's First and Last Name equals to what was inputted the Nurse is deleted.
                if(nurse.getFirstname().contentEquals(firstName) && nurse.getLastname().contentEquals(lastName)){

                        nurseList.remove(nurse);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath));
                        objectOutputStream.writeObject(nurseList);
                        objectOutputStream.close();
                        return true;


                }


            }
            return false;

        } catch(Exception e){

            return false;
        }



    }


}
