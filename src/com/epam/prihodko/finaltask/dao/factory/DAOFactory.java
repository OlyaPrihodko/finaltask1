package com.epam.prihodko.finaltask.dao.factory;

import com.epam.prihodko.finaltask.dao.domain.*;
import com.epam.prihodko.finaltask.dao.factory.MySQLDAOFactory;
import com.epam.prihodko.finaltask.domain.Apartment;
import com.epam.prihodko.finaltask.domain.Check;

public abstract class DAOFactory {
    public static enum DataSourceName{
        MYSQL
    }

    public abstract AccountDao getAccountDao();
    public abstract ApartmentDao getApartmentDao();
    public abstract ApartmentClassDao getApartmentClassDao();
   // public abstract CheckDao getCheckDao();
    public abstract OrderDao getOrderDao();
    public abstract PersonDao getPersonDao();
    //public abstract StatusDao getStatusDao();

    /* ...*/

    public static DAOFactory getDAOFactory(DataSourceName dataSourceName ){
        switch (dataSourceName){
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }

}
