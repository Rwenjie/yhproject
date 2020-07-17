package com.zb.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 封装数据库连接的工具
 */
public class DBUtils {

    private static ComboPooledDataSource dataSource =
            new ComboPooledDataSource("c3p0-config.xml");
    
    //2.通过连接池获取连接
    public  static Connection getConnectionByDatasource(){
        Connection conn = null;
        try {
             conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  conn;
    }

    //2.关闭资源
    public static void close(Connection conn){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
