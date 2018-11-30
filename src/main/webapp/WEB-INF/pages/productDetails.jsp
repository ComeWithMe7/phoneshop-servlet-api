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

            <table>
                <thead>
                <tr>
                    <td>Image</td>
                    <td>Description</td>
                    <td class="price">Price</td>
                </tr>
                </thead>
                    <tr>
                        <td>
                            <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                        </td>
                        <td>${product.description}</td>
                        <td class="price">
                            <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/>
                        </td>
                    </tr>
            </table>
        </main>
    </body>
</html>