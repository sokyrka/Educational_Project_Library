package com.work.dao;

import com.work.common.Book;
import com.work.common.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugine Sokirka on 19.05.2015.
 */
public class UserDAOImpl implements UserDAO{

    private final String url = "jdbc:mysql://176.37.217.24:3306/zhenya_test1";
    private final String username = "quattro";
    private final String pass = "Zhenya2015";
    private Connection connection;

    @Override
    public void addUser(String first_name, String second_name, String login, String password) {
        User user = new User.Builder()
                .first_name(first_name)
                .second_name(second_name)
                .login(login)
                .password(password)
                .build();
        try {
            String sqlQuery = "INSERT INTO USER " +
                    "(first_name, second_name, login, password)" +
                    "VALUES" +
                    "(?, ?, ?, ?)";

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getSecond_name());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String login, String password){
        boolean result = false;
        try {
            String sqlQuery = "SELECT * FROM USER";

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                if(resultSet.getString("login").equals(login) && resultSet.getString("password").equals(password)){
                    result = true;
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> getAllFreeBook() {
        List<Book> tmpList = new ArrayList<Book>();
        try {
            String sqlQuery = "SELECT * FROM BOOK";

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()){
                if(!resultSet.getBoolean("busy")){
                    Book book = new Book.Builder()
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .year(resultSet.getInt("year"))
                            .pages(resultSet.getInt("pages"))
                            .build();
                    tmpList.add(book);
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tmpList;
    }

    @Override
    public Book findBook(String title) {
        Book book = null;
        try {
            String sqlQuery = "SELECT * FROM BOOK WHERE title = ?";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                book = new Book.Builder()
                        .title(resultSet.getString("title"))
                        .author(resultSet.getString("author"))
                        .year(resultSet.getInt("year"))
                        .pages(resultSet.getInt("pages"))
                        .build();
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean addRequest(String title, String login){
        boolean result = false;
        try {
            String sqlQuery = "INSERT INTO REQUEST (book_id, user_id) VALUES (" +
                    "(SELECT book_id FROM BOOK WHERE title = ?), " +
                    "(SELECT user_id FROM USER WHERE login = ?))";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, login);
            result = preparedStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
