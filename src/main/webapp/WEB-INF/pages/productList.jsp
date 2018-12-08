<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<html>
  <head>
    <title>Product List</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="<c:url value="/styles/main.css"/>">
  </head>
  <body class="product-list">
    <jsp:include page="header.jsp"/>
    <main>
      <p>
        Welcome to Expert-Soft training!
      </p>
      <form method="get">
        <p><input type="search" name="searchLine" placeholder="Site search" value="${searchLineAttrib}">
          <input type="submit" value="Search"></p>
      </form>
      <table>
        <thead>
          <tr>
            <td>Image</td>
            <td>Description <a href="<c:url value="/products"><c:param name="searchLine" value="${searchLineAttrib}"/><c:param name="sortingParameter" value="upDescription"/></c:url>">up</a> <a href="<c:url value="/products"><c:param name="searchLine" value="${searchLineAttrib}"/><c:param name="sortingParameter" value="downDescription"/></c:url>">down</a></td>
            <td class="price">Price <a href="<c:url value="/products"><c:param name="searchLine" value="${searchLineAttrib}"/><c:param name="sortingParameter" value="upPrice"/></c:url>">up</a> <a href="<c:url value="/products"><c:param name="searchLine" value="${searchLineAttrib}"/><c:param name="sortingParameter" value="downPrice"/></c:url>">down</a> </td>
          </tr>
        </thead>
        <c:forEach var="product" items="${products}">
          <tr>
            <td>
              <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
            </td>
            <td><a href="<c:url value="/products/${product.id}"/>">${product.description}</a></td>
            <td class="price">
              <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/>
            </td>
          </tr>
        </c:forEach>
      </table>
      <h2>Recently viewed</h2>
      <table>
        <c:forEach var="product" items="${sessionScope.viewedProducts.productList}">
          <tr>
            <td>
              <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
            </td>
            <td><a href="<c:url value="/products/${product.id}"/>">${product.description}</a></td>
            <td class="price">
              <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/>
            </td>
          </tr>
        </c:forEach>
      </table>
    </main>
  </body>
</html>