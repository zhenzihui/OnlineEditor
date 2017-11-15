<%@ page import="com.zhenz.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user =(User)session.getAttribute("user");
    String authcode=(String)session.getAttribute("authcode");

%>
<html>
<head>
    <title>修改${article.title}</title>
    <script src="../../js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/showdown/1.7.6/showdown.min.js"></script>
    <link href="../../css/bootstrap.css" rel="stylesheet"></head>
<link href="../../css/Editor.css" rel="stylesheet">

</head>
<body>

<div class="container">

    <div id="editorCompoLabel" class="row" style="height: 10%">
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


            <button id="saveBtn" class="list-group-item form-control ">保存</button>
            <a href="/article/my.do" class="list-group-item">所有文章</a>



            <%}else{%>
            <a href="/auth/register.do" class="list-group-item">注册</a>
            <a href="#" id="loginbtn"  class="list-group-item">登录</a>
            <%}%>

        </div>

        <div class="col-md-5">



            <form id="editorForm">
                <div class="form-group">
                    <label for="title" >标题:</label>
                    <input id="title" class="form-control" value="${article.title}" name="title">
                </div>
                <div class="form-group">

                <textarea id="editor" class="form-control editor" onkeyup="onContentChange()" >${article.body}</textarea>
                </div>
            </form>
        </div>

        <div class="col-md-5">
            <div class="form-group">
                <div id="predictor" class="predictor form-control">

                </div>
            </div>

        </div>

    </div>
</div>

</body>
<script>
    var predictor=$("#predictor");
    var editor=$("#editor");
    var converter=new showdown.Converter();
    var loginbtn=$("#loginbtn");
    var saveBtn=$("#saveBtn");
    var title=$("#title");
    var newBtn=$("#newBtn");
    var editorLabel=$("#editorCompoLabel");
    var editorForm=$("#editorForm");
    $(document).ready(function() {




        onContentChange();

        var showed=0;

        newBtn.click(function () {
            var speed="normal";
            editorLabel.toggle(speed);
            editorForm.toggle(speed);
            predictor.toggle(speed);
        });

        saveBtn.click(function () {


            var titleval=title.val();
            var bodyval=editor.val();

            if($.trim(titleval)!=""&&$.trim(bodyval)!="")
            {
                $.post("/article/${article.id}.do",{title:title.val(),body:editor.val()},function (data) {
                    data=eval(data);
                    console.log(data.status);

                    if(data.status=="ok")
                    {
                        alert("保存成功!");
                    }
                    else if(data.status=="err") {
                        alert("保存失败!");
                    }
                    else
                    {
                        alert("保存失败!\n请完整填写字段");
                    }
                },'json');
            }else
            {
                alert("保存失败");
            }

        });

        loginbtn.click(function()
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


        predictor.scroll(function(){
            editor.scrollTop($(this).scrollTop());
            console.log("editor:"+editor.scrollTop()+";preview:"+predictor.scrollTop());
        });




    });
    function onContentChange()
    {
        var html=converter.makeHtml(editor.val());
        predictor.html(html);

    }




    function showLoginForm() {
        var form="<div id='loginform' class='list-group-item'>" +
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
