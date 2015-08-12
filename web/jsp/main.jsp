<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Logout" var="Logout"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>Welcome page</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <link rel="stylesheet" href="css/button.css" type="text/css"/>
</head>
<body>
    <h1>Welcome</h1>
<hr/>
${user}, hello!
<hr/>
<form action="controller" method="post">
    <input type="hidden" id="command" name="command" value=""/>
    <input class="button" name ="registration" type="submit" onclick="document.getElementById('command').value='logout';"
           value="${Logout}">
</form>
</body>
</html>
