package com.work.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Eugine Sokirka on 22.05.2015.
 */
public class DBPool {

    private static final String url = "jdbc:mysql://176.37.217.24:3306/zhenya_test1";
    private static final String username = "quattro";
    private static final String pass = "Zhenya2015";
    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<Connection>(100);

    public void createConnection(){
        if(pool.isEmpty()){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                pool.add(DriverManager.getConnection(url, username, pass));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        createConnection();
        Connection connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection){
        if(connection != null){
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
