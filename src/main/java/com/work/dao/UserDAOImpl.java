package com.work.dao;

import com.work.common.Book;
import com.work.common.User;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugine Sokirka on 19.05.2015.
 */
public class UserDAOImpl implements UserDAO{

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
    private final DBPool dbPool = new DBPool();

    @Override
    public void addUser(String first_name, String second_name, String login, String password) {

        logger.info("add user " + login + " to db");
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
            logger.error("addUser", e);
        }
    }

    @Override
    public boolean validateUser(String login, String password){
        boolean result = false;

        logger.info("validate user: " + login);

        String sqlQuery = "SELECT * FROM USER";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                if(resultSet.getString("login").equals(login) && resultSet.getString("password").equals(password)){
                    result = true;
                    logger.info("user is valid");
                }
            }
            dbPool.closeConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("validateUser", e);
        }
        return result;
    }

    @Override
    public List<Book> getAllFreeBook() {
        List<Book> tmpList = new ArrayList<Book>();

        logger.info("get all free books");

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
            logger.error("getAllFreeBook", e);
        }
        return tmpList;
    }

    @Override
    public Book findBook(String title) {
        Book book = null;

        logger.info("find book");

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
            logger.error("findBook", e);
        }
        return book;
    }

    @Override
    public boolean addRequest(String title, String login){
        boolean result = false;

        logger.info("add request");

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
            logger.error("addRequest", e);
        }
        return result;
    }

    @Override
    public List<Book> getUsersBook(String login){
        List<Book> tmpList = new ArrayList<Book>();

        logger.info("get users book");

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
            logger.error("getUsersBook", e);
        }
        return tmpList;
    }

    @Override
    public boolean deleteUsersBook(String title, String login) {
        boolean result = false;

        logger.info("delete users book");

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
            logger.error("deleteUsersBook", e);
        }
        return result;
    }

    @Override
    public void changeBookStatus(String title){

        logger.info("change book status");

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
            logger.error("changeBookStatus", e);
        }
    }
}
