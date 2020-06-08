package com.revature.dao;


import com.revature.models.Resident;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ResidentDAOImpl implements ResidentDAO{

   // List<Resident> residentList = new ArrayList<Resident>();

    String filepath = "src/main/java/com/revature/resources/ResidentData.txt";


    @Override
    public List<Resident> getAllResidents() {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Resident> residentList = (List<Resident>)objectInputStream.readObject();
            System.out.println("Got Array List");
            objectInputStream.close();

            System.out.println("Currently are: " + residentList.size() + " Residents.");
            for (Resident resident: residentList) {

                System.out.println(resident.toString());
            }

            return residentList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        }
              

        return null;
    }


    private List<Resident> getAllResidentsNoPrint() {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Resident> residentList = (List<Resident>)objectInputStream.readObject();
            objectInputStream.close();

            return residentList;

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("IOException");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        } catch (Exception e){

        }


        return null;
    }
    
    
    
    
    @Override
    public boolean addResident(Resident resident) {
        resident.toString();
        try {

                List<Resident> residentList = this.getAllResidentsNoPrint();


            residentList.add(resident);

            try{
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath));
                objectOutputStream.writeObject(residentList);
                System.out.println("Added Resident Successfully.");
                objectOutputStream.close();

            }catch (IOException e){
                System.out.println(e.getStackTrace());
            }






            return true;

        } catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Resident. Please Check your inputs.");
            return false;
        }
        

    }

    @Override
    public boolean removeResident(String firstName, String lastName) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filepath)));
            List<Resident> residentList = (List<Resident>)objectInputStream.readObject();
            objectInputStream.close();

            //ForEach Loop to iterate through the List of Residents
            for (Resident listresident: residentList) {

                //If both the Resident's First and Last Name equals to what was inputted the Resident is deleted.
                if(listresident.getFirstName().contentEquals(firstName) && listresident.getLastName().contentEquals(lastName)){

                    residentList.remove(listresident);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath));
                    objectOutputStream.writeObject(residentList);
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
