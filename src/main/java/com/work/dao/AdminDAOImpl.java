package com.work.dao;

import com.work.common.Book;
import com.work.common.User;

import java.sql.*;

/**
 * Created by Eugine Sokirka on 21.05.2015.
 */
public class AdminDAOImpl implements AdminDAO {

    private final String url = "jdbc:mysql://176.37.217.24:3306/zhenya_test1";
    private final String username = "quattro";
    private final String pass = "Zhenya2015";
    private Connection connection;

    @Override
    public void addBook() {

    }

    @Override
    public Book getBook() {
        return null;
    }

    @Override
    public void updateBook() {

    }

    @Override
    public void deleteBook() {

    }

    @Override
    public User getUser() {
        String first_name = null;
        String second_name = null;
        String login = null;
        String password = null;
        try {
            String sqlQuery = "SELECT * FROM USER";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()){
                first_name = resultSet.getString("first_name");
                second_name = resultSet.getString("second_name");
                login = resultSet.getString("login");
                password = resultSet.getString("password");
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new User.Builder()
                .first_name(first_name)
                .second_name(second_name)
                .login(login)
                .password(password)
                .build();
    }

    @Override
    public void updateUser() {

    }

    @Override
    public boolean deleteUser(String first_name, String second_name) {
        boolean res = false;
        try {
            String sqlQuery = "DELETE FROM USER WHERE first_name = ? AND second_name = ?";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, second_name);
            res = preparedStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}
