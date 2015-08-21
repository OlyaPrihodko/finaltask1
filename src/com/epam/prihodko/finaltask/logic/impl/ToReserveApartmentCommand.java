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
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ToReserveApartmentCommand implements ICommand {
    private final static String LOCALE = "com.epam.prihodko.finaltask/localization.locale";
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page;
        DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        CheckDao checkDao = MySQLDaoFactory.getCheckDao();
        ApartmentDao apartmentDao = MySQLDaoFactory.getApartmentDao();
        ApartmentClassDao apartmentClassDao = MySQLDaoFactory.getApartmentClassDao();
        String orderId = request.getSession().getAttribute(RequestParameterName.ORDER_ID).toString();
        int ordId = Integer.parseInt(orderId);
        String apartmentId = request.getParameter(RequestParameterName.APARTMENT_ID);
        int apId = Integer.parseInt(apartmentId);
        request.getSession().setAttribute(RequestParameterName.APARTMENT_ID,apId);
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
            Iterator<Map.Entry<Integer,Order>> iterator = mapOrder.entrySet().iterator();
            Object ob = request.getSession().getAttribute(RequestParameterName.LANGUAGE);
            ResourceBundle resourceBundle = null;
            if(!(ob instanceof Locale)){
                String lan = (String)ob;
                String language = lan.substring(0,2);
                String country = lan.substring(3,5);
                Locale locale = new Locale(language,country);
                resourceBundle =ResourceBundle.getBundle(LOCALE,locale);
            }
            else{
                resourceBundle = ResourceBundle.getBundle(LOCALE, (Locale) ob);
            }

            while(iterator.hasNext()){
                Map.Entry<Integer,Order> m = iterator.next();
                Order ord = m.getValue();
                String apartmentCl = ord.getApartmentClass();
                String status = ord.getStatus();
                if (apartmentCl.equals(RequestParameterName.BEDROOM)) {
                    if (status.equals(RequestParameterName.STATUS_NEW)) {
                        if (ob.toString().equals("en_EN")) {
                            ord.setApartmentClass(resourceBundle.getString("locale.message.ApartmentClass1"));
                            ord.setStatus(resourceBundle.getString("locale.message.StatusNew"));
                            m.setValue(ord);
                        }

                    } else if (status.equals(RequestParameterName.STATUS_ORDERED)) {
                        if (ob.toString().equals("en_EN")) {
                            ord.setApartmentClass(resourceBundle.getString("locale.message.ApartmentClass1"));
                            ord.setStatus(resourceBundle.getString("locale.message.StatusOrdered"));
                            m.setValue(ord);
                        }

                    }

                } else if (apartmentCl.equals(RequestParameterName.FAMILYROON)) {
                    if (status.equals(RequestParameterName.STATUS_NEW)) {
                        if (ob.toString().equals("en_EN")) {
                            ord.setApartmentClass(resourceBundle.getString("locale.message.ApartmentClass2"));
                            ord.setStatus(resourceBundle.getString("locale.message.StatusNew"));
                            m.setValue(ord);
                        }

                    } else if (status.equals(RequestParameterName.STATUS_ORDERED)) {
                        if (ob.toString().equals("en_EN")) {
                            ord.setApartmentClass(resourceBundle.getString("locale.message.ApartmentClass2"));
                            ord.setStatus(resourceBundle.getString("locale.message.StatusOrdered"));
                            m.setValue(ord);
                        }

                    }

                } else if (apartmentCl.equals(RequestParameterName.HONEYMOONROOM)) {
                    if (status.equals(RequestParameterName.STATUS_NEW)) {
                        if (ob.toString().equals("en_EN")) {
                            ord.setApartmentClass(resourceBundle.getString("locale.message.ApartmentClass3"));
                            ord.setStatus(resourceBundle.getString("locale.message.StatusNew"));
                            m.setValue(ord);
                        }

                    } else if (status.equals(RequestParameterName.STATUS_ORDERED)) {
                        if (ob.toString().equals("en_EN")) {
                            ord.setApartmentClass(resourceBundle.getString("locale.message.ApartmentClass3"));
                            ord.setStatus(resourceBundle.getString("locale.message.StatusOrdered"));
                            m.setValue(ord);
                        }
                    }

                }
            }
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
