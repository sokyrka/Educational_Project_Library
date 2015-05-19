package com.work.dao;

import com.work.common.Book;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public interface AdminDAO {
    void addBook();
    Book getBook();
    void updateBook();
    void deleteBook();
}
