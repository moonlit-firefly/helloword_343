<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/9
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-success text-center">
                欢迎来到多云转晴的商城系统，请登陆
            </h3>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <img alt="300x300" src="../../../imgs/login/login3.jpg" width="300px" height="300px"/>
        </div>
        <div class="col-md-4 column">
            <form role="form" action="/fore_user1_login" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1">用户:</label><input type="text" name="username" class="form-control" id="exampleInputEmail1" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码:</label><input type="password" name="password" class="form-control" id="exampleInputPassword1" />
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
        <div class="col-md-4 column">
            <img alt="300x300" src="../../../imgs/login/login4.jpg"  width="300px" height="300px"/>
        </div>
    </div>
</div>
</body>
</html>
