package com.work.dao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Eugine Sokirka on 18.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            Properties prop = new Properties();
            prop.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            prop.put(Context.PROVIDER_URL, "t3://localhost:7001");
            Context ctx = new InitialContext(prop);
            Object obj = ctx.lookup("myDB");

            DataSource ds = (DataSource) obj;
            conn = ds.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM BOOK");
            while (rs.next()){
                System.out.println(rs.getString("title"));
            }
            ctx.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
