package com.patikadev.Helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    private static Connection connect =null;

    public static Connection getInstance () {
        try {
            connect = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASsWORD);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
            return connect;
    }
}
