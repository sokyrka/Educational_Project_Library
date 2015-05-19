package com.work.dao;

import com.work.common.User;

import java.sql.*;

/**
 * Created by Eugine Sokirka on 19.05.2015.
 */
public class UserDAOImpl implements UserDAO{

    private final String url = "jdbc:mysql://176.37.217.24:3306/zhenya_test1";
    private final String username = "quattro";
    private final String pass = "Zhenya2015";
    private Connection connection;

    @Override
    public void addUser(String first_name, String second_name, String login, String password, int book_id) {
        User user = new User.Builder()
                .first_name(first_name)
                .second_name(second_name)
                .login(login)
                .password(password)
                .book_id(book_id)
                .build();
        try {
            String sqlQuery = "INSERT INTO USERS " +
                    "(first_name, second_name, login, password, book_id)" +
                    "VALUES" +
                    "(?, ?, ?, ?, ?" +
                    ")";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pass);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getSecond_name());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getBook_id());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser() {
        String first_name = null;
        String second_name = null;
        String login = null;
        String password = null;
        int book_id = 0;
        try {
            String sqlQuery = "SELECT * FROM USERS";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()){
                first_name = resultSet.getString("first_name");
                second_name = resultSet.getString("second_name");
                login = resultSet.getString("login");
                password = resultSet.getString("password");
                book_id = resultSet.getInt("book_id");
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
                .book_id(book_id)
                .build();
    }

    @Override
    public void updateUser() {

    }

    @Override
    public boolean deleteUser(String first_name, String second_name) {
        boolean res = false;
        try {
            String sqlQuery = "DELETE FROM USERS WHERE first_name = ? AND second_name = ?";
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

    @Override
    public boolean validateUser(String login, String password){
        boolean res = false;
        try {
            String sqlQuery = "SELECT * FROM USERS";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                if(resultSet.getString("login").equals(login) && resultSet.getString("password").equals(password)){
                    res = true;
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}
