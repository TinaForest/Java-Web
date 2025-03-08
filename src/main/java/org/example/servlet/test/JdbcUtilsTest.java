package org.example.servlet.test;


import org.example.servlet.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {

    @Test
    public void getConnection() {
        Connection connection = JdbcUtils.getConnection();
//        System.out.println(connection);
        JdbcUtils.rollbackAndClose();
    }
}