package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.dao.entity.AccountDao;
import com.epam.prihodko.finaltask.dao.entity.CheckDao;
import com.epam.prihodko.finaltask.dao.entity.OrderDao;
import com.epam.prihodko.finaltask.dao.entity.PersonDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.entity.*;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;
import javax.servlet.http.HttpServletRequest;
import com.epam.prihodko.finaltask.controller.RequestParameterName;

import java.util.*;

public class LoginCommand implements ICommand{
    private final static String LOCALE = "com.epam.prihodko.finaltask/localization.locale";

    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        AccountDao accountDao = MySQLDaoFactory.getAccountDao();
        PersonDao personDao = MySQLDaoFactory.getPersonDao();
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        CheckDao checkDao = MySQLDaoFactory.getCheckDao();
        String page;
        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);
        String pass = (String)request.getAttribute(RequestParameterName.PARAM_NAME_PASSWORD);
        //String password = passwordHash.getHash(pass);
        Account account = new Account(login,pass);
        try{
            if(accountDao.checkAccount(account)){
                Person person = personDao.getByAccountId(account.getId());
                request.getSession().setAttribute(RequestParameterName.PERSON,person);

                if(accountDao.getRole(account).equals(RequestParameterName.USER)){
                    request.getSession().setAttribute(RequestParameterName.ROLE_USER,RequestParameterName.ROLE_USER);
                    request.getSession().setAttribute(RequestParameterName.PERSON_ID,person.getId());
                    Map<Integer,Order> mapOrder = orderDao.getOrderMapByPersonId(person.getId());
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
                    Map<Integer,Check> mapCheck = new HashMap<Integer, Check>();
                    int mapBeanOrderSize = Integer.parseInt(mapBeanOrder.getSize());
                    for(int i=0; i<mapBeanOrderSize;i++){
                        mapBeanOrder.getElementToString();
                        int ordId = mapBeanOrder.getId();
                        Check check = checkDao.getCheckByOrderId(ordId);
                        if(check!=null){
                        mapCheck.put(check.getId(),check);
                        }
                    }
                    MapBean mapBeanCheck = new MapBean(mapCheck);
                    request.getSession().setAttribute(RequestParameterName.MAP_BEAN_CHECK,mapBeanCheck);
                    request.getSession().setAttribute(RequestParameterName.MAP_BEAN_ORDER,mapBeanOrder);
                    page = JSPPageName.USER_PERSONAL_AREA_PAGE;
                }
                else{
                    request.getSession().setAttribute(RequestParameterName.ROLE_USER,RequestParameterName.ROLE_ADMIN);
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
        }else{
                page = JSPPageName.LOGIN_PAGE;
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
                request.setAttribute(RequestParameterName.MISTAKE, resourceBundle.getString("locale.message.Message19"));
            }
        }catch (DaoException e){
            throw new ProjectException("problem with dao",e);
        }
        return page;
    }
}
