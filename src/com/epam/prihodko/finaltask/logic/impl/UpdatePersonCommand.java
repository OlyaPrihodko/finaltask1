package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.dao.domain.PersonDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.domain.Person;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class UpdatePersonCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
    String page = JSPPageName.USER_PERSONAL_AREA_PAGE;
        DAOFactory MySQLDaoFactory =
                DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
        PersonDao personDao = MySQLDaoFactory.getPersonDao();
        Person person = (Person)request.getSession().getAttribute("user");
        person.setName(request.getParameter("name"));
        person.setSurname(request.getParameter("surname"));
        person.setEmail(request.getParameter("email"));
        person.setPhone(request.getParameter("phone"));
        try{
            personDao.update(person);
            //как-то само обновляется и запизивается в сессию
            //request.getSession().setAttribute("user",person);//не знаю еще точно зачем это надо или не надо
            //луше надо. прокатило с обновлением заказов
        }catch (DaoException e){
            throw new ProjectException("problem with dao",e);
        }
    return page;
    }

}
