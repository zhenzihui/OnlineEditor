<%@ page import="com.zhenz.Entity.User" %>


<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: zhenzihui
  Date: 2017/10/31
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章列表</title>
    <script src="../../js/jquery.js"></script>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/Editor.css" rel="stylesheet">
</head>
<body>
<script>
    console.log('${sessionScope.user.type}');
</script>
<div class="container">
    <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <div class="form-control header">
            <h3 style="margin-top: 7%;">文章列表</h3>

        </div>
        <ul class="list-group">
           <c:forEach items="${articles}" var="article" >
               <li class="list-group-item">
                   <div class="left-pill"><a href="/article/${article.id}.do">${article.title}</a></div>
               </li>
               <li class="list-group-item">

                   <a href="/article/${article.id}/update.do" class="btn btn-warning badge-warning">修改</a>
                   <button onclick="dele(${article.id})" class="btn btn-danger badge-danger">删除</button>
               </li>
           </c:forEach>
        </ul>
    </div>
    <div class="col-md-2"></div>
    </div>
</div>
</body>
<script>
    function dele(id) {
        $.post("/article/"+id+"/delete.do",{},function () {
            location.reload();
        })
    }

  $(document).ready(function () {

    });


</script>
</html>
