package com.work.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class DAOImpl  {
    private final String url = "jdbc:mysql://176.37.217.24:3306/zhenya_test1";
    private final String user = "quattro";
    private final String pass = "Zhenya2015";
    private Connection connection = null;

    public DAOImpl(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
