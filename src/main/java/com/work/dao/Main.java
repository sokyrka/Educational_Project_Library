package com.work.dao;

import com.work.common.User;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.addRequest("Java", "root");
    }
}
