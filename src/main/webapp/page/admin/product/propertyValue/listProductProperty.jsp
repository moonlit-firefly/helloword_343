<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈郅治
  Date: 2023/4/4
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <style>
        .container {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }

        .propertyValue {
            min-width: 250px;
            height: 40px;
            width: auto;
            margin: 20px;
            border: 1px solid gray;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<jsp:include page="../../include/header.jsp"/>
<div class="container">
    <c:forEach items="${ppvs}" var="ppv" varStatus="st">
        <div>
            <label for="propertyValue">${ppv.property.name}</label><br>
            <div class="d1">
                <input type="hidden" class="propertyValue" id="pvid" name="pvid" value="${ppv.id}"/>
                <input type="text" class="propertyValue" id="propertyValue"
                       name="/admin_productPropertyValue_update" value="${ppv.value}"/>
            </div>
        </div>
    </c:forEach>
</div>
</body>
<script>
    $(function () {
        $('.propertyValue').change(function () {
            //获取后续ajax要发送的属性值
            let url1 = $(this).attr("name");
            let pvid = $(this).prev('input').val();
            let propertyValue = $(this).val();
            let that = $(this);
            $.ajax({
                    url: url1,
                    data: {"pvid": pvid, "propertyValue": propertyValue},
                    success: function (result) {
                        if (result == "success") {
                            // alert();
                            that.css({borderColor: "greenyellow"});
                        } else {
                            alert("出现错误，请刷新页面！")
                        }
                    }
                }
            )
        })
    })
</script>
</html>
