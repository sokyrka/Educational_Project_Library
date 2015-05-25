package com.work.dao;

import com.work.common.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugine Sokirka on 21.05.2015.
 */
public class AdminDAOImpl implements AdminDAO {

    private final DBPool dbPool = new DBPool();

    @Override
    public boolean addBook(String title, String author, int year, int pages) {
        boolean result = false;

        String sqlQuery = "INSERT INTO BOOK " +
                "(title, author, year, pages)" +
                "VALUES" +
                "(?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, year);
            preparedStatement.setInt(4, pages);
            result = preparedStatement.execute();
            dbPool.closeConnection(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteBook(String title, String author) {
        boolean result = false;

        String sqlQuery = "DELETE FROM BOOK WHERE title = ? AND author = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            result = preparedStatement.execute();
            dbPool.closeConnection(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Request> allRequests() {
        List<Request> tmpList = new ArrayList<Request>();

        String sqlQuery = "SELECT * FROM REQUEST WHERE done = FALSE";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Request request = new Request.Builder()
                        .book_id(resultSet.getInt("book_id"))
                        .user_id(resultSet.getInt("user_id"))
                        .done(resultSet.getBoolean("done"))
                        .home(resultSet.getBoolean("home"))
                        .library(resultSet.getBoolean("library"))
                        .build();
                tmpList.add(request);
            }
            dbPool.closeConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tmpList;
    }

    @Override
    public boolean updateRequest(Request request) {
        return false;
    }
}
