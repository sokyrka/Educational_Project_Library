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

    private final DBPool dbPool = new DBPool();

    @Override
    public void addUser(String first_name, String second_name, String login, String password) {

        String sqlQuery = "INSERT INTO USER " +
                "(first_name, second_name, login, password)" +
                "VALUES" +
                "(?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, second_name);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.execute();
            dbPool.closeConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String login, String password){
        boolean result = false;

        String sqlQuery = "SELECT * FROM USER";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                if(resultSet.getString("login").equals(login) && resultSet.getString("password").equals(password)){
                    result = true;
                }
            }
            dbPool.closeConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> getAllFreeBook() {
        List<Book> tmpList = new ArrayList<Book>();

        String sqlQuery = "SELECT * FROM BOOK WHERE busy = FALSE";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                    Book book = new Book.Builder()
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .year(resultSet.getInt("year"))
                            .pages(resultSet.getInt("pages"))
                            .build();
                    tmpList.add(book);
            }
            dbPool.closeConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tmpList;
    }

    @Override
    public Book findBook(String title) {
        Book book = null;

        String sqlQuery = "SELECT * FROM BOOK WHERE title = ? AND busy = FALSE";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
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
            dbPool.closeConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean addRequest(String title, String login){
        boolean result = false;

        String sqlQuery = "INSERT INTO REQUEST (book_id, user_id) VALUES (" +
                "(SELECT book_id FROM BOOK WHERE title = ?), " +
                "(SELECT user_id FROM USER WHERE login = ?))";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, login);
            result = preparedStatement.execute();
            dbPool.closeConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> getUsersBook(String login){
        List<Book> tmpList = new ArrayList<Book>();

        String sqlQuery = "SELECT * FROM BOOK WHERE book_id IN(" +
                "SELECT book_id FROM REQUEST WHERE user_id = (" +
                "SELECT user_id FROM USER WHERE login = ?) " +
                "AND done = TRUE AND (home = TRUE OR library = TRUE)) ";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                    Book book = new Book.Builder()
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .year(resultSet.getInt("year"))
                            .pages(resultSet.getInt("pages"))
                            .build();
                    tmpList.add(book);
            }
            dbPool.closeConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tmpList;
    }

    @Override
    public boolean deleteUsersBook(String title, String login) {
        boolean result = false;

        String sqlQuery = "UPDATE REQUEST SET home = FALSE , library = FALSE " +
                "WHERE book_id = (SELECT book_id FROM BOOK WHERE title = ?) " +
                "AND user_id = (SELECT user_id FROM USER WHERE login = ?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, login);
            result = preparedStatement.execute();
            dbPool.closeConnection(connection);
            changeBookStatus(title);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void changeBookStatus(String title){

        String sqlQuery = "UPDATE BOOK SET busy = FALSE " +
                "WHERE title = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.execute();
            dbPool.closeConnection(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
