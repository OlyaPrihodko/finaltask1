package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.ResponseParameterName;
import com.epam.prihodko.finaltask.dao.domain.AccountDao;
import com.epam.prihodko.finaltask.dao.domain.CheckDao;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginCommand implements ICommand{
    public static PasswordHash passwordHash = PasswordHash.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        DAOFactory MySQLDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        AccountDao accountDao = MySQLDaoFactory.getAccountDao();
        PersonDao personDao = MySQLDaoFactory.getPersonDao();
        OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        CheckDao checkDao = MySQLDaoFactory.getCheckDao();
        String page = null;
        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);
        String pass = request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD);
        String password = passwordHash.getHash(pass);
        Account account = new Account(login,password);
        try{
            if(accountDao.checkAccount(account)){
                //request.setAttribute(ResponseParameterName.US, login);
                Person person = personDao.getByAccountId(account.getId());
                request.getSession().setAttribute(ResponseParameterName.PERSON,person);
               // request.getSession().setAttribute("user-id",person.getId());

                if(accountDao.getRole(account).equals(ResponseParameterName.USER)){
                    request.getSession().setAttribute(ResponseParameterName.USER,ResponseParameterName.USER);
                    request.getSession().setAttribute(ResponseParameterName.PERSON_ID,person.getId());
                    //получаем все заказы. связанные с пользователем
                    Map<Integer,Order> mapOrder = orderDao.getOrderMapByPersonId(person.getId());
                    MapBean mapBeanOrder = new MapBean(mapOrder);

                    Map<Integer,Check> mapCheck = new HashMap<Integer, Check>();
                    for(int i=0; i<Integer.parseInt(mapBeanOrder.getSize());i++){
                        mapBeanOrder.getElementToString();
                        int ordId = mapBeanOrder.getId();
                        Check check = checkDao.getCheckByOrderId(ordId);
                        mapCheck.put(check.getId(),check);
                    }
                    MapBean mapBeanCheck = new MapBean(mapCheck);
                    request.getSession().setAttribute(ResponseParameterName.MAP_BEAN_CHECK,mapBeanCheck);
                    request.getSession().setAttribute(ResponseParameterName.MAP_BEAN_ORDER,mapBeanOrder);
                    page = JSPPageName.USER_PERSONAL_AREA_PAGE;
                }
                else{
                    request.getSession().setAttribute(ResponseParameterName.USER,ResponseParameterName.ADMIN);
                    Map<Integer,Order> mapOrder = orderDao.getOrderMapByStatus(RequestParameterName.STATUS_NEW);
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
