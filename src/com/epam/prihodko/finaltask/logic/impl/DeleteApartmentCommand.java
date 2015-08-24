package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.dao.entity.CheckDao;
import com.epam.prihodko.finaltask.dao.entity.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteApartmentCommand implements ICommand {
    private static DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    private static OrderDao orderDao = MySQLDaoFactory.getOrderDao();
    private static CheckDao checkDao = MySQLDaoFactory.getCheckDao();
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page=null;
        return page;
    }
}
