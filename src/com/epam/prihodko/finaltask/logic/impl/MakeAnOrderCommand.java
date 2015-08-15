package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.controller.ResponseParameterName;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.domain.MapBean;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.domain.Person;
import com.epam.prihodko.finaltask.domain.SetBean;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;


public class MakeAnOrderCommand implements ICommand {
private static final String STATUS = "new";
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
    String page = null;
        DAOFactory MySQLDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        String apartmentClass = request.getParameter(RequestParameterName.APARTMENT_CLASS);
        int couchette = Integer.parseInt(request.getParameter(RequestParameterName.COUCHETTE));
        String datein = request.getParameter(RequestParameterName.DATE_IN);
        String dateout = request.getParameter(RequestParameterName.DATE_OUT);
        int roomNumber = Integer.parseInt(request.getParameter(RequestParameterName.ROOM_NUMBER));

        Order order = new Order(apartmentClass,roomNumber,couchette,datein,dateout,STATUS);
        try{
            Person person = (Person)request.getSession().getAttribute("user");
            order.setPersonId(person.getId());
            orderDao.create(order);
            //чтоб обновлялась таблица списка заказов
            Map orderMap = orderDao.getOrderMapByPersonId(person.getId());
            MapBean setBeanOrder = new MapBean(orderMap);
            request.getSession().setAttribute(ResponseParameterName.MAP_BEAN_ORDER,setBeanOrder);
            page= JSPPageName.USER_PERSONAL_AREA_PAGE;
        }
        catch (DaoException e){
            throw new ProjectException("MakeAnOrderCommand has problem with dao",e);
        }
        return page;
    }
}
