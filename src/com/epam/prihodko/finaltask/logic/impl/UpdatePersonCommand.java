package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.dao.entity.PersonDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.entity.Person;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class UpdatePersonCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
    String page = JSPPageName.USER_PERSONAL_AREA_PAGE;
        DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        PersonDao personDao = MySQLDaoFactory.getPersonDao();
        Person person = (Person)request.getSession().getAttribute(RequestParameterName.PERSON);
        person.setName(request.getParameter(RequestParameterName.PARAM_NAME_NAME));
        person.setSurname(request.getParameter(RequestParameterName.PARAM_NAME_SURNAME));
        person.setEmail(request.getParameter(RequestParameterName.PARAM_NAME_EMAIL));
        person.setPhone(request.getParameter(RequestParameterName.PARAM_NAME_PHONE));
        try{
            personDao.update(person);
        }catch (DaoException e){
            throw new ProjectException("UpdatePersonCommand has problem with dao",e);
        }
    return page;
    }

}
