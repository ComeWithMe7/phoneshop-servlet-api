<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Product Detail</title>
    <link href="http://fonts.googleapis.com/css?family=Lobster+Two" rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="<c:url value="/styles/main.css"/>">
</head>
<body class="product-list">
<jsp:include page="header.jsp"/>
<main>
    <p>
        Welcome to Expert-Soft training!
    </p>
        <br>
        <form method="post" action="<c:url value="/quickorder"/>">
            <c:set value="1" var="start"/>
            <c:set value="10" var="limit"/>
            <table>
                <tr>
                    <td>Product's code</td>
                    <td>Quantity</td>
                </tr>

                <c:forEach begin="${start}" end="${limit}" var="i">
                    <tr>
                        <td><input type="search" name="code"></td>
                        <td><input type="search" name="quantity"></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
</main>
</body>
</html>