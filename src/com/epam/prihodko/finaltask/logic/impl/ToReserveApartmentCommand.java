package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.dao.entity.ApartmentClassDao;
import com.epam.prihodko.finaltask.dao.entity.ApartmentDao;
import com.epam.prihodko.finaltask.dao.entity.CheckDao;
import com.epam.prihodko.finaltask.dao.entity.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.entity.*;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ToReserveApartmentCommand implements ICommand {
    private static DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    private static OrderDao orderDao = MySQLDaoFactory.getOrderDao();
    private static CheckDao checkDao = MySQLDaoFactory.getCheckDao();
    private static ApartmentDao apartmentDao = MySQLDaoFactory.getApartmentDao();
    private static ApartmentClassDao apartmentClassDao = MySQLDaoFactory.getApartmentClassDao();
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page;
        String orderId = request.getSession().getAttribute(RequestParameterName.ORDER_ID).toString();
        int ordId = Integer.parseInt(orderId);
        String apartmentId = request.getParameter(RequestParameterName.APARTMENT_ID);
        int apId = Integer.parseInt(apartmentId);
        request.getSession().setAttribute(RequestParameterName.APARTMENT_ID, apId);
        try{
            Order order = orderDao.getById(ordId);
            order.setStatus(RequestParameterName.STATUS_ORDERED);
            Apartment apartment = apartmentDao.getById(apId);
            apartment.setStatus(RequestParameterName.APARTMENT_STATUS_NOT_AVAILABLE);
            apartmentDao.update(apartment);
            int apClId = apartment.getClassId();
            ApartmentClass apartmentClass = apartmentClassDao.getById(apClId);
            order.setRoomNumber(apartment.getRoomNumber());
            order.setCouchette(apartment.getCouchette());
            order.setApartmentClass(apartmentClass.getType());
            orderDao.update(order);
            Check check = new Check();
            check.setPrice(apartment.getPrice());
            check.setApatrmentId(apartment.getId());
            check.setOrderId(order.getId());
            checkDao.create(check);
            Map<Integer,Order> mapOrder = orderDao.getOrderMapByStatus(RequestParameterName.STATUS_NEW);
            MapBean mapBeanOrder = new MapBean(mapOrder);
            request.getSession().setAttribute(RequestParameterName.MAP_BEAN_ORDER,mapBeanOrder);
            page = JSPPageName.ADMIN_MAIN_PAGE;
        }
        catch (DaoException e){
            throw new ProjectException("ToReserveApartmentCommand has problem with dao",e);
        }
        return  page;
    }
}
