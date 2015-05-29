package com.work.dao.hibernate;

import com.work.dao.AdminDAO;
import com.work.entity.Request;

import java.util.List;

/**
 * Created by Eugine Sokirka on 29.05.2015.
 */
public class AdminHibernateImpl implements AdminDAO {

    @Override
    public boolean addBook(String title, String author, int year, int pages) {
        return false;
    }

    @Override
    public boolean deleteBook(String title, String author) {
        return false;
    }

    @Override
    public List<Request> allRequests() {
        return null;
    }

    @Override
    public boolean updateRequest(int request_id, int home, int library) {
        return false;
    }

    @Override
    public void changeBookStatus(int request_id) {

    }
}
