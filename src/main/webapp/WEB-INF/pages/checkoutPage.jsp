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
    <h3>Your cart</h3>
    <c:if test="${empty sessionScope.cart.cartItemList}">
        <p>Your cart is empty</p>
    </c:if>
    <c:if test="${not empty sessionScope.cart.cartItemList}">
        <br>
        <form method="post" action="<c:url value="/checkout"/>">
            <table>
                <c:forEach var="cartItem" items="${sessionScope.cart.cartItemList}">
                    <tr>
                        <td>
                            <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartItem.product.imageUrl}">
                        </td>
                        <td><a href="<c:url value="/products/${cartItem.product.id}"/>">${cartItem.product.description}</a></td>
                        <td>${cartItem.quantity}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <p>Cart: ${cart.total}</p>
                </tr>
            </table>
            <br>
            <table>
                <tr>
                    <p>First Name: </p><input type="search" name="firstName" value="${firstName}">
                    <p>${firstNameVald}</p>
                </tr>
                <tr>
                    <p>Last Name: </p><input type="search" name="lastName" value="${lastName}">
                    <p>${lastNameVald}</p>
                </tr>
                <tr>
                    <p>Phone Number (Enter in a format +123456789999): </p> <input type="search" name="phone" value="${phone}">
                    <p>${phoneVald}</p>
                </tr>
                <tr>
                    <p>Delivery mode: </p> <input disabled name="mode" value="courier">
                </tr>
                <tr>
                    <p>Delivery costs: </p> <input disabled name="costs" value="$30">
                </tr>
                <tr>
                    <p>Payment method: </p> <input disabled name="payment" value="money to corier">
                </tr>
                <tr>
                    <p>Delivery address: </p> <input type="search" name="address" value="${address}">
                </tr>
            </table>
            <p><input type="submit" value="Confirm"></p>
        </form>
    </c:if>
</main>
</body>
</html>