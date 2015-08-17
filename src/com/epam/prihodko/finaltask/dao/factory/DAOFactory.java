package com.epam.prihodko.finaltask.dao.factory;

import com.epam.prihodko.finaltask.dao.domain.*;

public abstract class DAOFactory {
    public static enum DataSourceName{
        MYSQL
    }

    public abstract AccountDao getAccountDao();
    public abstract ApartmentDao getApartmentDao();
    public abstract ApartmentClassDao getApartmentClassDao();
    public abstract CheckDao getCheckDao();
    public abstract OrderDao getOrderDao();
    public abstract PersonDao getPersonDao();

    public static DAOFactory getDAOFactory(DataSourceName dataSourceName ){
        switch (dataSourceName){
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}
