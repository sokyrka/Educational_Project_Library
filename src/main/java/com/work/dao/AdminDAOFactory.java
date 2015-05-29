package com.work.dao;

import com.work.dao.jdbc.AdminDAOImpl;
import com.work.dao.jpa.AdminJPAImpl;

/**
 * Created by Eugine Sokirka on 29.05.2015.
 */
public class AdminDAOFactory {

    public AdminDAO getAdminDAO(String adminDAOType){
        if(adminDAOType == null){
            return null;
        }

        if(adminDAOType.equalsIgnoreCase("JDBC")){
            return new AdminDAOImpl();
        }else if(adminDAOType.equalsIgnoreCase("JPA")){
            return new AdminJPAImpl();
        }
        return null;
    }
}
