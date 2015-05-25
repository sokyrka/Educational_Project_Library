package com.work.dao;

import com.work.common.Request;
import com.work.common.User;

import java.util.ArrayList;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAOImpl();
        ArrayList<Request> arrayList = (ArrayList) adminDAO.allRequests();
        for(Request request : arrayList){
            System.out.println(request.toString());
        }
    }
}
