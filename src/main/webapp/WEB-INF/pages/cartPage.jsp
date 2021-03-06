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
        ${quantityAnswer}
        <br>
        <form method="post" action="<c:url value="/cart"/>">
            <table>
                    <c:forEach var="cartItem" items="${sessionScope.cart.cartItemList}">
                        <tr>
                            <td>
                                <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartItem.product.imageUrl}">
                            </td>
                            <td><a href="<c:url value="/products/${cartItem.product.id}"/>">${cartItem.product.description}</a></td>
                            <td>${cartItem.quantity}</td>
                            <td>
                                <p><input type="search" name="quantity" value="${not empty quantityMap[cartItem.productID] ? quantityMap[cartItem.productID] : cartItem.quantity}"></p>
                                <input type="hidden" name="productID" value="${cartItem.product.id}">
                                <p>${answers[cartItem.productID]}</p>
                            </td>
                            <td>
                                <button formaction="<c:url value = "cart/${cartItem.productID}/delete" />">delete</button>
                            </td>
                        </tr>
                    </c:forEach>
            </table>
            <br>
            <p><input type="submit" value="Update"></p>
        </form>
        <form action="<c:url value="/checkout"/>">
            <input type="submit" value="Checkout">
        </form>
    </c:if>
</main>
</body>
</html>