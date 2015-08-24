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
import java.util.*;
/**
 * FindApartmentCommand find available apartments:
 *
 * */
public class FindApartmentCommand implements ICommand {
    private static DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    private static OrderDao orderDao = MySQLDaoFactory.getOrderDao();
    private static CheckDao checkDao = MySQLDaoFactory.getCheckDao();
    private static ApartmentClassDao apartmentClassDao = MySQLDaoFactory.getApartmentClassDao();
    private static ApartmentDao apartmentDao = MySQLDaoFactory.getApartmentDao();
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page;
        String ordId = request.getParameter(RequestParameterName.ORDER_ID);
        int orderId = Integer.parseInt(ordId);
        request.getSession().setAttribute(RequestParameterName.ORDER_ID,orderId);
        try{
            Order order = orderDao.getById(orderId);
            ApartmentClass apartmentClass = new ApartmentClass();
            apartmentClass.setType(order.getApartmentClass());
            Map<Integer,Order> mapOrder = new HashMap<Integer, Order>();
            mapOrder.put(order.getId(),order);
            MapBean mapBeanOrder = new MapBean(mapOrder);
            request.getSession().setAttribute(RequestParameterName.MAP_BEAN_ORDER,mapBeanOrder);
            Apartment apartment = new Apartment();
            apartment.setRoomNumber(order.getRoomNumber());
            apartment.setCouchette(order.getCouchette());
            int apClassId = apartmentClassDao.findIdByType(apartmentClass);
            apartment.setClassId(apClassId);
            Map<Integer,Apartment> mapApartment = apartmentDao.getSuitableApartmentMap(apartment);
            for (Map.Entry<Integer, Apartment> m : mapApartment.entrySet()) {
                Apartment apartment1 = m.getValue();
                if(apartment1.getStatus().equals(RequestParameterName.APARTMENT_STATUS_AVAILABLE)){
                   apartment1.setStatus(RequestParameterName.APARTMENT_STATUS_NOT_AVAILABLE);
                   m.setValue(apartment1);
                }
                else if (apartment1.getStatus().equals(RequestParameterName.APARTMENT_STATUS_NOT_AVAILABLE)){
                    Map<Date,Date> dates = new TreeMap<Date,Date>();
                    Map<Integer,Check> mapCheck = checkDao.getCheckMapByApartmentId(apartment1.getId());
                    for (Map.Entry<Integer, Check> c : mapCheck.entrySet()) {
                        Check check = c.getValue();
                        Order ord = orderDao.getById(check.getOrderId());
                        dates.put(ord.getDate_in(),ord.getDate_out());
                    }
                    Iterator<Map.Entry<Date,Date>> iterator = dates.entrySet().iterator();
                    int fl=0;
                    while(iterator.hasNext()){
                        Map.Entry<Date,Date> ch = iterator.next();
                        if(dates.size()>1){
                            if(!(fl==0||fl==dates.size()-1)){
                                if(!(order.getDate_in().after(ch.getValue())&&order.getDate_out().before(iterator.next().getKey()))){
                                    mapApartment.remove(m.getKey());
                                }
                            }
                            else{
                                if(!(order.getDate_out().before(ch.getValue())||order.getDate_in().after(ch.getKey()))){
                                    mapApartment.remove(m.getKey());
                                }
                            }
                        }
                        else {
                            if(!(order.getDate_out().before(ch.getKey())||order.getDate_in().after(ch.getValue()))){
                                mapApartment.remove(m.getKey());
                            }
                        }
                        fl++;
                    }
                }
            }
            MapBean mapBeanApartment = new MapBean(mapApartment);
            request.getSession().setAttribute(RequestParameterName.MAP_BEAN_APARTMENT,mapBeanApartment);
            page= JSPPageName.AVAILABLE_APARTMENT_PAGE;
        }catch (DaoException e){
            throw new ProjectException("FindApartmentCommand has problem with dao",e);
        }
        return page;
    }
}