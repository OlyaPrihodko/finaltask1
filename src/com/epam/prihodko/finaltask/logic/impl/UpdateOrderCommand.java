package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.dao.entity.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.entity.MapBean;
import com.epam.prihodko.finaltask.entity.Order;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UpdateOrderCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page = JSPPageName.USER_PERSONAL_AREA_PAGE;
        DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        Order order = (Order)request.getSession().getAttribute(RequestParameterName.ORDER);
        String ordId = request.getSession().getAttribute(RequestParameterName.ORDER_ID).toString();
        int orderId = Integer.parseInt(ordId);
        order.setId(orderId);
        order.setApartmentClass(request.getParameter(RequestParameterName.APARTMENT_CLASS));
        order.setRoomNumber(Integer.parseInt(request.getParameter(RequestParameterName.ROOM_NUMBER)));
        order.setCouchette(Integer.parseInt(request.getParameter(RequestParameterName.PARAM_NAME_COUCHETTE)));
        order.setDate_in(request.getParameter(RequestParameterName.DATE_IN));
        order.setDate_out(request.getParameter(RequestParameterName.DATE_OUT));
        try{
            orderDao.update(order);
            String personId = request.getSession().getAttribute(RequestParameterName.PERSON_ID).toString();
            Map<Integer,Order> mapOrder = orderDao.getOrderMapByPersonId(Integer.parseInt(personId));
            MapBean mapBeanOrder = new MapBean(mapOrder);
            request.getSession().setAttribute(RequestParameterName.MAP_BEAN_ORDER,mapBeanOrder);
        }catch (DaoException e){
            throw new ProjectException("UpdateOrderCommand has problem with dao",e);
        }

        return page;
    }
}
