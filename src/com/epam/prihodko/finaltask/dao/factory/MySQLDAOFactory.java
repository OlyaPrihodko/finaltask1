package com.epam.prihodko.finaltask.dao.factory;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.AccountDao;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.dao.domain.PersonDao;
import com.epam.prihodko.finaltask.dao.impl.MySQLAccountDao;
import com.epam.prihodko.finaltask.dao.impl.MySQLOrderDao;
import com.epam.prihodko.finaltask.dao.impl.MySQLPersonDao;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

   /* public static Connection getConnection() throws ConnectionPoolException {
        return Controller.connectionPool.takeConnection();
    }*/
    public  PersonDao getPeronDao(){
        return new MySQLPersonDao();
    }
    public  OrderDao getOrderDao(){
        return new MySQLOrderDao();
    }
    public AccountDao getAccountDao(){
        return new MySQLAccountDao();
    }
}
