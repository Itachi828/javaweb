<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/6/16
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增部门</title>
</head>
<body>
    <h1>新增部门</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/dept/doSave" method="post">
        部门编号:<input type="text" name="deptno" /><br>
        部门名字:<input type="text" name="dname" /><br>
        部门位置:<input type="text" name="loc" /><br>
        <input type="submit" value="保存">
    </form>
</body>
</html>
