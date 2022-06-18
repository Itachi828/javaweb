package shool.wuhues.oa.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import shool.wuhues.oa.utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Itachi
 * @Date: 2022/6/17 11:10
 * @Version: jdk1.8
 * @Description: 查询cookie
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                if("username".equals(name)){
                    username = cookie.getValue();
                }else if("password".equals(name)){
                    password = cookie.getValue();
                }
            }
        }
        if(username != null && password != null){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            boolean success = false;
            try {
                conn = DBUtil.getConn();
                String sql = "select * from t_user where username = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                //执行sql
                rs = ps.executeQuery();
                //因为结果最多为1条数据，所以不需要while循环
                if(rs.next()){
                    success = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBUtil.close(conn,ps,rs);
            }
            if(success){
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect(request.getContextPath() + "/dept/list");
            }else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }

        }else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }
}
