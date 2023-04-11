<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/9
  Time: 13:54
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
    <style>
        .container {
            width: 100%;
            display: flex;
            flex-direction: column;
        }

        .header1 {
            position: fixed;
            width: 100%;
            height: 60px;
            min-width: 1200px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-left: 25px;
            background-color: rgb(240, 240, 240);
            z-index: 1;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix header1">
        <div class="col-md-1 column">
        </div>
        <div class="col-md-3 column">
            <h3 class="text-info">
                <c:if test="${empty buyUser.name}">
                    <a href="/page/fore/register/login.jsp">
                        <button type="button">登录</button>
                    </a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/page/fore/register/register.jsp">
                        <button type="button">注册</button>
                    </a>
                </c:if>
                <c:if test="${buyUser.name !=null}">
                    欢迎您！<a href="/page/fore/user/editUser.jsp">${buyUser.name}</a> 。
                    &nbsp;&nbsp;<a href="/fore_user1_exitLogin">退出登录</a>
                </c:if>
            </h3>
        </div>
        <div class="col-md-4 column">
            <h3>
                <form class="form-horizontal" role="form" action="/fore_productView_search" method="post">
                    <input type="text" name="value">
                    <input type="submit" value="搜索">
                </form>

            </h3>
        </div>
        <div class="col-md-1 column">
            <h5><a href="/fore_order_cart">购物车</a></h5>
        </div>
        <div class="col-md-1 column">
            <h5><a href="/fore_order_manager">订单管理</a></h5>
        </div>
        <div class="col-md-2 column">
            <h5><a href="/page/admin/login/login.jsp">
                <button type="button" class="btn btn-default btn-primary">管理员登陆</button>
            </a></h5>
        </div>
    </div>
    <div style="height: 100px">

    </div>
    <div class="row clearfix">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-8 column">
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <div class="carousel slide" id="carousel-558125">
                        <ol class="carousel-indicators">
                            <li class="active" data-slide-to="0" data-target="#carousel-558125"></li>
                            <li data-slide-to="1" data-target="#carousel-558125"></li>
                            <li data-slide-to="2" data-target="#carousel-558125"></li>
                            <li data-slide-to="3" data-target="#carousel-558125"></li>
                            <li data-slide-to="4" data-target="#carousel-558125"></li>
                            <li data-slide-to="5" data-target="#carousel-558125"></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="item active">
                                <img alt="" src="${p.images.get(0).url}" width="500px" height="500px"/>
                            </div>
                            <c:forEach items="${p.images}" var="i" varStatus="st">
                                <div class="item">
                                    <img alt="" src="${i.url}" width="500px" height="500px"/>
                                </div>
                            </c:forEach>
                        </div>
                        <a class="left carousel-control" href="#carousel-558125" data-slide="prev"><span
                                class="glyphicon glyphicon-chevron-left"></span></a> <a
                            class="right carousel-control" href="#carousel-558125" data-slide="next"><span
                            class="glyphicon glyphicon-chevron-right"></span></a>
                    </div>
                </div>
                <div class="col-md-6 column" style="display:flex;flex-wrap:wrap;">
                    <c:forEach items="${ppvs}" var="ppv" varStatus="st">
                        <div style="margin-left:30px;">
                            <label>${ppv.property.name}</label><br>
                            <div >
                                ${ppv.value}"
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-md-2 column">
            <a href="/fore_cart_add?id=${p.id}">
                <button type="button" class="btn btn-default btn-primary">加入购物车</button>
            </a>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-8 column">
            <h4>商品评价</h4><br>
            <c:forEach items="${rs}" var="r" varStatus="st">
                <div>
                    评价者:${r.user.name}<br>
                    评价:${r.content}<br>
                    评价时间:${r.createDate}<br><br>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-2 column">
        </div>
    </div>
</div>
</body>
</html>
