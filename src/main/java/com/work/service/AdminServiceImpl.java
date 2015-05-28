package com.work.service;

import com.work.entity.Request;
import com.work.dao.AdminDAO;

import java.util.List;

/**
 * Created by Eugine Sokirka on 21.05.2015.
 */
public class AdminServiceImpl implements AdminService{

    private final AdminDAO adminDAO;

    public AdminServiceImpl(AdminDAO adminDAO){
        this.adminDAO = adminDAO;
    }

    @Override
    public boolean addBook(String title, String author, int year, int pages) {
        return adminDAO.addBook(title, author, year, pages);
    }

    @Override
    public boolean deleteBook(String title, String author) {
        return adminDAO.deleteBook(title, author);
    }

    @Override
    public List<Request> allRequests() {
        return adminDAO.allRequests();
    }

    @Override
    public boolean updateRequest(int request_id, boolean home, boolean library) {
        return adminDAO.updateRequest(request_id, home, library);
    }
}
