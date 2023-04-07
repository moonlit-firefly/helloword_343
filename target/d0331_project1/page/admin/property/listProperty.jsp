<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/3
  Time: 14:49
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
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>属性名</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pts}" var="pt" varStatus="st">
                    <tr>
                        <td>${pt.id}</td>
                        <td><a href="/admin_property_edit?id=${pt.id}">${pt.name}</a></td>
                        <td>
                            <a href="/admin_property_edit?id=${pt.id}">
                                <button type="button" class="btn btn-default btn-primary">编辑</button>
                            </a>
                        </td>
                        <td>
                            <button type="button" class="btn btn-default btn-primary delete-btn"
                                    name="/admin_property_delete?id=${pt.id}">删除
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
            <form class="form-horizontal" role="form" action="/admin_property_add" method="post">
                <div class="form-group">
                    <label for="categoryName" class="col-sm-2 control-label">属性名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="categoryName" name="name"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="hidden" class="btn btn-default" name="cid" value="${pts.get(0).category.id}">添加</button>
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
            let url=$(this).attr("name");
            let that=$(this);
            $.get(
                url,
                function (result) {
                    if(result=="success"){
                        that.parent().parent().hide();
                    }else{
                        alert("出现错误，请刷新页面！")
                    }
                }
            )
        })
    })
</script>
</html>
