<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
    <head>
    </head>
    <body>
        <%
            String userLogin = (String) session.getAttribute("userLogin");
            if (userLogin == null){
                userLogin = "unknown user";
            }
        %>
        <h2>Hello <%=userLogin%>!</h2>
        <h3>Simple menu</h3>
        <ol>
            <li>
                <form action="${pageContext.request.contextPath}/dinosaur/all">
                    <input type="submit" value="view all dino">
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/profile/login">
                    <input type="submit" value="login">
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/dinosaur/add">
                    <input type="submit" value="add dino">
                </form>
            </li>
        </ol>
    </body>
</html>
