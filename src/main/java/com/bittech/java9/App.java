package com.bittech.java9;
import com.bittech.java9.util.CommUtils;

import java.sql.*;
import java.util.Collections;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // 1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 2.获取连接
        Properties props = CommUtils.loadProperties("db.properties");
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(
                    url,username,password
            );
        // 3.执行SQL
        statement = connection.createStatement();
        String sqlStr = "SELECT * FROM user";
            System.out.println(sqlStr);
        rs = statement.executeQuery(sqlStr);
        while (rs.next()) {
            int id = rs.getInt("id");
            String userName = rs.getString("username");
            String ps = rs.getString("password");
            System.out.println("id为"+id+",用户名为:"+
                    userName+",密码为:"+ps);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 4.关闭资源
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
