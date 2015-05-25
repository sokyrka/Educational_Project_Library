package com.work.dao;

import com.work.common.Request;

import java.util.List;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public interface AdminDAO {

    boolean addBook(String title, String author, int year, int pages);

    boolean deleteBook(String title, String author);

    List<Request> allRequests();

    boolean updateRequest(int request_id, boolean home, boolean library);

    void changeBookStatus(int request_id);
}
