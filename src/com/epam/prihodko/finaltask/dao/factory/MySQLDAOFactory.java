package com.epam.prihodko.finaltask.dao.factory;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.*;
import com.epam.prihodko.finaltask.dao.impl.*;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

   /* public static Connection getConnection() throws ConnectionPoolException {
        return Controller.connectionPool.takeConnection();
    }*/

    public AccountDao getAccountDao(){
        return new MySQLAccountDao();
    }
    public ApartmentDao getApartmentDao(){
        return new MySQLApartmentDao();
    }
    public ApartmentClassDao getApartmentClassDao(){
        return new MySQLApartmentClassDao();
    }
    /*public CheckDao getCheckDao(){
        return new MySQLCheckDao();
    }*/
    public  OrderDao getOrderDao(){
        return new MySQLOrderDao();
    }
    public  PersonDao getPersonDao(){
        return new MySQLPersonDao();
    }
    /*public  StatusDao getStatusDao(){
        return new MySQLStatusDao();
    }*/
}
