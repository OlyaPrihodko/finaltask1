<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mytag" uri="customtags"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Logout" var="Logout"/>
<fmt:message bundle="${loc}" key="locale.message.PersonalData" var="PersonalData"/>
<fmt:message bundle="${loc}" key="locale.message.ToBookARoom" var="ToBookARoom"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>User Personal Area</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <link rel="stylesheet" href="css/button.css" type="text/css"/>
</head>
<body>
<jsp:useBean id="setBeanOrder" beanName="com.epam.prihodko.finaltask.domain.SetBean"
             type="com.epam.prihodko.finaltask.domain.SetBean" scope="session"/>

<h1>Welcome</h1>
<h1>Your Personal Area</h1>
<hr/>
${us}, hello!
<hr/>
<form action="controller" method="post">
    <input type="hidden" id="command" name="command" value=""/>
    <div align="center">
    <input class="button" type="submit" onclick="document.getElementById('command').value='go-to-user-personal-data-page';"
           value="${PersonalData}">
        <br/>
    <input class="button" type="submit" onclick="document.getElementById('command').value='logout';"
           value="${Logout}">
        <br/>
        <input class="button" type="submit" onclick="document.getElementById('command').value='go-to-make-an-order-page';"
               value="${ToBookARoom}">
    </div>
</form>
<br/>
<mytag:mylist size="${setBeanOrder.size}">
    ${setBeanOrder.element}
</mytag:mylist>
</body>
</html>
