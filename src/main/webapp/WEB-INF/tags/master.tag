<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageTitle" type="java.lang.String" required="true" %>
<%@ attribute name="pageClass" type="java.lang.String" required="false" %>
<html>
<head>
    <title>${pageTitle}</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="href="<c:url value="/styles/main.css"/>">
</head>
</head>
<body class=${pageClass}>
<header>
    <c:url var="contextLink" context="${pageContext.servletContext.contextPath}" value="/products" />
    <a href="${contextLink}">
        <img src="${pageContext.servletContext.contextPath}/images/logo.svg"/>
        PhoneShop   Total:${cart.total}
    </a>
</header>
<main>
    <p>
        Welcome to Expert-Soft training!
    </p>
    <jsp:doBody/>
</main>
</body>
</html>