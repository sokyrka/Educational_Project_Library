package com.work.dao;

import com.work.dao.jdbc.UserDAOImpl;
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
            return new UserDAOImpl();
        }else if(userDAOType.equalsIgnoreCase("JPA")){
            return new UserJPAImpl();
        }
        return null;
    }
}
