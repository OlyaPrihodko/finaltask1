<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Back" var="Back"/>

<fmt:message bundle="${loc}" key="locale.message.ApartmentClass" var="ApartmentClass"/>
<fmt:message bundle="${loc}" key="locale.message.RoomNumber" var="RoomNumber"/>
<fmt:message bundle="${loc}" key="locale.message.Couchette" var="Couchette"/>
<fmt:message bundle="${loc}" key="locale.message.Datein" var="Datein"/>
<fmt:message bundle="${loc}" key="locale.message.Dateout" var="Dateout"/>
<fmt:message bundle="${loc}" key="locale.message.FinalTask" var="FinalTask"/>
<fmt:message bundle="${loc}" key="locale.message.Message12" var="Message12"/>
<fmt:message bundle="${loc}" key="locale.message.Update" var="Update"/>
<fmt:message bundle="${loc}" key="locale.message.Logout" var="Logout"/>
<html lang="${language}">
<head>
    <title>${Message12}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<c:set scope="session" value="/WEB-INF/jsp/userPages/userPersonalArea.jsp" var="previous-page"/>

<jsp:useBean id="order" beanName="com.epam.prihodko.finaltask.entity.Order"
             type="com.epam.prihodko.finaltask.entity.Order" scope="session"/>
<div class="container-fluid" >
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-lg-offset-3 col-lg-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title text-center">
                        ${Message12}
                    </h2>
                </div>
                <div class="panel-body">
                    <form role="form" action="controller" method="post">
                        <input type="hidden" id="command" name="command" value=""/>
                        <input type="hidden" id="orderId" name="orderId" value=""/>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 col-lg-offset-2" >
                                <table class="table table-bordered">
                                    <tr>
                                        <td><th>${ApartmentClass}</th></td>
                                        <td>
                                            <input class="input-small" type="text" name="apartment-class" value="<jsp:getProperty name="order" property="apartmentClass"/>"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><th>${RoomNumber}</th></td>
                                        <td>
                                            <input class="input-small" type="text" name="room-number" value="<jsp:getProperty name="order" property="roomNumber"/>">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><th>${Couchette}</th></td>
                                        <td>
                                            <input class="input-small" type="text" name="couchette" value="<jsp:getProperty name="order" property="couchette"/>" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><th>${Datein}</th></td>
                                        <td>
                                            <input class="input-small" type="text" name="datein" value="<jsp:getProperty name="order" property="date_in"/>" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><th>${Dateout}</th></td>
                                        <td>
                                            <input class="input-small" type="text" name="dateout" value="<jsp:getProperty name="order" property="date_out"/>" >
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8  col-lg-offset-3" >
                                <br/>
                                <div class="btn-group">
                                    <button  type="submit" class="btn btn-labeled btn-primary"
                                             onclick="document.getElementById('command').value='update-order';">
                                      <span class="btn-label" ><i class="fa fa-edit"></i>
                                      </span> ${Update}
                                    </button>
                                    <button  type="submit" class="btn btn-labeled btn-danger"
                                             onclick="document.getElementById('command').value='logout';">
                                      <span class="btn-label" ><i class="fa fa-sign-out"></i>
                                      </span> ${Logout}
                                    </button>
                                    <button  type="submit" class="btn btn-labeled btn-primary"
                                             onclick="document.getElementById('command').value='previous-page';">
                                      <span class="btn-label" ><i class="fa fa-arrow-left"></i>
                                      </span> ${Back}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <%@include file="/WEB-INF/jsp/footerPart.jsp"%>
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