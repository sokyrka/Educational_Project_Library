package com.work.dao;

import com.work.entity.Book;

import java.util.List;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public interface UserDAO {

    void addUser(String first_name, String second_name, String login, String password);

    boolean validateUser(String login, String password);

    List<Book> getAllFreeBook();

    com.work.entity.Book findBook(String title);

    boolean addRequest(String title, String login);

    List<Book> getUsersBook(String login);

    boolean deleteUsersBook(String title, String login);

    void changeBookStatus(String title);
}
