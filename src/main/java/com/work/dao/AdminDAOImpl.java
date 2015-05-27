package com.work.dao;

import com.work.common.Request;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugine Sokirka on 21.05.2015.
 */
public class AdminDAOImpl implements AdminDAO {

    private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);
    private final DBPool dbPool = new DBPool();

    @Override
    public boolean addBook(String title, String author, int year, int pages) {
        boolean result = false;

        logger.info("add book");

        String sqlQuery = "INSERT INTO BOOK " +
                "(book_id, title, author, year, pages)" +
                "VALUES" +
                "(SEQ_BOOK.nextval, ?, ?, ?, ?)";
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, year);
            preparedStatement.setInt(4, pages);
            result = preparedStatement.execute();
            dbPool.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("addBook", e);
        }
        return result;
    }

    @Override
    public boolean deleteBook(String title, String author) {
        boolean result = false;

        logger.info("delete book");

        String sqlQuery = "DELETE FROM BOOK WHERE title = ? AND author = ?";
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            result = preparedStatement.execute();
            dbPool.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("deleteBook", e);
        }
        return result;
    }

    @Override
    public List<Request> allRequests() {
        List<Request> tmpList = new ArrayList<Request>();

        logger.info("all requests");

        String sqlQuery = "SELECT * FROM REQUEST WHERE done = 0";
        try {
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Request request = new Request.Builder()
                        .request_id(resultSet.getInt("request_id"))
                        .book_id(resultSet.getInt("book_id"))
                        .user_id(resultSet.getInt("user_id"))
                        .done(resultSet.getBoolean("done"))
                        .home(resultSet.getBoolean("home"))
                        .library(resultSet.getBoolean("library"))
                        .build();
                tmpList.add(request);
            }
            dbPool.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("allRequests", e);
        }
        return tmpList;
    }

    @Override
    public boolean updateRequest(int request_id, boolean home, boolean library) {
        boolean result = false;

        logger.info("update request");

        String sqlQuery = "UPDATE REQUEST " +
                "SET done = 1, home = ?, library = ? " +
                "WHERE request_id = ?";
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setBoolean(1, home);
            preparedStatement.setBoolean(2, library);
            preparedStatement.setInt(3, request_id);
            result = preparedStatement.execute();
            dbPool.closeConnection(connection);
            changeBookStatus(request_id);
        } catch (SQLException e) {
            logger.error("updateRequest", e);
        }
        return result;
    }

    @Override
    public void changeBookStatus(int request_id){

        logger.info("change book status");

        String sqlQuery = "UPDATE BOOK SET busy = 1 " +
                "WHERE book_id = (SELECT book_id FROM REQUEST WHERE request_id = ?)";
        try {
            Connection connection = dbPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, request_id);
            preparedStatement.execute();
            dbPool.closeConnection(connection);
        } catch (SQLException e) {
            logger.error("changeBookStatus", e);
        }
    }
}
