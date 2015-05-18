package com.work.dao;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        DAOImpl dao = new DAOImpl();
    }
}
