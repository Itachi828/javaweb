package shool.wuhues.oa.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shool.wuhues.oa.bean.Dept;
import shool.wuhues.oa.utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Itachi
 * @Date: 2022/6/15 15:06
 * @Version: jdk1.8
 * @Description: 部门信息
 */
@WebServlet({"/dept/list","/dept/del","/dept/modify","/dept/doSave","/dept/detail"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("username") != null) {
            String servletPath = request.getServletPath();
            if ("/dept/list".equals(servletPath)) {
                doList(request, response);
            } else if ("/dept/del".equals(servletPath)) {
                doDel(request, response);
            } else if ("/dept/modify".equals(servletPath)) {
                doModify(request, response);
            } else if ("/dept/doSave".equals(servletPath)) {
                doSave(request, response);
            } else if ("/dept/detail".equals(servletPath)) {
                doDetail(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/list.jsp");
            }
        }else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    /**
     * 部门详情
     * @param request
     * @param response
     */
    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dno = request.getParameter("dno");
        Dept dept = new Dept();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            String sql = "select deptno,dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dno);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                dept.setDeptno(dno);
                dept.setDname(dname);
                dept.setLoc(loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("dept",dept);
        request.getRequestDispatcher("/" + request.getParameter("f") + ".jsp").forward(request,response);
    }

    /**
     * 部门新增
     * @param request
     * @param response
     */
    private void doSave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        try {
            conn = DBUtil.getConn();
            String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }

    /**
     * 部门修改
     * @param request
     * @param response
     */
    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Dept dept = new Dept();
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConn();
            String sql = "update dept set dname = ? , loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setString(3,deptno);
           count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        if(count == 1){
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }

    /**
     * 部门删除
     * @param request
     * @param response
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dno = request.getParameter("dno");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConn();
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }

    /**
     * 部门列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Dept> depts = new ArrayList<>();
        // Dept dept = new Dept();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            String sql = "select deptno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);

                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        request.setAttribute("deptList",depts);
        // 转发
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
