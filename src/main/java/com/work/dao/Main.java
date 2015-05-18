package com.work.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dispatcher-servlet.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        DAOImpl dao = new DAOImpl(dataSource);
    }
}
