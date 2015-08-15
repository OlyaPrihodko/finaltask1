<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Welcome" var="Welcome"/>
<fmt:message bundle="${loc}" key="locale.message.Login" var="Login"/>
<fmt:message bundle="${loc}" key="locale.message.Password" var="Password"/>
<fmt:message bundle="${loc}" key="locale.message.Signin" var="Signin"/>
<fmt:message bundle="${loc}" key="locale.message.Signup" var="Signup"/>
<fmt:message bundle="${loc}" key="locale.message.FinalTask" var="FinalTask"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>Log in</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">

</head>
<body>
<c:set scope="session" value="/jsp/login.jsp" var="previous-page"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-lg-offset-3 col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <div class="row">
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-5 col-lg-offset-1" >
                                    ${Welcome} <i class="fa fa-smile-o"></i>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3 col-lg-offset-2" >
                                    <form role="form">
                                        <select class="form-control" id="language" name="language" onchange="submit()">
                                            <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''} >English</option>
                                            <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''} >Русский</option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <form role="form" action="controller" method="post">
                                <input type="hidden" id="command" name="command" value=""/>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 login-box ">
                                    <div class="input-group">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-user">
                                                    </span>
                                                </span>
                                        <input type="text" id="login"name="login" value="" class="form-control" placeholder="${Login}" autofocus/>
                                    </div>
                                    <div class="input-group">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-lock">
                                                    </span>
                                                </span>
                                        <input type="password" id="password" name="password" value="" class="form-control" placeholder="${Password}" />
                                    </div>
                                </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-offset-2 col-lg-4">
                                <div class="btn-group-vertical">
                                    <button type="submit" class="btn btn-labeled btn-success"
                                            onclick="document.getElementById('login').required='required';document.getElementById('password').required='required';
                                                     document.getElementById('command').value='login';">
                                        <span class="btn-label" type="submit"><i class="fa fa-sign-in" style="font-size: 120%"></i></span> ${Signin}
                                     </button>
                                    <button  type="submit" class="btn btn-labeled btn-primary"
                                             onclick="document.getElementById('login').required='';document.getElementById('password').required='';
                                             document.getElementById('command').value='go-to-registr-page';">
                                        <span class="btn-label" ><i class="fa fa-user-plus"></i></span> ${Signup}
                                    </button>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-lg-offset-3 col-lg-6">
                                <h5 class="text-center">${FinalTask}</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>