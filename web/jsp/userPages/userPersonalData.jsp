<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
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
<fmt:message bundle="${loc}" key="locale.message.Logout" var="Logout"/>
<fmt:message bundle="${loc}" key="locale.message.Signup" var="Signup"/>
<fmt:message bundle="${loc}" key="locale.message.Update" var="Update"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>Personal Data</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link rel="stylesheet" href="css/button.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
  <body>
  <jsp:useBean id="user" beanName="com.epam.prihodko.finaltask.domain.Person"
               type="com.epam.prihodko.finaltask.domain.Person" scope="session"/>
    <form action="controller" method="post">
        <input type="hidden" id="command" name="command" value=""/>

        <div align="center">
            <h3>Your personal data</h3>
            <br/>
            <table class="simple_table">
                <tr>
                    <td>${Name}</td>
                    <td><input id="name" type="text" name="name" value="<jsp:getProperty name="user" property="name"/>"/></td>
                </tr>
                <tr>
                    <td >${Surname}</td>
                    <td><input type="text" name="surname"  value="<jsp:getProperty name="user" property="surname"/>"></td>
                </tr>
                <tr>
                    <td>${Email}</td>
                    <td><input type="text" name="email"  value="<jsp:getProperty name="user" property="email"/>" ></td>
                </tr>
                <tr>
                    <td>${Phone}</td>
                    <td><input type="text" name="phone"  value="<jsp:getProperty name="user" property="phone"/>" ></td>
                </tr>
            </table>
        </div>
        <div align="center">
            <input class="button" type="submit" onclick="document.getElementById('command').value='update';"
               value="${Update}">
        <input class="button" type="submit" onclick="document.getElementById('command').value='logout';"
               value="${Logout}">
        </div>
    </form>
  </body>
</html>
