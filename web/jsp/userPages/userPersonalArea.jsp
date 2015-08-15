<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="customtags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Logout" var="Logout"/>
<fmt:message bundle="${loc}" key="locale.message.PersonalData" var="PersonalData"/>
<fmt:message bundle="${loc}" key="locale.message.ToOrder" var="ToOrder"/>
<fmt:message bundle="${loc}" key="locale.message.FinalTask" var="FinalTask"/>
<fmt:message bundle="${loc}" key="locale.message.Message6" var="Message6"/>
<fmt:message bundle="${loc}" key="locale.message.Message7" var="Message7"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>Personal Area</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<c:set scope="session" value="/jsp/login.jsp" var="previous-page"/>
<jsp:useBean id="mapBeanOrder" beanName="com.epam.prihodko.finaltask.domain.MapBean"
             type="com.epam.prihodko.finaltask.domain.MapBean" scope="session"/>
<div class="container-fluid" >
<div class="row">
    <div class="col-xs-12 col-sm-12 col-lg-offset-3 col-lg-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h2 class="panel-title text-center">
                    ${Message7}
                </h2>
            </div>
            <div class="panel-body">
                <form role="form" action="controller" method="post">
                    <input type="hidden" id="command" name="command" value=""/>
                    <input type="hidden" id="orderId" name="orderId" value=""/>
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-10  col-lg-offset-2" >
                                <div class="btn-group">
                                    <button  type="submit" class="btn btn-labeled btn-primary"
                                             onclick="document.getElementById('command').value='go-to-user-personal-data-page';">
                                        <span class="btn-label" ><i class="fa fa-edit"></i>
                                         </span> ${PersonalData}
                                    </button>
                                    <button  type="submit" class="btn btn-labeled btn-primary"
                                             onclick="document.getElementById('command').value='go-to-make-an-order-page';">
                                        <span class="btn-label" ><i class="fa fa-plus"></i>
                                         </span> ${ToOrder}
                                    </button>
                                    <button  type="submit" class="btn btn-labeled btn-danger"
                                             onclick="document.getElementById('command').value='logout';">
                                        <span class="btn-label" ><i class="fa fa-sign-out"></i>
                                        </span> ${Logout}
                                    </button>
                                </div>
                            <%--</form> --%>
                        </div>
                    </div>
                    <div class="row text-center">
                    <h3>${Message6}</h3>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-12">
                            <mytag:maptag map="${mapBeanOrder}" lang="${language}" />
                        </div>
                    </div>
                </form>
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













<%--<h1>Welcome</h1>
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
--%>