package com.work.dao;

import com.work.common.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class DAOImpl  {
    private final String url = "jdbc:mysql://176.37.217.24:3306/zhenya_test1";
    private final String user = "quattro";
    private final String password = "Zhenya2015";

    @Autowired
    private DataSource dataSource;
    Connection connection = null;

    public DAOImpl() {
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT busy FROM BOOKS");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    /*public DAOImpl(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT busy FROM BOOKS");
            while(rs.next()){
                //String name = rs.getString("first_name");
                Boolean busy = rs.getBoolean("busy");
                //String author = rs.getString("AUTHOR");

                System.out.print(busy);
                //System.out.println(author);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    }
}
