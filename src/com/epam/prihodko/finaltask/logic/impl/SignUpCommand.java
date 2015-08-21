package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
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
import java.util.HashMap;
import java.util.Map;

public class SignUpCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
    DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    AccountDao accountDao = MySQLDaoFactory.getAccountDao();
    PersonDao personDao = MySQLDaoFactory.getPersonDao();
    OrderDao orderDao = MySQLDaoFactory.getOrderDao();
    CheckDao checkDao = MySQLDaoFactory.getCheckDao();
        String page;
        String name = request.getParameter(RequestParameterName.PARAM_NAME_NAME);
        String surname = request.getParameter(RequestParameterName.PARAM_NAME_SURNAME);
        String email = request.getParameter(RequestParameterName.PARAM_NAME_EMAIL);
        String phone = request.getParameter(RequestParameterName.PARAM_NAME_PHONE);
        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);
        String pass = (String)request.getAttribute(RequestParameterName.PARAM_NAME_PASSWORD);
        Account account = new Account(login,pass,RequestParameterName.USER);
        Person person = new Person(name,surname,email,phone);
        try{
            accountDao.create(account);
            person.setAccountId(accountDao.getId(account));
            personDao.create(person);
            person = personDao.getByAccountId(person.getAccountId());
            request.getSession().setAttribute(RequestParameterName.ROLE_USER,RequestParameterName.ROLE_USER);
            request.getSession().setAttribute(RequestParameterName.PERSON_ID,person.getId());
            request.getSession().setAttribute(RequestParameterName.PERSON,person);
            Map<Integer,Order> mapOrder = orderDao.getOrderMapByPersonId(person.getId());
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
        }catch (DaoException e){
            throw new ProjectException("SignUpCommand has problem with dao",e);

         }
    return page;
}
}
