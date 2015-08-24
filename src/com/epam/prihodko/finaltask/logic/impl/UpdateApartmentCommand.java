package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.dao.entity.ApartmentDao;
import com.epam.prihodko.finaltask.dao.entity.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.entity.Apartment;
import com.epam.prihodko.finaltask.entity.MapBean;
import com.epam.prihodko.finaltask.entity.Order;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class UpdateApartmentCommand implements ICommand {
    private final static DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    private final static OrderDao orderDao = MySQLDaoFactory.getOrderDao();
    private final static ApartmentDao apartmentDao = MySQLDaoFactory.getApartmentDao();
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page = JSPPageName.APARTMENT_TABLE_PAGE;
        Apartment apartment = (Apartment)request.getSession().getAttribute(RequestParameterName.APARTMENT);
        String apartmentId = request.getSession().getAttribute(RequestParameterName.APARTMENT_ID).toString();

        String apartmentCl = request.getParameter(RequestParameterName.APARTMENT_CLASS);
        String roomNumber = request.getParameter(RequestParameterName.ROOM_NUMBER);
        String couchette = request.getParameter(RequestParameterName.PARAM_NAME_COUCHETTE);
        String price = request.getParameter(RequestParameterName.PRICE);

        Object ob = request.getSession().getAttribute(RequestParameterName.LANGUAGE);
        ResourceBundle resourceBundle;
        if(!(ob instanceof Locale)){
            String lan = (String)ob;
            String language = lan.substring(0,2);
            String country = lan.substring(3,5);
            Locale locale = new Locale(language,country);
            resourceBundle = ResourceBundle.getBundle(RequestParameterName.LOCALE,locale);
        }
        else{
            resourceBundle = ResourceBundle.getBundle(RequestParameterName.LOCALE, (Locale) ob);
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(apartmentCl);
        arrayList.add(couchette);
        arrayList.add(price);
        arrayList.add(roomNumber);
        /**
         * Check empty values ↓
         * */
        for(String element: arrayList){
            if(element.equals("")){
                request.setAttribute(RequestParameterName.MISTAKE, resourceBundle.getString("locale.message.Message20"));
                return JSPPageName.CHANGE_APARTMENT_PAGE;
            }
        }
        int apId = Integer.parseInt(apartmentId);
        apartment.setId(apId);
        apartment.setClassId(Integer.parseInt(apartmentCl));
        apartment.setRoomNumber(Integer.parseInt(roomNumber));
        apartment.setCouchette(Integer.parseInt(couchette));
        try {
            if(!apartmentDao.find(apartment)){
                apartmentDao.update(apartment);
            }
            else{
                request.setAttribute(RequestParameterName.MISTAKE, resourceBundle.getString("locale.message.Message26"));
                return JSPPageName.CHANGE_APARTMENT_PAGE;
            }
            Map<Integer,Apartment>mapApartment = apartmentDao.getAllApartmentMap();
            MapBean mapBeanApartment = new MapBean(mapApartment);
            request.getSession().setAttribute(RequestParameterName.MAP_BEAN_APARTMENT, mapBeanApartment);
        }catch (DaoException e){
            throw new ProjectException("UpdateApartmentCommand has problem with dao",e);
        }
        return page;
    }
}
