package com.work.dao;

import com.work.common.Book;

import java.sql.*;
import java.util.List;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class DAOImpl implements DAO {
    private Connection connection = null;
    private final String url = "jdbc:oracle:thin:@//192.168.3.90:1521/SOA11G";
    private final String user = "soatest";
    private final String password = "soatest";

    public DAOImpl(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT NAME FROM SOATEST.BOOKS");
            while(rs.next()){
                String name = rs.getString("NAME");
                //String author = rs.getString("AUTHOR");

                System.out.print(name);
                //System.out.println(author);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public Book update() {
        return null;
    }
}
