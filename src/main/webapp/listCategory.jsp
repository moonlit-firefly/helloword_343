<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: GUAN
  Date: 2023/3/28
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>编辑</td>
        <td>删除</td>
    </tr>
    <c:forEach items="${categories}" var="c" varStatus="st">
        <tr id="${c.id}">
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td><a href="editCategory?id=${c.id}" >编辑</a></td>
            <td><input type="button" class="btn" value="删除"></td>
        </tr>
    </c:forEach>
    <tr>
        <td><a href="?start=0">首页</a> </td>
        <td><a href="?start=${page.start-page.count}">上一页</a> </td>
        <td><a href="?start=${page.start+page.count}">下一页</a> </td>
        <td><a href="?start=${page.last}">尾页</a> </td>
    </tr>
</table>
<form action="addCategory" method="post">
    <input type="text" name="name"><br/>
    <input type="submit" value="提交"/>
</form>
</body>
<script>
    //1.将页面的表单数据提交给后端
    $(function () {
        $(".btn").click(function () {
            alert();
            let value = $(this).parent().parent().attr('id');
            console.log(value);
            let url = "/admin_category_delete";
            $.post(
                url,
                {"id": value},
                function (id) {
                    console.log(id);
                    let trid="#"+id;
                    $(trid).hide();
                }
            )
        })
    })
</script>
</html>
