<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhenzihui
  Date: 2017/11/9
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作日志</title>
    <script src="../../js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/showdown/1.7.6/showdown.min.js"></script>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/Editor.css" rel="stylesheet">
    <link href="../../css/log.css" rel="stylesheet">
</head>
<body style="background: url('https://images2.alphacoders.com/792/thumb-1920-792612.jpg') no-repeat fixed bottom center;background-size: cover;">
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <ul class="list-group">
                <li class="list-group-item">操作日志</li>
                <c:forEach items="${logs}" var="log">
                    <li class="list-group-item">${log.timestamp}时对文章${log.article_id}进行了${log.type}操作</li>
                </c:forEach>
            </ul>
            <button  id="delete" class="btn btn-warning">删除该用户所有日志</button>
        </div>
        <div class="col-md-2"></div>
    </div>

</div>


</body>
<script>
    $("#delete").click(function () {
            deleteLogs();
    });
    function deleteLogs() {
        $.post("/log/flush${uid}.do","",function (data) {
                alert("删除成功");
                location.reload();
                }
        ,'json')
    }
</script>
</html>
