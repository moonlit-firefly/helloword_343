<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/7
  Time: 16:24
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
        }

        .info {
            min-width: 1200px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding-left: 50px;
        }

        .minWidth {
            min-width: 1250px;
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
                <c:if test="${empty user.name}">
                    <a href="/page/fore/register/login.jsp">
                        <button type="button">登录</button>
                    </a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/page/fore/register/register.jsp">
                        <button type="button">注册</button>
                    </a>
                </c:if>
                <c:if test="${user.name !=null}">
                    欢迎您！${user.name}。
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
    <div class="row clearfix minWidth">
        <div class="col-md-1 column">
        </div>
        <div class="col-md-2 column">
            <c:forEach items="${cs}" var="c" varStatus="st">
                <ul><a href="/fore_productView_listByCategory?id=${c.id}">${c.name}</a></ul>
            </c:forEach>
        </div>
        <div class="col-md-8 column">
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
                        <img alt="" src="../../../imgs/mainPage/1.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                产品1
                            </h4>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="../../../imgs/mainPage/2.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                产品2
                            </h4>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="../../../imgs/mainPage/3.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                产品3
                            </h4>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="../../../imgs/mainPage/4.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                产品4
                            </h4>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="../../../imgs/mainPage/5.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                产品5
                            </h4>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="../../../imgs/mainPage/6.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                产品6
                            </h4>
                        </div>
                    </div>

                </div>
                <a class="left carousel-control" href="#carousel-558125" data-slide="prev"><span
                        class="glyphicon glyphicon-chevron-left"></span></a> <a
                    class="right carousel-control" href="#carousel-558125" data-slide="next"><span
                    class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
        <div class="col-md-1 column">
        </div>
    </div>
    <div style="height: 60px">

    </div>
    <div class="row clearfix">
        <div class="col-md-1 column">
        </div>
        <div class="col-md-10 column  info">
            <c:forEach items="${ps}" var="p" varStatus="st">
                <div class="col-md-3 column">
                    ${p.name}<br/>
                    <a href="/fore_productView_show?id=${p.id}">
                        <img src="${p.images.get(0).url}" width="200px" height="200px"/><br/>
                    </a>
                    ${p.promotePrice}<br/>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-1 column">
        </div>
    </div>
</div>
</body>
</html>
