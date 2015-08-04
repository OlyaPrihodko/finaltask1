package com.epam.prihodko.finaltask.dao.factory;

import com.epam.prihodko.finaltask.dao.domain.AccountDao;
import com.epam.prihodko.finaltask.dao.factory.MySQLDAOFactory;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.dao.domain.PersonDao;

public abstract class DAOFactory {
    public static enum DataSourceName{
        MYSQL
    }
    public abstract PersonDao getPeronDao();
    public abstract OrderDao getOrderDao();
    public abstract AccountDao getAccountDao();
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
