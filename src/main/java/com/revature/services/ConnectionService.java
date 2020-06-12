package com.revature.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
    private  static  ConnectionService connectionService_single_instance = null;
    private Connection connection;

    public ConnectionService(){

        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ruby.db.elephantsql.com:5432/yrngucii/", "yrngucii", "1FM_VybxeviYjdHIPgTGcB3nXwlndbh6" );
            System.out.println("Successful Connection to Database!");
        }catch (SQLException e){
            System.out.println("Could not connect to Database!");
            e.printStackTrace();
        }


    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionService getInstance(){

        if( connectionService_single_instance == null) {
            System.out.println("Connection Established");
            connectionService_single_instance = new ConnectionService();
        }

        return connectionService_single_instance;
    }


    @Override
    protected void finalize() throws Throwable {
        try{
            connection.close();
        }catch (Exception e){

        }
        super.finalize();
    }
}
