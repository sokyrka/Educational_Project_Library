package com.work.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Eugine Sokirka on 22.05.2015.
 */
public class DBPool {

    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<Connection>(100);
    private Connection connection;

    public void createConnection(){
        try {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        Context context = new InitialContext(properties);
        Object obj = context.lookup("oracle/oracleDB");

        DataSource ds = (DataSource) obj;
        connection = ds.getConnection();

            if(pool.isEmpty()){
                pool.add(connection);
            }
        context.close();
        } catch (Exception e) {
        e.printStackTrace();
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
