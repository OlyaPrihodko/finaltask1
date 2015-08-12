<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<html lang="${language}">
<head>
    <title> Main admin page</title>
    <link rel="stylesheet" href="css/button.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body>
<h1>Hello</h1>
<h2>${us} - Admin</h2>
</body>
</html>
