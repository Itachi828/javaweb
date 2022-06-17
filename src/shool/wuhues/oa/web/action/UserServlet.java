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
import java.util.function.Predicate;

/**
 * @Author: Itachi
 * @Date: 2022/6/17 10:44
 * @Version: jdk1.8
 * @Description: 验证登录信息
 */
@WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if("/user/login".equals(servletPath)){
            doLogin(request,response);
        }else if("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session对象，销毁seesion对象
        HttpSession session = request.getSession(false);
        if(session != null){

            session.invalidate();

            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    //设置cookie有效期为0，表示删除该cookie
                    cookie.setMaxAge(0);
                    //设置cookie的路径
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect(request.getContextPath()+ "/index.jsp");

/*            Cookie[] cookies = request.getCookies();

            Cookie cookie1 = new Cookie("username","");
            Cookie cookie2 = new Cookie("password","");
            cookie1.setMaxAge(0);
            cookie2.setMaxAge(0);
            cookie1.setPath(request.getContextPath());
            cookie2.setPath(request.getContextPath());

            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.sendRedirect(request.getContextPath());
            response.sendRedirect(request.getContextPath());*/

        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

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


        //登录成功or失败
        if(success){
            //获取session对象（没有则新建）
            HttpSession session = request.getSession();
            session.setAttribute("username",username);

            //用户登录成功并且勾选了十天免登录
            String autoLogin = request.getParameter("autoLogin");
            if("1".equals(autoLogin)){
                //创建cookie对象储存用户名及密码
                Cookie cookie1 = new Cookie("username",username);
                Cookie cookie2 = new Cookie("password",password);

                //设置cookie的有效时间
                cookie1.setMaxAge(60 * 60 * 24 *10);
                cookie2.setMaxAge(60 * 60 * 24 *10);

                //设置cookie的地址
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());

                //将cookie响应到浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }

            response.sendRedirect(request.getContextPath() + "/dept/list");
        }else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
