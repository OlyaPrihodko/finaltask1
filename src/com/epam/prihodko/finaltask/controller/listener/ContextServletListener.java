package com.epam.prihodko.finaltask.controller.listener;

import com.epam.prihodko.finaltask.dao.connection.ConnectionPool;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import org.apache.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

/**
 * Create and destroyed connectionPool for DataBase
 */
public class ContextServletListener implements ServletContextListener {
    public static ConnectionPool connectionPool=null;
    //private static final Logger log = Logger.getLogger(ContextServletListener.class);
    public void contextInitialized(ServletContextEvent event) {
        try{
            connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
        }
        catch (ConnectionPoolException e) {
         //   log.info("Exception with connectionPool initialization");
        }
    }
    public void contextDestroyed(ServletContextEvent event) {
        try {
            connectionPool.closeConnectionsQueue(connectionPool.getBlockingQueue());
        } catch (SQLException e) {
        //    log.info("Exception with closeConnectionQueue connectionPool");
        }
    }
}
