<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Login" var="Login"/>
<fmt:message bundle="${loc}" key="locale.message.Password" var="Password"/>
<fmt:message bundle="${loc}" key="locale.message.Enter" var="Enter"/>
<fmt:message bundle="${loc}" key="locale.message.Signup" var="Signup"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>Log in</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link rel="stylesheet" href="css/button.css" type="text/css" />
    <link rel="stylesheet" href="css/list.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />

    <script type="text/javascript">
        <!--
      function check(){
            f_login=document.inputform.login.value.toString();
            if (f_login==''){
                document.inputform.login.focus();
                document.inputform.login.required="required";
                return false;
             }
            f_password=document.inputform.password.value.toString();
            if (f_password==''){
                document.inputform.password.focus();
                document.inputform.password.required="required";
                return false;
            }
        } //-->
    </script>
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

<form action="controller" method="post" name="inputform" onsubmit="return check();">
    <input type="hidden" name="command" value="login"/>
    <table class="simple_table" >
        <tr>
            <td >${Login}</td>
            <td><input type="text" name="login" value="" required =""></td>
        </tr>
        <tr>
            <td >${Password}</td>
            <td><input type="password" name="password"  value="" required=""></td>
        </tr>
    </table>
    <br/>
    <input name="enter" class="button" type="submit"  value="${Enter}" >

</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="go-to-registr-page"/>
    <input class="button" type="submit" value="${Signup}">
</form>
<%-- <a href="jsp/registration.jsp">${Signup}</a>--%>
</body>
</html>