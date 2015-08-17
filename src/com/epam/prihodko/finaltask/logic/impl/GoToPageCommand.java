package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.CommandName;
import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.controller.ResponseParameterName;
import com.epam.prihodko.finaltask.dao.domain.AccountDao;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.dao.domain.PersonDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.domain.MapBean;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class GoToPageCommand implements ICommand{
private static DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
private static OrderDao orderDao = MySQLDaoFactory.getOrderDao();
        @Override
        public String execute(HttpServletRequest request) throws ProjectException {
            CommandName commandName = CommandName.valueOf(request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase().replace("-","_"));
switch (commandName){
    case GO_TO_REGISTR_PAGE:
        return JSPPageName.REGISTR_PAGE;
    case GO_TO_USER_PERSONAL_DATA_PAGE:
        return JSPPageName.USER_PERSONAL_DATA_PAGE;
    case GO_TO_MAKE_AN_ORDER_PAGE:
        return JSPPageName.MAKE_AN_ORDER_PAGE;
    case GO_TO_CHANGE_ORDER_PAGE:


        String ord = request.getParameter(ResponseParameterName.ORDER_ID);
        int orderId = Integer.parseInt(ord);
        try{
            Order order = orderDao.getById(orderId);
            //Map<Integer,Order> mapOrder = new HashMap<Integer, Order>();
            //mapOrder.put(orderId,order);
            //MapBean mapBeanOrder = new MapBean(mapOrder);
            //request.setAttribute(ResponseParameterName.ORDER_ID,orderId);
            request.getSession().setAttribute(ResponseParameterName.ORDER_ID,orderId);
            request.getSession().setAttribute(ResponseParameterName.ORDER,order);
        }catch (DaoException e){
            throw new ProjectException("problem with dao",e);
        }


        return JSPPageName.CHANGE_ORDER_PAGE;
        case PREVIOUS_PAGE:
        String str = (String)request.getSession().getAttribute("previous-page");
        if(str.equals(JSPPageName.LOGIN_PAGE)){
            return JSPPageName.LOGIN_PAGE;
        }
        if(str.equals(JSPPageName.REGISTR_PAGE)){
            return JSPPageName.REGISTR_PAGE;
        }
        if(str.equals(JSPPageName.USER_PERSONAL_AREA_PAGE)){
            return JSPPageName.USER_PERSONAL_AREA_PAGE;
        }
        if(str.equals(JSPPageName.USER_PERSONAL_DATA_PAGE)){
            return JSPPageName.USER_PERSONAL_DATA_PAGE;
        }
        if(str.equals(JSPPageName.ADMIN_MAIN_PAGE)){
            MySQLDaoFactory =
                    DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);;
            orderDao = MySQLDaoFactory.getOrderDao();
            request.getSession().setAttribute(ResponseParameterName.USER,ResponseParameterName.ADMIN);
            try{
                Map<Integer,Order> mapOrder = orderDao.getOrderMapByStatus(RequestParameterName.STATUS_NEW);
                MapBean mapBeanOrder = new MapBean(mapOrder);
                request.getSession().setAttribute(ResponseParameterName.MAP_BEAN_ORDER,mapBeanOrder);
            }
            catch (DaoException e){
                throw new ProjectException("problem with dao",e);
            }

            return JSPPageName.ADMIN_MAIN_PAGE;
        }


}

        /*String str= (String)request.getSession().getAttribute("previous-page");
        String str1=str.replace("-","_").toUpperCase();
        if(JSPPageName.LOGIN_PAGE.equals(str1)){
            return JSPPageName.LOGIN_PAGE;
        }
        if(str.toUpperCase().replace("-","_").equals(JSPPageName.REGISTR_PAGE)){
            return JSPPageName.REGISTR_PAGE;}
        }
        return null;
        */
            return null;
    }
}
