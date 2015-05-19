package com.work.dao;

import com.work.common.User;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public interface UserDAO {
    void addUser(String first_name, String second_name, String login, String password, int book_id);
    User getUser();
    void updateUser();
    boolean deleteUser(String first_name, String second_name);
}
