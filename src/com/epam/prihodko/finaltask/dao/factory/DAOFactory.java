package com.epam.prihodko.finaltask.dao.factory;
import com.epam.prihodko.finaltask.dao.entity.*;

public abstract class DAOFactory {
    private final static MySQLDAOFactory mySqlDAOFactory = new MySQLDAOFactory();
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
                return mySqlDAOFactory;
            default:
                return null;
        }
    }
}
