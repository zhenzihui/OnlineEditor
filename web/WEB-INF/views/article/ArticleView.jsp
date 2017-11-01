<%@ page import="com.zhenz.Entity.User" %>
<%@ page import="com.zhenz.Entity.Article" %><%--
  Created by IntelliJ IDEA.
  User: zhenzihui
  Date: 2017/10/24
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
User user =(User)session.getAttribute("user");

%>
<html>
  <head>
    <title>查看${article.title}</title>
  </head>
      <script src="../../js/jquery.js"></script>
      <script src="https://cdn.bootcss.com/showdown/1.7.6/showdown.min.js"></script>
      <link href="../../css/bootstrap.css" rel="stylesheet">
  <link href="../../css/Editor.css" rel="stylesheet">
  </head>
  <body>
  <div class="container">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8" >
                <div class="form-control header">
                    <h3 style="margin-top: 7%;">${article.title}</h3>
                </div>
            <div class="predictor form-control" id="article">
            </div>

                <a href="/article/${article.id}/update.do" class="btn btn-warning">修改文章</a>
                <button onclick="delet()" class="btn btn-danger">删除文章</button>


            </div>
            <div class="col-md-2">
                <textarea id="source" class="form-control" readonly style="visibility: hidden">${article.body}</textarea>
            </div>
        </div>
  </div>
<script>
    $(document).ready(function () {
        var md=$("#source").val();
        var converter=new showdown.Converter();
        var article=$("#article");
        var text=converter.makeHtml(md);
        article.html(text);
    });

    function delet() {
        $.post("/article/${article.id}/delete.do",{},function () {
            location.href="./my.do";
        })

    }


</script>

  
  </body>
</html>
