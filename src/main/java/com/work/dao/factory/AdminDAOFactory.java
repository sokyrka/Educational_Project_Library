package com.work.dao.factory;

import com.work.dao.AdminDAO;
import com.work.dao.hibernate.AdminHibernateImpl;
import com.work.dao.jdbc.AdminJDBCImpl;
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
            return new AdminJDBCImpl();
        }else if(adminDAOType.equalsIgnoreCase("JPA")){
            return new AdminJPAImpl();
        }else if(adminDAOType.equalsIgnoreCase("HIBERNATE")){
            return new AdminHibernateImpl();
        }
        return null;
    }
}
