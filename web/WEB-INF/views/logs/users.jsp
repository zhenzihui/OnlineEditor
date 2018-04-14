<%@ page import="java.util.List" %>
<%@ page import="com.zhenz.Entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhenzihui
  Date: 2017/11/14
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> users=(List<User>) request.getAttribute("users");


%>
<html>
<head>
    <title>用户列表</title>
    <script src="../../js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/showdown/1.7.6/showdown.min.js"></script>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/Editor.css" rel="stylesheet">
    <link href="../../css/log.css" rel="stylesheet">
</head>
<body style="background: url('https://images2.alphacoders.com/792/thumb-1920-792612.jpg') no-repeat fixed bottom center;background-size: cover;">
<div class="container" >
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <ul class="list-group">
                <li class="list-group-item">用户列表</li>
                <%for(User user:users){%>
                <li class="list-group-item"><a href="/log/user<%=user.getId()%>.do"><%=user.getEmail()%></a> </li>
                <%}%>
                <li class="list-group-item"><button id="delete" class="btn btn-danger">清空所有日志</button> </li>

            </ul>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
<script>
    $("#delete").click(function () {
        flushAll();
    });

    function flushAll() {
        $.post("/log/flush.do","",function (data,status) {
           if(status=="success")
           {
               alert("清空完成");
           }

        })
    }

</script>
</html>
