package com.work.service;

import com.work.common.Book;
import com.work.dao.UserDAO;
import java.util.List;

/**
 * Created by Eugine Sokirka on 21.05.2015.
 */
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public void addUser(String first_name, String second_name, String login, String password) {
        userDAO.addUser(first_name, second_name, login, password);
    }

    @Override
    public boolean validateUser(String login, String password) {
        return userDAO.validateUser(login, password);
    }

    @Override
    public List<Book> getAllFreeBook() {
        return userDAO.getAllFreeBook();
    }

    @Override
    public Book findBook(String title) {
        return userDAO.findBook(title);
    }
}
