<%@ page import="shool.wuhues.oa.bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/6/16
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部门详情</title>
</head>
<body>
<%
    Dept d = (Dept) request.getAttribute("dept");
%>
    <h1>部门详情</h1>
    <hr>
    部门编号:<%=d.getDeptno()%><br>
    部门名称:<%=d.getDname()%><br>
    部门位置:<%=d.getLoc()%><br>

    <input type="button" value="后退" onclick="window.history.back()" />
</body>
</html>
