<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Name" var="Name"/>
<fmt:message bundle="${loc}" key="locale.message.Surname" var="Surname"/>
<fmt:message bundle="${loc}" key="locale.message.Email" var="Email"/>
<fmt:message bundle="${loc}" key="locale.message.Phone" var="Phone"/>
<fmt:message bundle="${loc}" key="locale.message.Password" var="Password"/>
<fmt:message bundle="${loc}" key="locale.message.Login" var="Login"/>
<fmt:message bundle="${loc}" key="locale.message.Signup" var="Signup"/>
<fmt:message bundle="${loc}" key="locale.message.Message1" var="Message1"/>
<fmt:message bundle="${loc}" key="locale.message.Back" var="Back"/>

<html lang="${language}">
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="css/button.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />


</head>
<body>
<div align="center" >
<h2>${Signup}</h2>
<h3>${Message1}</h3>
</div>
<form action="controller" method="post" name="inputform" onsubmit="return check();">
    <input type="hidden" id="command" name="command" value=""/>
    <div align="center">
<table class="simple_table" >
    <tr>
        <td>${Name}</td>
        <td><input type="text" name="name" value="" onkeypress="checkreq()" onkeyup="checkreq()" onblur="checkreq()"></td>
    </tr>
    <tr>
        <td >${Surname}</td>
        <td><input type="text" name="surname"  value="" onkeypress="checkreq()" onkeyup="checkreq()" onblur="checkreq()"></td>
    </tr>
    <tr>
        <td>${Email}</td>
        <td><input type="text" name="email"  value="" onkeypress="checkreq()" onkeyup="checkreq()" onblur="checkreq()"></td>
    </tr>
    <tr>
        <td >${Phone}</td>
        <td><input type="text" name="phone"  value="" onkeypress="checkreq()" onkeyup="checkreq()" onblur="checkreq()"></td>
    </tr>
    <tr>
        <%-- тип пустая строка --%>
    </tr>
    <tr>
        <td >${Login}</td>
        <td><input type="text" name="login"  value="" onkeypress="checkreq()" onkeyup="checkreq()" onblur="checkreq()"></td>
    </tr>
    <tr>
        <td >${Password}</td>
        <td><input type="password" name="password"  value="" onkeypress="checkreq()" onkeyup="checkreq()" onblur="checkreq()"></td>
    </tr>
</table>
    </div>
<br/>
    <c:set scope="request" value="previous-page" var="login-page"/>
    <div align="center">
<input class="button" name ="registration" type="submit" onclick="document.getElementById('command').value='signup';"
       value="${Signup}" disabled="disabled">
        </div>
<br/>

</form>
<%--<a href="/jsp/login.jsp">${Back}</a>--%>
</body>
</html>
