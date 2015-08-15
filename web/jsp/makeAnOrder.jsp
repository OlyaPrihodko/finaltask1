<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/com.epam.prihodko.finaltask/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.OrderDatein" var="Datein"/>
<fmt:message bundle="${loc}" key="locale.message.OrderDateout" var="Dateout"/>
<fmt:message bundle="${loc}" key="locale.message.OrderApartmentClass" var="ApartmentClass"/>
<fmt:message bundle="${loc}" key="locale.message.OrderRoomNumber" var="RoomNumber"/>
<fmt:message bundle="${loc}" key="locale.message.OrderCouchette" var="Couchette"/>
<fmt:message bundle="${loc}" key="locale.message.ToOrder" var="ToOrder"/>
<fmt:message bundle="${loc}" key="locale.message.DateFormat" var="DateFormat"/>
<fmt:message bundle="${loc}" key="locale.message.Back" var="Back"/>
<fmt:message bundle="${loc}" key="locale.message.FinalTask" var="FinalTask"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>Creation an order</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<c:set scope="session" value="/jsp/userPages/userPersonalArea.jsp" var="previous-page"/>
<div class="container-fluid" >
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-lg-offset-3 col-lg-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title text-center">
                        New order
                    </h2>
                </div>
                <div class="panel-body">
                    <form role="form" action="controller" method="post">
                        <input type="hidden" id="command" name="command" value=""/>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-8 col-lg-8 col-lg-offset-2">
                                <table class="table-bordered">
                                    <tr>
                                        <td><th>${ApartmentClass}</th></td>
                                        <td>
                                            <select class="form-control" name="apartment-class" id ="apartment-class" >
                                                <option value="BedRoom" onclick="document.getElementById('apartment-class').value='BR'">Bed room</option>
                                                <option value="FamilyRoom" onclick="document.getElementById('apartment-class').value='FR'">Family room</option>
                                                <option value="HoneyRoom" onclick="document.getElementById('apartment-class').value='HR'">Honeymoon room</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><th>${RoomNumber}</th></td>
                                        <td>
                                            <select class="form-control" name="room-number" id ="room-number">
                                                <option value="1" onclick="document.getElementById('room-number').value='1'">1</option>
                                                <option value="2" onclick="document.getElementById('room-number').value='2'">2</option>
                                                <option value="3" onclick="document.getElementById('room-number').value='3'">3</option>
                                                <option value="4" onclick="document.getElementById('room-number').value='4'">4</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><th>${Couchette}</th></td>
                                        <td>
                                            <select class="form-control" name="couchette" id ="couchette">
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
                                        <td><th>${Datein}</th></td>
                                        <td>
                                            <input id="datein" type="text" name="datein" placeholder="${DateFormat}" value="" required=""/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><th>${Dateout}</th></td>
                                        <td>
                                            <input id="dateout" type="text" name="dateout" placeholder="${DateFormat}" value="" required=""/>
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
                                             onclick="document.getElementById('command').value='make-an-order';
                                                    document.getElementById('datein').required='required';
                                                    document.getElementById('dateout').required='required';">
                                      <span class="btn-label" ><i class="fa fa-edit"></i>
                                      </span> ${ToOrder}
                                    </button>
                                    <button  type="submit" class="btn btn-labeled btn-primary"
                                             onclick="document.getElementById('command').value='previous-page';
                                             document.getElementById('datein').required='';
                                             document.getElementById('dateout').required='';">
                                      <span class="btn-label" ><i class="fa fa-arrow-left"></i>
                                      </span> ${Back}
                                    </button>
                                </div>
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
</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
<%--


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
                    <select class="form-control" name="couchette" id ="couchette">
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
--%>