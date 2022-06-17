<%@ page import="java.util.List" %>
<%@ page import="shool.wuhues.oa.bean.Dept" %><%--
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
</head>
<body>
    <script type="text/javascript">
        function del(dno){
            var ok = window.confirm("确认要删除该数据吗？");
            if(ok){
                document.location.href = "<%=request.getContextPath()%>/dept/del?dno=" + dno;
            }
        }
    </script>
    <h1 align="center">部门列表</h1>
    <hr>
    <a href="<%=request.getContextPath()%>/user/exit">退出系统</a>
    <table border="1xp" width="50%" align="center">
        <tr align="center">
            <th>序号</th>
            <th>部门编号</th>
            <th>部门名称</th>
            <th>部门位置</th>
            <th>功能列表</th>
        </tr>
        <%
            List<Dept> deptList = (List<Dept>)request.getAttribute("deptList");
            int i = 0;
            for(Dept dept : deptList){

        %>
        <tr align="center">
            <td><%=++i%></td>
            <td><%=dept.getDeptno()%></td>
            <td><%=dept.getDname()%></td>
            <td><%=dept.getLoc()%></td>
            <td>
                <a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
                <a href="<%=request.getContextPath()%>/dept/detail?f=edit&dno=<%=dept.getDeptno()%>">修改</a>
                <a href="<%=request.getContextPath()%>/dept/detail?f=detail&dno=<%=dept.getDeptno()%>">详细信息</a>
            </td>
        </tr>
        <% }%>

    </table>
    <a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
</body>
</html>
