package shool.wuhues.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @Author: Itachi
 * @Date: 2022/6/15 14:12
 * @Version: jdk1.8
 * @Description: 链接数据库的工具
 */
public class DBUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.mysql");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    static {
        try {
            // 注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 获取链接
    public static Connection getConn() throws SQLException {
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }
    // 释放资源
    public static void close(Connection conn, Statement stm,ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
