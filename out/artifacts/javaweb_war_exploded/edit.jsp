<%@ page import="shool.wuhues.oa.bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/6/16
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改部门</title>
</head>
<body>
    <%--<%Dept d = (Dept) request.getAttribute("dept");%>--%>
    <h1>修改部门</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/dept/modify" method="post">
        部门编号<input type="text" name="deptno" value="${dept.deptno}" readonly /><br>
        部门名称<input type="text" name="dname" value="${dept.dname}"/><br>
        部门位置<input type="text" name="loc" value="${dept.loc}"/><br>
        <input type="submit" value="修改"/><br>
    </form>
</body>
</html>
