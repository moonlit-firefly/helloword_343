<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/3
  Time: 22:01
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
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    欢迎!
                </h4>尊贵的用户:${user.name}
                <a href="fore_user1_exitLogin" class="alert-link">退出登录</a>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>商品图片</th>
                    <th>商品名称</th>
                    <th>商品小标题</th>
                    <th>商品原价</th>
                    <th>商品优惠价</th>
                    <th>库存</th>
                    <th>加入购物车</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ps}" var="p" varStatus="st">
                    <tr>
                        <td>
                            <a href="/fore_productView_show?id=${p.id}">
                                <img src="${p.images.get(0).url}" width="70px" height="70px"/>
                            </a>
                        </td>
                        <td><a href="/fore_productView_show?id=${p.id}">${p.name}</a></td>
                        <td><p>${p.subTitle}</p></td>
                        <td><p>${p.originalPrice}</p></td>
                        <td><p>${p.promotePrice}</p></td>
                        <td><p>${p.stock}</p></td>
                        <td>
                            <button type="button" class="btn btn-default btn-primary order-add"
                                    style="background-color:#dc143c"
                                    name="/fore_cart_add?id=${p.id}">加入购物车
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $('.order-add').click(function () {
            //获取后续ajax要发送的url
            let url = $(this).attr("name");
            let that = $(this);
            $.get(
                url,
                function (result) {
                    if (result == "success") {
                        that.css({borderColor: "greenyellow"});
                    } else {
                        alert("出现错误，请刷新页面！")
                    }
                }
            )
        })
    })
</script>
</html>

