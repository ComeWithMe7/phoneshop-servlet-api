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
            <form method="post" action="<c:url value="/products/${product.id}"/>">
                <p><input type="search" name="quantity" value="${not empty quantity ? quantity : 1}">
                    <button type="submit">add</button>
                    <input type="hidden" name="id" value="${product.id}">
                </p>
            </form>
            <p>${quantityAnswer}</p>
            <h3>Your cart</h3>
            <table>
            <c:forEach var="cartItem" items="${sessionScope.cart.cartItemList}">
                <tr>
                    <td>
                        <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartItem.product.imageUrl}">
                    </td>
                    <td><a href="<c:url value="/products/${cartItem.productID}"/>">${cartItem.product.description}</a></td>
                    <td>${cartItem.quantity}</td>
                </tr>
            </c:forEach>
            </table>
            <br>
            <form method="get" action="<c:url value="/cart"/>">
                <input type="submit" value="Cart"></p>
            </form>
        </main>
    </body>
</html>