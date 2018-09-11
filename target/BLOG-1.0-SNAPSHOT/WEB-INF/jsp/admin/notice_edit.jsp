
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>博客管理系统</title>
    <link rel="stylesheet" href="/css/bootstrap4.0.min.css" >
    <script src="/js/jquery.slim.min.js" ></script>
    <script src="/js/popper.min.js" ></script>
    <script src="/js/bootstrap4.0.min.js"></script>
    <script src="/js/layer.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light" >
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand text-success" href="/admin/main">博客管理</a>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <!-- Example single danger button -->
                <div class="btn-group">
                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        新建
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="javascript:void(0);" onclick="fullScreen('添加文章','/admin/article/add')">文章</a>
                        <!-- <a class="dropdown-item" href="#">评论</a>-->
                    </div>
                </div>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="/admin/main">主页 </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/admin/article/list">文章管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/notice/edit">公告管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/advertisement/edit">广告管理</a>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0" action="/admin/article/search" method="GET">
            <input class="form-control mr-sm-2" type="search" placeholder="文章标题或内容..." aria-label="Search" name="word">
            <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">搜索</button>
        </form>

        <a class="btn btn-outline-danger btn-sm" href="/admin/logout" role="button">退出</a>
    </div>
</nav>
<br/>
<form action="/admin/notice/edit/do" method="post">
    <center>
    <div>
        公告：
        <div>
            <textarea name="notifydesc" id="notifydesc" cols="30" rows="5">${notice.notifydesc }</textarea>
        </div>
        <div>
            <input type="submit" class="btn btn-primary" value="发布"/>
        </div>
        <div>
            <font id="mark" color="red">${mark }</font>
        </div>
    </div>
    </center>

</form>
<br>
<center>
<table width='600'  border='1' cellspacing='1'>
    <thead>
    <tr class="table-info">
        <th>id</th>
        <th>历史公告</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${noticeList}" var="notice">
        <tr>
            <th scope="row">${notice.id}</th>
            <td>${notice.notifydesc}</td>
            <td><button type="button" class="btn btn-outline-danger btn-sm" onclick="ifdelete('${notice.id}','${notice.notifydesc}') ">删除</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <br><br><br><br><br><br>
    <div class="btn-group">
        <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            新建
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="javascript:void(0);" onclick="fullScreen('layer弹出框','/admin/article/add2')">弹框</a>
            <!-- <a class="dropdown-item" href="#">评论</a>-->
        </div>
    </div><br><br>
</center>
<script src="/js/jquery-3.2.1.min.js"></script>
<script>
    function fullScreen(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            area: ['50%', '50%'],
            content: url,
     //       content:'http://www.baidu.com',
            maxmin: true
        });
        layer.full(index);
    }

    function ifdelete(id,desc) {
        layer.confirm('确定删除该公告吗?', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                type: 'POST',
                url: '/admin/notice/delete',
                datatype:'json',
                data:{"id":id},
                success: function(data){
                    if(data['stateCode']==1){
                        layer.msg('删除成功!',{icon:1,time:1000});
                        setTimeout("window.location.reload()",1000);
                    }
                    else {
                        layer.msg('删除失败!',{icon:5,time:1000});
                    }
                },
                error:function(data) {
                    console.log('错误...');
                },
            });
        }, function(){

        });
    }

</script>
</body>
</html>