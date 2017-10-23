<%@ page import="com.zhenz.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
User user =(User)session.getAttribute("user");
String authcode=(String)session.getAttribute("authcode");

%>

<html>
<head>
    <meta content="text/html" charset="UTF-8"/>
    <title>在线编辑器</title>

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

</head>
<body style="background: url('https://i.loli.net/2017/08/15/59926e94e27df.jpg') no-repeat fixed bottom center;background-size: cover;">

<div class="container">

    <div class="row" style="height: 10%">
        <div class="col-md-2"></div>
        <div class="col-md-5">
            <h3>编辑器：</h3>
        </div>
        <div class="col-md-5">
            <h3>预览：</h3>

        </div>

    </div>

    <div class="row">

        <div class="col-md-2">
            <%if(user!=null){%>

               <a href="#" class="list-group-item active">当前用户：<%=user.getEmail()%></a>

               <a href="/auth/logout.do" class="list-group-item">安全退出</a>
               <a href="#" class="list-group-item">所有文章</a>
               <a href="#" class="list-group-item">新建</a>
               <a href="#" class="list-group-item">保存</a>




            <%}else{%>
            <a href="/auth/register.do" class="list-group-item">注册</a>
            <a id="loginbtn" href="#" class="list-group-item">登录</a>
            <%}%>



            <div class="row">

            </div>
            <div class="row">

            </div>
        </div>
        <div class="col-md-5">

            <textarea id="editor" class="form-control editor" onkeyup="onContentChange()" >

            </textarea>
        </div>
        <div class="col-md-5">

            <div id="predictor" class="predictor form-control">

            </div>
        </div>
    </div>
</div>

</body>
<script>
    let predictor=$("#predictor");
    let editor=$("#editor");
    let converter=new showdown.Converter();
    let loginbtn=$("#loginbtn");
$(document).ready(()=> {


    onContentChange();

    let showed=0;
    loginbtn.click(()=>
    {
        if(showed===0) {
            loginbtn.after(showLoginForm());
            showed=1;
        }else
            {
                $("#loginform").remove();
                showed=0;
            }
    });


    predictor.scroll(()=>{
        editor.scrollTop($(this).scrollTop());
        console.log("editor:"+editor.scrollTop()+";preview:"+predictor.scrollTop());
    });




});
function onContentChange()
{
    let html=converter.makeHtml(editor.val());
    predictor.html(html);

}

function showLoginForm() {
    let form="<div id='loginform' class='list-group-item'>" +
        "<form action='/auth/login.do' method='post'>" +
        "<div class='form-group'>" +
        "<label for='email'>邮箱：</label>" +
        "<input id='email' name='email' class='form-control' type='email'>" +
        "</div>" +
        "<div class='form-group'>" +
        "<label for='password'>密码：</label>" +
        "<input id='password' name='password' class='form-control' type='password'>" +
        "</div>" +

        "<div class='form-group'>" +
        "<input type='submit' class='form-control btn btn-primary' value='登录'>" +
        "</div>" +
        "</form>" +
        "</div>";

    return form;
}

</script>
</html>
