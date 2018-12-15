<%--
  Created by IntelliJ IDEA.
  User: Mi Pro
  Date: 18.11.2018
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = pageContext.getException().getMessage();
%>
<html>
<head>
    <title>404</title>
</head>
<body>
<h1>${pageContext.response.setStatus(404, "((")}</h1>
<h2> ${pageContext.exception.message} </h2>
</body>
</html>
