<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/4
  Time: 11:14
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
<div class="row clearfix">
    <div class="col-md-12 column">
        <form class="form-horizontal" role="form" action="/admin_productImage_update" method="post">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="hidden" class="form-control" id="id" name="id" value="${pi.id}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="hidden" class="form-control" id="pid" name="pid" value="${pid}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="url" class="col-sm-2 control-label">图片url</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="url" name="url" value="${pi.url}"/>
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

