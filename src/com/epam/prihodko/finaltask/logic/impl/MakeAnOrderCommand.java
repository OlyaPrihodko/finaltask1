package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.dao.entity.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.entity.MapBean;
import com.epam.prihodko.finaltask.entity.Order;
import com.epam.prihodko.finaltask.entity.Person;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MakeAnOrderCommand implements ICommand {

    private final static String LOCALE = "com.epam.prihodko.finaltask/localization.locale";
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
    String page;
        DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        String apartmentClass = request.getParameter(RequestParameterName.APARTMENT_CLASS);
        int couchette = Integer.parseInt(request.getParameter(RequestParameterName.PARAM_NAME_COUCHETTE));
        String datein = request.getParameter(RequestParameterName.DATE_IN);
        String dateout = request.getParameter(RequestParameterName.DATE_OUT);
        int roomNumber = Integer.parseInt(request.getParameter(RequestParameterName.ROOM_NUMBER));
        Order order = new Order(apartmentClass,roomNumber,couchette,datein,dateout,RequestParameterName.STATUS_NEW);
        try{
            Person person = (Person)request.getSession().getAttribute(RequestParameterName.PERSON);
            order.setPersonId(person.getId());
            orderDao.create(order);
            Map<Integer,Order> orderMap = orderDao.getOrderMapByPersonId(person.getId());
            Iterator<Map.Entry<Integer,Order>> iterator = orderMap.entrySet().iterator();
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
            MapBean mapBeanOrder = new MapBean(orderMap);
            request.getSession().setAttribute(RequestParameterName.MAP_BEAN_ORDER,mapBeanOrder);
            page= JSPPageName.USER_PERSONAL_AREA_PAGE;
        }
        catch (DaoException e){
            throw new ProjectException("MakeAnOrderCommand has problem with dao",e);
        }
        return page;
    }
}
