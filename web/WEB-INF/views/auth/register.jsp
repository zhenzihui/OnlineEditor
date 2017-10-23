<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/19
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String authcode=(String)session.getAttribute("authcode");

%>
<html>
<head>
    <SCRIPT>
        let authode='<%=authcode%>';
        let msg;
        switch(authode)
        {
            case "error_login":
                msg="登录失败";
                break;
            case "error_reg":
                msg="注册失败";
                break;
        }

        if(msg!=null)
        {
            alert(msg);
        }


    </SCRIPT>
    <script src="../../js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/showdown/1.7.6/showdown.min.js"></script>
    <link href="../../css/bootstrap.css" rel="stylesheet"></head>
<link href="../../css/Editor.css" rel="stylesheet"></head>

<title>注册</title>
</head>
<body style="background: url('https://i.loli.net/2017/08/15/59926e94e27df.jpg') no-repeat fixed bottom center;background-size: cover;">
<div class="container">
    <div class="row">
<div class="col-md-2"></div>
<div class="col-md-8">
    <div class="form-control">
    <div class="panel panel-heading">
        注册表单
    </div>
    <div class="panel panel-body">
        <form action="/auth/register.do" method="post">
            <div class="form-group">
                <div class="form-group">
                    <label for="email">邮箱：</label>
                    <input id="email" type="email" name="email" class="form-control" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="password">密码：</label>
                <input id="password" type="password" name="password" class="form-control"  required/>
            </div>
            <div class="form-group">
                <label for="confirm">确认密码：</label>
                <input id="confirm" type="password" name="confirm" class="form-control" required />
            </div>
            <div class="form-group">
                <input type="submit" name="reg" class="form-control btn btn-primary" value="注册"/>
            </div>

        </form>
    </div>
</div>
</div>
<div class="col-md-2"></div>
</div>
</div>
</body>
</html>
