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
    <table>
        <c:forEach var="cartItem" items="${order.cart.cartItemList}">
            <tr>
                <td>
                    <img class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartItem.product.imageUrl}">
                </td>
                <td><a href="<c:url value="/products/${cartItem.product.id}"/>">${cartItem.product.description}</a></td>
                <td>${cartItem.quantity}</td>
            </tr>
        </c:forEach>
        <tr>
            <p>Cart: ${order.cart.total}</p>
        </tr>
    </table>
    <br>
    <table>
        <tr>
            <p>First Name: </p><input disabled name="firstName" value="${order.firstName}">
        </tr>
        <tr>
            <p>Last Name: </p><input disabled name="lastName" value="${order.lastName}">
        </tr>
        <tr>
            <p>Phone Number: </p> <input disabled name="phone"
                                                                           value="${order.phoneNumber}">
        </tr>
        <tr>
            <p>Delivery mode: </p> <input disabled name="mode" value="${order.mode}">
        </tr>
        <tr>
            <p>Delivery costs: </p> <input disabled name="costs" value="${order.costs}">
        </tr>
        <tr>
            <p>Payment method: </p> <input disabled name="payment" value="${order.payment}">
        </tr>
        <tr>
            <p>Delivery address: </p> <input disabled name="address" value="${order.address}">
        </tr>
    </table>
</main>
</body>
</html>