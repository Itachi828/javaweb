<%@ page import="java.util.List" %>
<%@ page import="shool.wuhues.oa.bean.Dept" %>
<%--引入jstl标签--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/6/15
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部门列表</title>
<%--    设置整个页面的基础路径--%>
</head>
<body>
    <script type="text/javascript">
        function del(dno){
            var ok = window.confirm("确认要删除该数据吗？");
            if(ok){
                document.location.href = "${pageContext.request.contextPath}/dept/del?dno=" + dno;
            }
        }
    </script>
    <h1 align="center">部门列表</h1>
    <h2>${username}</h2>
    <hr>
    <a href="<%=request.getContextPath()%>/user/exit">退出系统</a>
    <table border="1xp" width="50%" align="center">
        <tr align="center">
            <th>序号</th>
            <th>部门编号</th>
            <th>部门名称</th>
            <th>功能列表</th>
        </tr>
        <%--<%
            List<Dept> deptList = (List<Dept>)request.getAttribute("deptList");
            int i = 0;
            for(Dept dept : deptList){

        %>--%>
        <c:forEach items="${deptList}" varStatus="deptStatus" var="dept">
        <tr align="center">
            <td>${deptStatus.count}</td>
            <td>${dept.deptno}</td>
            <td>${dept.dname}</td>
            <td>
                <a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
                <a href="<%=request.getContextPath()%>/dept/detail?f=edit&dno=${dept.deptno}">修改</a>
                <a href="<%=request.getContextPath()%>/dept/detail?f=detail&dno=${dept.deptno}">详细信息</a>
            </td>
        </tr>
        </c:forEach>

    </table>
    <a href="${pageContext.request.contextPath}/add.jsp">新增部门</a>
</body>
</html>
