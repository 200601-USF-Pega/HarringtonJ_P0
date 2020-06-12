package com.revature.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {


    public ConnectionService(){
        Connection connection;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ruby.db.elephantsql.com:5432/yrngucii/", "yrngucii", "1FM_VybxeviYjdHIPgTGcB3nXwlndbh6" );
            System.out.println("Successful Connection to Database!");
        }catch (SQLException e){
            System.out.println("Could not connect to Database!");
            e.printStackTrace();
        }


    }




}
