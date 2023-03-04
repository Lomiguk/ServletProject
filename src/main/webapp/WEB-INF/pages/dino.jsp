<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 04.03.2023
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
            isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello user! Your dino is</h1>
    id: ${dinosaurId}
    <br/>
    name: ${dinosaurName}
    <br/>
    <h2>All dinosaurs:</h2>
    <table border="1" cellpadding=10 cellspacing=5>
        <th>ID</th>
        <th>Name</th>
        <c:forEach var="dino" items="${dinosaurs}">
            <tr>
                <td>${dino.getId()}</td>
                <td>${dino.getName()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
