package com.zoc.furns.test;

import com.zoc.furns.utils.JDBCUtilsByDruid;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtilsByDruidTest {

    @Test
    public void getConnection() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println("connection is " + connection);
        JDBCUtilsByDruid.close(null,null,connection);
    }
}
