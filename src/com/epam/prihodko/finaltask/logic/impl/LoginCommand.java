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
/***
 * LoginCommand checks whether the user exists in the system
 */
public class LoginCommand implements ICommand{
    private static DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    private static AccountDao accountDao = MySQLDaoFactory.getAccountDao();
    private static PersonDao personDao = MySQLDaoFactory.getPersonDao();
    private static OrderDao orderDao = MySQLDaoFactory.getOrderDao();
    private static CheckDao checkDao = MySQLDaoFactory.getCheckDao();
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page;
        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);
        String pass = (String)request.getAttribute(RequestParameterName.PARAM_NAME_PASSWORD);
        Account account = new Account(login,pass);
        try{
            /**
             * Check account. If account exists, get person by account ID
             * */
            if(accountDao.checkAccount(account)){
                Person person = personDao.getByAccountId(account.getId());
                request.getSession().setAttribute(RequestParameterName.PERSON,person);
                /**
                 * Identify the user and give information about ures's orders and checks
                 * */
                if(accountDao.getRole(account).equals(RequestParameterName.USER)){
                    request.getSession().setAttribute(RequestParameterName.ROLE_USER,RequestParameterName.ROLE_USER);
                    request.getSession().setAttribute(RequestParameterName.PERSON_ID,person.getId());
                    Map<Integer,Order> mapOrder = orderDao.getOrderMapByPersonId(person.getId());
                    MapBean mapBeanOrder = new MapBean(mapOrder);
                    Map<Integer,Check> mapCheck = new HashMap<Integer, Check>();
                    int mapBeanOrderSize = Integer.parseInt(mapBeanOrder.getSize());
                    for(int i=0; i<mapBeanOrderSize;i++){
                        mapBeanOrder.getElement();
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
                /**
                 * Identify the admin and give information about new orders
                 * */
                else{
                    request.getSession().setAttribute(RequestParameterName.ROLE_USER,RequestParameterName.ROLE_ADMIN);
                    Map<Integer,Order> mapOrder = orderDao.getOrderMapByStatus(RequestParameterName.STATUS_NEW);
                    MapBean mapBeanOrder = new MapBean(mapOrder);
                    request.getSession().setAttribute(RequestParameterName.MAP_BEAN_ORDER,mapBeanOrder);
                    page = JSPPageName.ADMIN_MAIN_PAGE;
                }
            }
            /**
             * If account doesn't exist, message is sent that the data is entered incorrectly
             * */
            else{
                page = JSPPageName.LOGIN_PAGE;
                Object ob = request.getSession().getAttribute(RequestParameterName.LANGUAGE);
                ResourceBundle resourceBundle;
                if(!(ob instanceof Locale)){
                    String lan = (String)ob;
                    String language = lan.substring(0,2);
                    String country = lan.substring(3, 5);
                    Locale locale = new Locale(language,country);
                    resourceBundle = ResourceBundle.getBundle(RequestParameterName.LOCALE, locale);
                }
                else{
                    resourceBundle = ResourceBundle.getBundle(RequestParameterName.LOCALE, (Locale) ob);
                }
                request.setAttribute(RequestParameterName.MISTAKE, resourceBundle.getString("locale.message.Message19"));
            }
        }catch (DaoException e){
            throw new ProjectException("problem with dao",e);
        }
        return page;
    }
}
/*
Удалять все заказы, дата выезда которых прошла

java.sql.Date date = new Date(new java.util.Date().getTime());
                    if(ord.getDate_out().compareTo(date)>=0){
                        checkDao.deleteByOrderId();

                    }
 */