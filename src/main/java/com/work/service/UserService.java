package com.work.service;

import com.work.entity.Book;

import java.util.List;

/**
 * Created by Eugine Sokirka on 21.05.2015.
 */
public interface UserService {

    void addUser(String first_name, String second_name, String login, String password);

    boolean validateUser(String login, String password);

    List<Book> getAllFreeBook();

    Book findBook(String title);

    boolean addRequest(String title, String login);

    List<Book> getUsersBook(String login);

    boolean deleteUsersBook(String title, String login);
}
