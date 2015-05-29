package com.work.dao.hibernate;

import com.work.dao.UserDAO;
import com.work.entity.Book;

import java.util.List;

/**
 * Created by Eugine Sokirka on 29.05.2015.
 */
public class UserHibernateImpl implements UserDAO {

    @Override
    public void addUser(String first_name, String second_name, String login, String password) {

    }

    @Override
    public boolean validateUser(String login, String password) {
        return false;
    }

    @Override
    public List<Book> getAllFreeBook() {
        return null;
    }

    @Override
    public Book findBook(String title) {
        return null;
    }

    @Override
    public boolean addRequest(String title, String login) {
        return false;
    }

    @Override
    public List<Book> getUsersBook(String login) {
        return null;
    }

    @Override
    public boolean deleteUsersBook(String title, String login) {
        return false;
    }

    @Override
    public void changeBookStatus(String title) {

    }
}
