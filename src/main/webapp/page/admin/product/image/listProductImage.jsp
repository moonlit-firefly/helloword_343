<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/4
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../include/header.jsp"/>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    欢迎!
                </h4>尊贵的用户:${user.name}
                <a href="/admin_user_exitLogin" class="alert-link">退出登录</a>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th>图片id</th>
                    <th>图片url</th>
                    <th>图片</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pis}" var="pi" varStatus="st">
                    <tr>
                        <td><p>${pi.product.name}</p> </td>
                        <td><p>${pi.id}</p></td>
                        <td><p>${pi.url}</p></td>
                        <td><img src="${pi.url}" width="70px" height="70px"/></td>
                        <td>
                            <a href="/admin_productImage_edit?id=${pi.id}">
                                <button type="button" class="btn btn-default btn-primary">编辑</button>
                            </a>
                        </td>
                        <td>
                            <button type="button" class="btn btn-default btn-primary delete-btn"
                                    style="background-color:crimson"
                                    name="/admin_productImage_delete?id=${pi.id}">删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" role="form" action="/admin_productImage_add" method="post">
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="hidden" class="form-control" id="pid" name="pid" value="${pid}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="url" class="col-sm-2 control-label">图片url</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="url" name="url"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">添加</button>
                    </div>
                </div>
            </form>
        </div>
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

