package com.work.dao;

import com.work.common.Book;
import com.work.common.User;

import java.util.List;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public interface UserDAO {

    void addUser(String first_name, String second_name, String login, String password);

    boolean validateUser(String login, String password);

    List<Book> getAllFreeBook();

    Book findBook(String title);

    boolean addRequest(String title, String login);

    List<Book> getUsersBook(String login);
}
