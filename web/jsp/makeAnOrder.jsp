<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Datein" var="Datein"/>
<fmt:message bundle="${loc}" key="locale.message.Dateout" var="Dateout"/>
<fmt:message bundle="${loc}" key="locale.message.ToBookARoom" var="ToBookARoom"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>Creation an order</title>
    <link rel="stylesheet" href="css/button.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <link rel="stylesheet" href="css/list.css" type="text/css" />
</head>
<body>
<form action="controller" method="post">
<input type="hidden" id="command" name="command" value=""/>
    <div align="center" id="makeanorder">
        <table class="simple_table" >
            <tr>
                <td>apartment-class</td>
                <td>
                    <select class="list" name="apartment-class" id ="apartment-class" >
                    <option value="AC">Apartment class</option>
                    <option value="BR" onclick="document.getElementById('apartment-class').value='BR'">Bed room</option>
                    <option value="FR" onclick="document.getElementById('apartment-class').value='FR'">Family room</option>
                    <option value="HR" onclick="document.getElementById('apartment-class').value='HR'">Honeymoon room</option>
                </select>
                </td>
            </tr>
            <tr>
                <td>Room-number</td>
                <td>
                    <select class="list" name="room-number" id ="room-number">
                    <option value="0">Couchette</option>
                    <option value="1" onclick="document.getElementById('room-number').value='1'">1</option>
                    <option value="2" onclick="document.getElementById('room-number').value='2'">2</option>
                    <option value="3" onclick="document.getElementById('room-number').value='3'">3</option>
                    <option value="4" onclick="document.getElementById('room-number').value='4'">4</option>
                </select>
                </td>
            </tr>
            <tr>
                <td>couchette</td>
                <td>
                    <select class="list" name="couchette" id ="couchette">
                    <option value="0">Couchette</option>
                    <option value="1" onclick="document.getElementById('couchette').value='1'">1</option>
                    <option value="2" onclick="document.getElementById('couchette').value='2'">2</option>
                    <option value="3" onclick="document.getElementById('couchette').value='3'">3</option>
                    <option value="4" onclick="document.getElementById('couchette').value='4'">4</option>
                    <option value="5" onclick="document.getElementById('couchette').value='5'">5</option>
                    <option value="6" onclick="document.getElementById('couchette').value='6'">6</option>
                    <option value="7" onclick="document.getElementById('couchette').value='7'">7</option>
                </select>
                </td>
            </tr>
            <tr>
                <td>${Datein}</td>
                <td>${Dateout}</td>
            </tr>
            <tr>
                <td><input type="text" name="datein" placeholder="yyyy-mm-dd" value=""/></td>
                <td><input type="text" name="dateout" placeholder="yyyy-mm-dd" value=""/></td>
            </tr>
        </table>
        <input class="button" type="submit" onclick="document.getElementById('command').value='make-an-order';"
               value="${ToBookARoom}">
    </div>
</form>
</body>
</html>
