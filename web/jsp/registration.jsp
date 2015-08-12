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
<fmt:message bundle="${loc}" key="locale.message.Back" var="Back"/>
<fmt:message bundle="${loc}" key="locale.message.Message1" var="Message1"/>
<fmt:message bundle="${loc}" key="locale.message.Message3" var="Message3"/>
<fmt:message bundle="${loc}" key="locale.message.Message4" var="Message4"/>
<fmt:message bundle="${loc}" key="locale.message.Message5" var="Message5"/>

<html lang="${language}">
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="css/button.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <script type="text/javascript">
        <!--
        function check(){
            f_name=document.inputform.name.value.toString();
            if (f_name==''){
                alert('${Message4} "${Name}"');
                document.inputform.name.focus();
               // document.inputform.name.required="required";
                return false;
            }else{
                if(f_name.length<2){
                    alert('${Message3}');
                    document.inputform.name.focus();
                 //   document.inputform.name.required="required";
                    return false;
                }
            }
            //-------------------------------------------------------------------
            f_surname=document.inputform.surname.value.toString();
             if (f_surname==''){
                alert('${Message4} "${Surname}"');
                document.inputform.surname.focus();
                return false;
             }else{
                 if(f_surname.length<2){
                     alert('${Message3}');
                     document.inputform.surname.focus();
                     return false;
                 }
             }
            f_email=document.inputform.email.value.toString();
            if (f_email==''){
                alert('${Message4} "${Email}"');
                document.inputform.email.focus();
                return false;
            }else{
                dog=f_email.indexOf('@');
                if ((f_email.indexOf('.')==-1) || (dog==-1) || (dog < 1) ||
                        (dog > f_email.length - 5) || (f_email.charAt(dog - 1)=='.') || (f_email.charAt(dog + 1)=='.')){
                    alert('${Message5}');
                    document.inputform.email.focus();
                    return false;
                }
            }
            f_phone=document.inputform.phone.value.toString();
            if (f_phone==''){
                alert('${Message4} "${Phone}"');
                document.inputform.phone.focus();
                return false;
            }else{
                if(f_phone.length<6){
                    alert('${Message3}');
                    document.inputform.phone.focus();
                    return false;
                }
            }
            f_login=document.inputform.login.value.toString();
            if (f_login==''){
                alert('${Message4} "${Login}"');
                document.inputform.login.focus();
                return false;
            }else{
                if(f_login.length<2){
                    alert('${Message3}');
                    document.inputform.login.focus();
                    return false;
                }
            }
            f_password=document.inputform.password.value.toString();
            if (f_password==''){
                alert('${Message4} "${Password}"');
                document.inputform.password.focus();
                return false;
            }else{
                if(f_password.length<4){
                    alert('${Message3}');
                    document.inputform.password.focus();
                    return false;
                }
            }
        }
        //-->
    </script>

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
        <td><input type="text" name="name" value=""></td>
    </tr>
    <tr>
        <td >${Surname}</td>
        <td><input type="text" name="surname"  value=""></td>
    </tr>
    <tr>
        <td>${Email}</td>
        <td><input type="text" name="email"  value="" ></td>
    </tr>
    <tr>
        <td >${Phone}</td>
        <td><input type="text" name="phone"  value="" ></td>
    </tr>
    <tr>
        <td >${Login}</td>
        <td><input type="text" name="login"  value="" ></td>
    </tr>
    <tr>
        <td >${Password}</td>
        <td><input type="password" name="password"  value="" ></td>
    </tr>
</table>
    </div>
<br/>
    <c:set scope="session" value="/jsp/registration.jsp" var="previous-page"/>
    <div align="center">
    <input class="button" name ="registration" type="submit" onclick="document.getElementById('command').value='signup';"
       value="${Signup}">
        <br/>
        <input class="button"  type="submit" onclick="document.getElementById('command').value='previous-page';"
               value="${Back}">
        </div>
<br/>

</form>
<%--<a href="/jsp/login.jsp">${Back}</a>--%>
</body>
</html>
