package com.work.dao;

import com.work.common.Book;
import com.work.common.User;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public interface AdminDAO {

    void addBook();

    Book getBook();

    void updateBook();

    void deleteBook();

    User getUser();

    void updateUser();

    boolean deleteUser(String first_name, String second_name);
}
