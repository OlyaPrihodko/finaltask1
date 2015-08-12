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
  <%--  <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    --%>
      <link rel="stylesheet" href="css/button.css" type="text/css" />
      <link rel="stylesheet" href="css/style.css" type="text/css" />
      <link rel="stylesheet" href="css/list.css" type="text/css" />


  <%-- --%>
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
<%--
<form>
<div class="container">
    <div class="row">
        <div class="btn-group btn-large" >
            <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                <i class="fa fa-language"></i>
                ${language}
                <i class="fa fa-caret-down"></i>
            </button>
            <ul class="dropdown-menu">
                <li onclick="${language == 'ru_RU' ? 'selected' : ''}">Русский</li>
                <li onclick="${language == 'en_EN' ? 'selected' : ''}">Английский</li>
            </ul>
        </div>
    </div>
</div>
</form>
--%>
<br/>
<form>
    <c:set scope="session" value="/jsp/login.jsp" var="previous-page"/>
    <div align="right"  >
        <select id="language" name="language" onchange="submit()" class="list">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''} class="list">English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''} class="list">Русский</option>
    </select>
    </div>
</form>

<form action="controller" method="post" name="inputform" onsubmit="return check();"><%-- --%>
    <input type="hidden" name="command" value="login"/>
    <table class="simple_table" >
        <tr>
            <td >${Login}</td>
            <td><input type="text" name="login" value="" required ="" placeholder="login"></td>
        <tr>
            <td >${Password}</td>
            <td><input type="password" name="password"  value="" required ="" placeholder="password"></td> <%-- required =""></td>--%>
        </tr>
    </table>
    <br/>
    <input name="enter" class="button" type="submit"  value="${Enter}" >
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="go-to-registr-page"/>
    <input class="button" type="submit" value="${Signup}">
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>