<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Message2" var="Message2"/>
<fmt:message bundle="${loc}" key="locale.message.Back" var="Back"/>
<html lang="${language}">
<head>
    <title>Error</title>
 <link rel="stylesheet" href="css/button.css" type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />

</head>
<body>
<div align="center" >
<h2>${Message2}</h2>
<form action="controller" method="post">
    <input type="hidden" id="command" name="command" value=""/>
    <br/>
    <div align="center">
        <input class="button" type="submit" onclick="document.getElementById('command').value='previous-page';" value="${Back}">
    </div>
</form>
</div>
</body>
</html>
