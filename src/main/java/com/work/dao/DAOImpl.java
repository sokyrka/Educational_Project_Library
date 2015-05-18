package com.work.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class DAOImpl  {
    private DataSource dataSource;

    public DAOImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void get(){
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
