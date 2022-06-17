<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/6/15
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>OA系统</title>
  </head>
  <body>
    <h1 align="center">欢迎使用OA系统</h1>
    <hr>
    <h2>请先登录</h2>
    <form action="<%=request.getContextPath()%>/user/login" method="post">
      用户名:<input type="text" name="username" /><br>
      密 码：<input type="password" name="password" /><br>
      十天免登录<input type="checkbox" name="autoLogin" value="1"/><br>
      <input type="submit" value="登录" />
    </form>
  </body>
</html>
