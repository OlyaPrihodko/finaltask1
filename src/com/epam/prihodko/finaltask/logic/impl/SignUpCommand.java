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
import com.epam.prihodko.finaltask.logic.PasswordHash;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements ICommand {
    private final static String ROLE_USER = "user";
    public static PasswordHash passwordHash = PasswordHash.getInstance();
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
    DAOFactory MySQLDaoFactory =
            DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    AccountDao accountDao = MySQLDaoFactory.getAccountDao();
    PersonDao personDao = MySQLDaoFactory.getPersonDao();
        String page = null;
        String name = request.getParameter(RequestParameterName.PARAM_NAME_NAME);
        String surname = request.getParameter(RequestParameterName.PARAM_NAME_SURNAME);
        String email = request.getParameter(RequestParameterName.PARAM_NAME_EMAIL);
        String phone = request.getParameter(RequestParameterName.PARAM_NAME_PHONE);
        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);
        String pass = request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD);
        String password = passwordHash.getHash(pass);
        Account account = new Account(login,password,ROLE_USER);
        Person person = new Person(name,surname,email,phone);
        try{
            accountDao.create(account);
            person.setAccountId(accountDao.getId(account));
            personDao.create(person);
            request.setAttribute("user", name);
            page = JSPPageName.USER_PAGE;
        }catch (DaoException e){
            throw new ProjectException("problem with dao",e);

         }
    return page;
}
}
