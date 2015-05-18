package com.work.dao;

import com.work.common.Book;

import java.sql.*;
import java.util.List;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class DAOImpl implements DAO {
    private Connection connection = null;
    private final String url = "jdbc:mysql://176.37.217.24:3306/zhenya_test1";
    private final String user = "quattro";
    private final String password = "Zhenya2015";

    public DAOImpl(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT first_name FROM USERS");
            while(rs.next()){
                String name = rs.getString("first_name");
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
