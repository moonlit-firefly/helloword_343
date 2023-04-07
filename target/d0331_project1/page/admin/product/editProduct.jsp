<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/3
  Time: 22:17
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
<div class="row clearfix">
    <div class="col-md-12 column">
        <form class="form-horizontal" role="form" action="/admin_product_update" method="post">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="hidden" class="form-control" id="id" name="id" value="${p.id}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="hidden" class="form-control" id="cid" name="cid" value="${p.category.id}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="productName" class="col-sm-2 control-label">商品名称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="productName" name="name" value="${p.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="productSubTitle" class="col-sm-2 control-label">商品小标题</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="productSubTitle" name="subTitle" value="${p.subTitle}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="productOriginalPrice" class="col-sm-2 control-label">商品原价</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="productOriginalPrice" name="originalPrice"
                           value="${p.originalPrice}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="productPromotePrice" class="col-sm-2 control-label">商品优惠价</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="productPromotePrice" name="promotePrice"
                           value="${p.promotePrice}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="productStock" class="col-sm-2 control-label">库存</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="productStock" name="stock" value="${p.stock}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">更新</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>

