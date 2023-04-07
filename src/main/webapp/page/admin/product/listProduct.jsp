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
                    <th>商品图片</th>
                    <th>商品名称</th>
                    <th>商品小标题</th>
                    <th>商品原价</th>
                    <th>商品优惠价</th>
                    <th>库存</th>
                    <th>商品创建时间</th>
                    <th>编辑</th>
                    <th>删除</th>
                    <th>图片管理</th>
                    <th>属性值设置</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ps}" var="p" varStatus="st">
                    <tr>
                        <td><img src="${p.images.get(0).url}" width="70px" height="70px"/></td>
                        <td><p>${p.name}</p></td>
                        <td><p>${p.subTitle}</p></td>
                        <td><p>${p.originalPrice}</p></td>
                        <td><p>${p.promotePrice}</p></td>
                        <td><p>${p.stock}</p></td>
                        <td><p>${p.createDate}</p></td>
                        <td>
                            <a href="/admin_product_edit?id=${p.id}">
                                <button type="button" class="btn btn-default btn-primary">编辑</button>
                            </a>
                        </td>
                        <td>
                            <button type="button" class="btn btn-default btn-primary delete-btn"
                                    style="background-color:crimson"
                                    name="/admin_product_delete?id=${p.id}">删除
                            </button>
                        </td>
                        <td>
                            <a href="/admin_productImage_list?id=${p.id}">
                                <button type="button" class="btn btn-default btn-primary"
                                        style="background-color:goldenrod">图片管理</button>
                            </a>
                        </td>
                        <td>
                            <a href="/admin_productPropertyValue_list?id=${p.id}">
                                <button type="button" class="btn btn-default btn-primary"
                                        style="background-color:lightblue">属性值设置</button>
                            </a>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" role="form" action="/admin_product_add" method="post">
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="hidden" class="form-control" id="cid" name="cid" value="${cid}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="productName" class="col-sm-2 control-label">商品名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="productName" name="name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="productSubTitle" class="col-sm-2 control-label">商品小标题</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="productSubTitle" name="subTitle"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="productOriginalPrice" class="col-sm-2 control-label">商品原价</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="productOriginalPrice" name="originalPrice"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="productPromotePrice" class="col-sm-2 control-label">商品优惠价</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="productPromotePrice" name="promotePrice"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="productStock" class="col-sm-2 control-label">库存</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="productStock" name="stock"/>
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

