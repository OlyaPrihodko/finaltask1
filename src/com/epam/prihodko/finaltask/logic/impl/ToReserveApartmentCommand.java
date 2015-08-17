package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.controller.ResponseParameterName;
import com.epam.prihodko.finaltask.dao.domain.ApartmentDao;
import com.epam.prihodko.finaltask.dao.domain.CheckDao;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.domain.Apartment;
import com.epam.prihodko.finaltask.domain.Check;
import com.epam.prihodko.finaltask.domain.MapBean;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
public class ToReserveApartmentCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page;
        DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        CheckDao checkDao = MySQLDaoFactory.getCheckDao();
        ApartmentDao apartmentDao = MySQLDaoFactory.getApartmentDao();
        String orderId = request.getSession().getAttribute(ResponseParameterName.ORDER_ID).toString();
        int ordId = Integer.parseInt(orderId);
        String apartmentId = request.getParameter(ResponseParameterName.APARTMENT_ID);
        int apId = Integer.parseInt(apartmentId);
        request.getSession().setAttribute(ResponseParameterName.APARTMENT_ID,apId);//на всякий запихиваю apartmentId
        //из реквеста в сессию. может пригодится
        try{
            Order order = orderDao.getById(ordId);
            order.setStatus(RequestParameterName.STATUS_ORDERED);
            orderDao.update(order);//изменени заказа (должно рабоать, т.к. прописано уже при изменени заказа пользователем)
            Apartment apartment = apartmentDao.getById(apId);
            apartment.setStatus(RequestParameterName.APARTMENT_STATUS_NOT_AVAILABLE);
            apartmentDao.update(apartment);
            Check check = new Check();
            check.setPrice(apartment.getPrice());
            check.setApatrmentId(apartment.getId());
            check.setOrderId(order.getId());
            checkDao.create(check);
            //может надо ид чека в сессию???
            Map<Integer,Order> mapOrder = orderDao.getOrderMapByStatus(RequestParameterName.STATUS_NEW);
            MapBean mapBeanOrder = new MapBean(mapOrder);
            request.getSession().setAttribute(ResponseParameterName.MAP_BEAN_ORDER,mapBeanOrder);
            page = JSPPageName.ADMIN_MAIN_PAGE;
        }
        catch (DaoException e){
            throw new ProjectException("ToReserveApartmentCommand has problem with dao",e);
        }
        return  page;
    }
}
