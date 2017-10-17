
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta content="text/html" charset="UTF-8"/>
    <title>在线编辑器</title>
    <script src="../../js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/showdown/1.7.6/showdown.min.js"></script>
    <link href="../../css/bootstrap.css" rel="stylesheet"></head>
<link href="../../css/Editor.css" rel="stylesheet"></head>

</head>
<body>

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
            <div class="form-group">
                <div class="row">
                    <button class="btn btn-primary">插入图片</button>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <button class="btn btn-primary">插入连接</button>
                </div>
            </div>
                       <div class="form-group">
                <div class="row">
                    <button class="btn btn-primary">所有文章</button>
                </div>
            </div>
           <div class="form-group">
                <div class="row">
                    <button class="btn btn-primary">新建</button>
                </div>
            </div>

                  <div class="form-group">
                <div class="row">
                    <button class="btn btn-primary">登录</button>
                </div>
            </div>

                         <div class="form-group">
                <div class="row">
                    <button class="btn btn-primary">注册</button>
                </div>
            </div>



            <div class="form-group">
                <div class="row">
                    <button class="btn btn-primary">保存</button>
                </div>
            </div>
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
$(document).ready(()=> {


    onContentChange();

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
</script>
</html>
