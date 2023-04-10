<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/9
  Time: 21:46
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
<div class="row clearfix">
    <div class="col-md-3 column">
    </div>
    <div class="col-md-6 column">
        <label for="name" >昵称</label><br><br>
        <input type="text" class="name" id="name" name="name"/><br><br>
        <label for="password" >密码</label><br><br>
        <input type="password" class="password" id="password" name="password"/><br><br>
        <button type="submit" class="register">修改</button><br><br>
    </div>
    <div class="col-md-3 column">
    </div>
</div>
</body>
<script>
    $(function () {
        $('.register').click(function () {
            //获取后续ajax要发送的属性值
            let url1 = "/fore_user1_edit";
            let name = $('.name').val();
            let password = $('.password').val();
            $.ajax({
                    url: url1,
                    data: {"name": name, "password": password},
                    success: function (result) {
                        if (result == "success") {
                            alert("修改成功");
                        }else {
                            alert("未填写完整信息")
                        }
                    }
                }
            )
        })
    })
</script>
</html>
