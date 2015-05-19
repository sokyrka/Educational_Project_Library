package com.work.dao;

import com.work.common.User;

/**
 * Created by Eugine Sokirka on 19.05.2015.
 */
public class UserDAOImpl implements UserDAO{

    private final String url = "jdbc:mysql://176.37.217.24:3306/zhenya_test1";
    private final String user = "quattro";
    private final String pass = "Zhenya2015";

    @Override
    public void addUser() {

    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }
}
