package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.dao.domain.AccountDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.domain.Account;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;
import javax.servlet.http.HttpServletRequest;
import com.epam.prihodko.finaltask.controller.RequestParameterName;

import java.sql.SQLException;

public class LoginCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        DAOFactory MySQLDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        AccountDao accountDao = MySQLDaoFactory.getAccountDao();
        String page = null;
        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);
        String pass = request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD);
        Account account = new Account(login,pass);
        //String comm = request.getParameter(RequestParameterName.COMMAND_NAME);
       // comm+="54";
        try{
            if(accountDao.checkAccount(account)){
                request.setAttribute("user", login);
                page = JSPPageName.MAIN_PAGE;
        }else{
                page = JSPPageName.LOGIN_PAGE;
            }
        }catch (DaoException e){
            throw new ProjectException("problem with dao",e);
        }
        return page;
    }
}
