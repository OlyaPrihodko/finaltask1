package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.controller.ResponseParameterName;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.domain.MapBean;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UpdateOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page = JSPPageName.USER_PERSONAL_AREA_PAGE;
        DAOFactory MySQLDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        Order order = (Order)request.getSession().getAttribute(ResponseParameterName.ORDER);
        String ordId = request.getSession().getAttribute(ResponseParameterName.ORDER_ID).toString();
        int orderId = Integer.parseInt(ordId);
        order.setId(orderId);
        order.setApartmentClass(request.getParameter(RequestParameterName.APARTMENT_CLASS));
        order.setRoomNumber(Integer.parseInt(request.getParameter(RequestParameterName.ROOM_NUMBER)));
        order.setCouchette(Integer.parseInt(request.getParameter(RequestParameterName.COUCHETTE)));
        order.setDate_in(request.getParameter(RequestParameterName.DATE_IN));
        order.setDate_out(request.getParameter(RequestParameterName.DATE_OUT));

        try{
            orderDao.update(order);
            String personId = request.getSession().getAttribute(ResponseParameterName.PERSON_ID).toString();
            Map<Integer,Order> mapOrder = orderDao.getOrderMapByPersonId(Integer.parseInt(personId));
            MapBean mapBeanOrder = new MapBean(mapOrder);
            request.getSession().setAttribute(ResponseParameterName.MAP_BEAN_ORDER,mapBeanOrder);
            //как-то само обновляется и запизивается в сессию
            //request.getSession().setAttribute("user",person);//не знаю еще точно зачем это надо или не надо
            //луше надо. прокатило с обновлением заказов
        }catch (DaoException e){
            throw new ProjectException("UpdateOrderCommand has problem with dao",e);
        }
        return page;
    }
}
