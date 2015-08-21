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
<fmt:message bundle="${loc}" key="locale.message.Message8" var="Message8"/>
<fmt:message bundle="${loc}" key="locale.message.FinalTask" var="FinalTask"/>
<fmt:message bundle="${loc}" key="locale.message.Back" var="Back"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>${Message8}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
</head>
  <body>

  <c:set scope="session" value="/WEB-INF/jsp/userPages/userPersonalArea.jsp" var="previous-page"/>
  <jsp:useBean id="person" beanName="com.epam.prihodko.finaltask.entity.Person"
               type="com.epam.prihodko.finaltask.entity.Person" scope="session"/>
  <div class="container-fluid" >
      <div class="row">
          <div class="col-xs-12 col-sm-12 col-lg-offset-3 col-lg-6">
              <div class="panel panel-primary">
                  <div class="panel-heading">
                      <h2 class="panel-title text-center">
                          ${Message8}
                      </h2>
                  </div>
                  <div class="panel-body">
                      <form role="form" action="controller" method="post">
                          <input type="hidden" id="command" name="command" value=""/>
                          <div class="row">
                              <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-lg-offset-3" >
                                  <table class="table-bordered">
                                          <tr>
                                              <td><th>${Name}</th></td>
                                              <td>
                                                  <input class="input-small" type="text" name="name" value="<jsp:getProperty name="person" property="name"/>"/>
                                              </td>
                                          </tr>
                                          <tr>
                                              <td><th>${Surname}</th></td>
                                              <td>
                                                  <input class="input-small" type="text" name="surname" value="<jsp:getProperty name="person" property="surname"/>">
                                              </td>
                                          </tr>
                                          <tr>
                                              <td><th>${Email}</th></td>
                                              <td>
                                                  <input class="input-small" type="text" name="email" value="<jsp:getProperty name="person" property="email"/>" >
                                              </td>
                                          </tr>
                                          <tr>
                                              <td><th>${Phone}</th></td>
                                              <td>
                                                  <input class="input-small" type="text" name="phone" value="<jsp:getProperty name="person" property="phone"/>" >
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
                                               onclick="document.getElementById('command').value='update';">
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

