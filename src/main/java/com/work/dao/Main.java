package com.work.dao;


/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAOImpl();
        adminDAO.updateRequest(1, 1, true, false);
    }
}
