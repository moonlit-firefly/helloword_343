<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/9
  Time: 14:00
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
        <div class="col-md-1 column"></div>
        <div class="col-md-10 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>购物车id</th>
                    <th>商品名称</th>
                    <th>商品数量</th>
                    <th>商品优惠价格</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ps}" var="p" varStatus="st">
                    <tr>
                        <td><p>${p.id}</p></td>
                        <td><p>${p.product.name}</p></td>
                        <td><p>${p.number}</p></td>
                        <td><p>${p.product.promotePrice}</p></td>
                        <td><p>${p.stock}</p></td>
                        <td>
                            <button type="button" class="btn btn-default btn-primary delete-btn"
                                    style="background-color:crimson"
                                    name="/fore_cart_delete?id=${p.id}">删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-1 column"></div>
    </div>
</div>
</body>
<script>
    $(function () {
        $('.delete-btn').click(function () {
            //获取后续ajax要发送的url
            let url = $(this).attr("name");
            let that = $(this);
            $.get(
                url,
                function (result) {
                    if (result == "success") {
                        that.parent().parent().hide();
                    } else {
                        alert("出现错误，请刷新页面！")
                    }
                }
            )
        })
    })
</script>
</html>

