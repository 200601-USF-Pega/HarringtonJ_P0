package com.revature.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionService {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionService.class.getName());
    private  static  ConnectionService connectionService_single_instance = null;
    private Connection connection;

    public ConnectionService(){

        try{

            connection = DriverManager.getConnection("jdbc:postgresql://ruby.db.elephantsql.com:5432/yrngucii/", "yrngucii", "1FM_VybxeviYjdHIPgTGcB3nXwlndbh6" );
            LOGGER.info("Successful Connection to Database!");
        }catch (SQLException e){
            LOGGER.fatal("Could not connect to Database!");
            e.printStackTrace();
        }


    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionService getInstance(){

        if( connectionService_single_instance == null) {
            LOGGER.info("Successful master Connection to Database created.");
            connectionService_single_instance = new ConnectionService();
        }

        return connectionService_single_instance;
    }


    @Override
    protected void finalize() throws Throwable {
        try{
            LOGGER.info("Connection to Database Closed.");
            connection.close();
        }catch (Exception e){

        }
        super.finalize();
    }
}
