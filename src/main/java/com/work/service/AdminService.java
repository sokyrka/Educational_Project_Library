package com.work.service;

import com.work.common.Request;

import java.util.List;

/**
 * Created by Eugine Sokirka on 21.05.2015.
 */
public interface AdminService {

    boolean addBook(String title, String author, int year, int pages);

    boolean deleteBook(String title, String author);

    List<Request> allRequests();

    boolean updateRequest(int request_id, boolean home, boolean library);
}
