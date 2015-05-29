package com.work.dao.factory;

import com.work.dao.UserDAO;
import com.work.dao.hibernate.UserHibernateImpl;
import com.work.dao.jdbc.UserJDBCImpl;
import com.work.dao.jpa.UserJPAImpl;

/**
 * Created by Eugine Sokirka on 29.05.2015.
 */
public class UserDAOFactory {

    public UserDAO getUserDAO(String userDAOType){
        if(userDAOType == null){
            return null;
        }

        if(userDAOType.equalsIgnoreCase("JDBC")){
            return new UserJDBCImpl();
        }else if(userDAOType.equalsIgnoreCase("JPA")){
            return new UserJPAImpl();
        }else if(userDAOType.equalsIgnoreCase("HIBERNATE")){
            return new UserHibernateImpl();
        }
        return null;
    }
}
