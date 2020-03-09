<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/9
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Bean Scope</title>
</head>
<body>
<!-- page -> request -> session -> application(servletContext)  -->
<h1>Hello Bean Scope</h1>
<h3>@requestScope \${user.name}: ${user.name}</h3>
<h3>@sessionScope \${userObject.name}: ${userObject.name}</h3>
<h3>@applicationScope \${applicationScope["scopedTarget.user"].name}: ${applicationScope["scopedTarget.user"].name}</h3>
<ul>
    <li>Singleton</li>
    <li>Prototype</li>
    <li>Session</li>
    <li>Request</li>
    <li>...</li>
</ul>
</body>
</html>
