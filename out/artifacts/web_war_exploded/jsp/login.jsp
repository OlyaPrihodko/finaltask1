<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Login" var="Login"/>
<fmt:message bundle="${loc}" key="locale.message.Password" var="Password"/>
<fmt:message bundle="${loc}" key="locale.message.Enter" var="Enter"/>
<fmt:message bundle="${loc}" key="locale.message.Signup" var="Signup"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="css/button.css" type="text/css" />
    <link rel="stylesheet" href="css/list.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />

</head>
<body>
<form>
    <div align="right"  >
        <select id="language" name="language" onchange="submit()" class="list">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''} class="list">English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''} class="list">Русский</option>
    </select>
    </div>
</form>

<form action="controller" method="post">
    <input type="hidden" id="command" name="command" value=""/>
    <table class="simple_table" >
        <tr>
            <td>${Login}</td>
            <td><input type="text" name="login" value=""></td>
        </tr>
        <tr>
            <td>${Password}</td>
            <td><input type="password" name="password"  value=""></td>
        </tr>
    </table>
    <br/>
    <input class="button" type="submit" onclick="document.getElementById('command').value='login';" value="${Enter}">
    <br/>
  <%--  <input class="button" type="submit" onclick=" <c:set var="toregistr" value="1"/>" value="${Signup}">
    <c:if test="${toregistr==1}"><jsp:forward page="/jsp/registration.jsp"/></c:if>
--%>
</form>
<a href="jsp/registration.jsp">${Signup}</a>
</body>
</html>