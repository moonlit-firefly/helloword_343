<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/6
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    欢迎!
                </h4>尊贵的用户:${user.name}
                <a href="/admin_user_exitLogin" class="alert-link">退出登录</a>
                <a href="/admin_user_list">
                    <button type="button" class="btn btn-default btn-primary">用户管理</button>
                </a>
                <a href="/admin_user_listOrder">
                    <button type="button" class="btn btn-default btn-primary">查看所有订单</button>
                </a>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>收货地址</th>
                    <th>收货人称呼</th>
                    <th>收货人手机号</th>
                    <th>备注</th>
                    <th>订单创建时间</th>
                    <th>支付时间</th>
                    <th>发货时间</th>
                    <th>收获确认时间</th>
                    <th>用户id</th>
                    <th>订单状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${os}" var="o" varStatus="st">
                    <tr>
                        <td>${o.id}</td>
                        <td>${o.address}</td>
                        <td>${o.receiver}</td>
                        <td>${o.mobile}</td>
                        <td>${o.userMessage}</td>
                        <td>${o.createDate}</td>
                        <td>${o.payDate}</td>
                        <td>${o.deliveryDate}</td>
                        <td>${o.confirmDate}</td>
                        <td>${o.user.id}</td>
                        <td>${o.getStatusDesc()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>


