package com.revature.dao;

import com.revature.models.Medication;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MedicationDAOImpl implements MedicationDAO{

    List<Medication> medicationList = new ArrayList<Medication>();

    String filepath = "src/main/java/com/revature/resources/MedicationData.txt";


    @Override
    public List<Medication> getAllMedications() {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Medication> medicationList = (List<Medication>)objectInputStream.readObject();
            System.out.println("Got Array List");
            objectInputStream.close();

            System.out.println("Currently are: " + medicationList.size() + " Medications.");
            for (Medication medication: medicationList) {

                System.out.println(medication.toString());
            }

            return medicationList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        }


        return null;
    }


    private List<Medication> getAllMedicationsNoPrint() {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Medication> medicationList = (List<Medication>)objectInputStream.readObject();
            objectInputStream.close();

            return medicationList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        } catch (Exception e){

        }


        return null;
    }




    @Override
    public boolean addNewMedication(Medication medication) {

        medication.toString();

        try {
            try {
                medicationList = this.getAllMedicationsNoPrint();
            }catch (Exception e){

            }

            medicationList.add(medication);

            try{
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath));
                objectOutputStream.writeObject(medicationList);
                System.out.println("Added Medication Successfully.");
                objectOutputStream.close();

            }catch (IOException e){
                System.out.println(e.getStackTrace());
            }






            return true;

        } catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Medication. Please Check your inputs.");
            return false;
        }

    }

    @Override
    public boolean removeMedication(int indexNum) {
        return false;
    }

    @Override
    public boolean updateMedication(int indexNum) {
        return false;
    }

    @Override
    public List<Medication> getListOfNeededMedication() {
        return null;
    }


    public boolean removeMedication(String medicationName) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Medication> medicationList = (List<Medication>)objectInputStream.readObject();
            objectInputStream.close();

            //ForEach Loop to iterate through the List of Medications
            for (Medication listmedication: medicationList) {

                //If both the Medication's First and Last Name equals to what was inputted the Medication is deleted.
                if(listmedication.getMedName().contentEquals(medicationName)){

                    medicationList.remove(listmedication);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath));
                    objectOutputStream.writeObject(medicationList);
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
