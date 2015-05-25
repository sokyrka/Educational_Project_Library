package com.work.dao;

import com.work.common.Book;
import com.work.common.User;

import java.sql.*;

/**
 * Created by Eugine Sokirka on 21.05.2015.
 */
public class AdminDAOImpl implements AdminDAO {

    private final DBPool dbPool = new DBPool();

    @Override
    public boolean addBook() {
        return false;
    }


    @Override
    public boolean deleteBook() {
        return false;
    }

}
