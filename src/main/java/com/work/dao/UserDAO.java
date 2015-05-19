package com.work.dao;

import com.work.common.User;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public interface UserDAO {
    void addUser();
    User getUser();
    void updateUser();
    void deleteUser();
}
