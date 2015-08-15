package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.ResponseParameterName;
import com.epam.prihodko.finaltask.dao.domain.AccountDao;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.dao.domain.PersonDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.domain.*;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;
import javax.servlet.http.HttpServletRequest;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.logic.PasswordHash;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class LoginCommand implements ICommand{
    public static PasswordHash passwordHash = PasswordHash.getInstance();
    private final static String ROLE_USER = "user";
    private final static String STATUS_NEW = "new";
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        DAOFactory MySQLDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        AccountDao accountDao = MySQLDaoFactory.getAccountDao();
        PersonDao personDao = MySQLDaoFactory.getPersonDao();
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        String page = null;
        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);
        String pass = request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD);
        String password = passwordHash.getHash(pass);
        Account account = new Account(login,password);
        try{
            if(accountDao.checkAccount(account)){
                //request.setAttribute(ResponseParameterName.US, login);
                Person person = personDao.getByAccountId(account.getId());
                request.getSession().setAttribute(ResponseParameterName.USER,person);
               // request.getSession().setAttribute("user-id",person.getId());
                if(accountDao.getRole(account).equals(ROLE_USER)){
                   /* Set<Order> orderSet = orderDao.getOrderSetByPersonId(person.getId());
                    SetBean setBeanOrder = new SetBean(orderSet);
                    request.getSession().setAttribute(ResponseParameterName.SET_BEAN_ORDER,setBeanOrder);
                    */
                    request.getSession().setAttribute(ResponseParameterName.PERSON_ID,person.getId());
                    Map<Integer,Order> mapOrder = orderDao.getOrderMapByPersonId(person.getId());
                    MapBean mapBeanOrder = new MapBean(mapOrder);
                    request.getSession().setAttribute(ResponseParameterName.MAP_BEAN_ORDER,mapBeanOrder);
                    page = JSPPageName.USER_PERSONAL_AREA_PAGE;
                }
                else{
                    /*Set<Order> orderSet = orderDao.getOrderSetByStatus(STATUS_NEW);
                    SetBean setBeanOrder = new SetBean(orderSet);
                    request.getSession().setAttribute(ResponseParameterName.SET_BEAN_ORDER,setBeanOrder);
                    */
                    Map<Integer,Order> mapOrder = orderDao.getOrderMapByStatus(STATUS_NEW);
                    MapBean mapBeanOrder = new MapBean(mapOrder);
                    request.getSession().setAttribute(ResponseParameterName.MAP_BEAN_ORDER,mapBeanOrder);
                    page = JSPPageName.ADMIN_MAIN_PAGE;
                }
        }else{
                page = JSPPageName.LOGIN_PAGE;
                /*тут бы еще какое-то сообщение о том, что неправильно введен логин и/или пароль*/
            }
        }catch (DaoException e){
            throw new ProjectException("problem with dao",e);
        }
        return page;
    }
}
