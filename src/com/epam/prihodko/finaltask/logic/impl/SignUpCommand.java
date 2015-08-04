package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.dao.domain.AccountDao;
import com.epam.prihodko.finaltask.dao.domain.PersonDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.domain.Account;
import com.epam.prihodko.finaltask.domain.Person;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
    DAOFactory MySQLDaoFactory =
            DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    AccountDao accountDao = MySQLDaoFactory.getAccountDao();
    PersonDao personDao = MySQLDaoFactory.getPeronDao();
        String name = request.getParameter(RequestParameterName.PARAM_NAME_NAME);
        String surname = request.getParameter(RequestParameterName.PARAM_NAME_SURNAME);
        String email = request.getParameter(RequestParameterName.PARAM_NAME_EMAIL);
        String phone = request.getParameter(RequestParameterName.PARAM_NAME_PHONE);
        String password = request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD);

        Account account = new Account(email,password);
        Person person = new Person(name,surname,email,phone);
        try{
            accountDao.create(account);
            person.setAccountId(accountDao.getId(account));
            personDao.create(person);
            request.setAttribute("user", name);

        }catch (DaoException e){
            throw new ProjectException("problem with dao",e);
         }

    String page = JSPPageName.USER_PAGE;


    return page;
}
}
