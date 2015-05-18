package com.work.dao;

import com.work.common.Book;

import java.util.List;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public interface DAO {
    public List<Book> getAll();
    public boolean delete();
    public boolean create();
    public Book update();
}
