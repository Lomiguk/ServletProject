<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 09.03.2023
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/profile/login" method="post">
        <p>Login</p>
        <input type="text" name="login"> <br/>
        <p>Password</p>
        <input type="password" name="password">
        <input type="submit">
    </form>
</body>
</html>
