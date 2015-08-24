package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.dao.entity.CheckDao;
import com.epam.prihodko.finaltask.dao.entity.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.DAOFactory;
import com.epam.prihodko.finaltask.entity.Check;
import com.epam.prihodko.finaltask.entity.MapBean;
import com.epam.prihodko.finaltask.entity.Order;
import com.epam.prihodko.finaltask.exception.DaoException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/***
 * DeleteOrderCommand removes selected order
 */

public class DeleteOrderCommand implements ICommand {
    private static DAOFactory MySQLDaoFactory = DAOFactory.getDAOFactory(DAOFactory.DataSourceName.MYSQL);
    private static OrderDao orderDao = MySQLDaoFactory.getOrderDao();
    private static CheckDao checkDao = MySQLDaoFactory.getCheckDao();
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page;
        String ordId = request.getParameter(RequestParameterName.ORDER_ID);
        int orderId = Integer.parseInt(ordId);
        try{
            Order order = orderDao.getById(orderId);
            checkDao.deleteByOrderId(orderId);
            orderDao.delete(order);
            /**
             * Get orders by person ID if man is user
             * or
             * get orders by status new if man is admin
             * */
            if(request.getSession().getAttribute(RequestParameterName.ROLE_USER).equals(RequestParameterName.ROLE_USER)){
                String personId = request.getSession().getAttribute(RequestParameterName.PERSON_ID).toString();
                Map<Integer,Order> mapOrder = orderDao.getOrderMapByPersonId(Integer.parseInt(personId));
                MapBean mapBeanOrder = new MapBean(mapOrder);
                request.getSession().setAttribute(RequestParameterName.MAP_BEAN_ORDER,mapBeanOrder);
                Map<Integer,Check> mapCheck = new HashMap<Integer, Check>();
                int mapBeanOrderSize = Integer.parseInt(mapBeanOrder.getSize());
                for(int i=0; i<mapBeanOrderSize;i++){
                    mapBeanOrder.getElement();
                    orderId = mapBeanOrder.getId();
                    Check check = checkDao.getCheckByOrderId(orderId);
                    if(check!=null){
                        mapCheck.put(check.getId(),check);
                    }
                }
                MapBean mapBeanCheck = new MapBean(mapCheck);
                request.getSession().setAttribute(RequestParameterName.MAP_BEAN_CHECK,mapBeanCheck);
                page = JSPPageName.USER_PERSONAL_AREA_PAGE;
            }
            else{
                Map<Integer,Order> mapOrder = orderDao.getOrderMapByStatus(RequestParameterName.STATUS_NEW);
                MapBean mapBeanOrder = new MapBean(mapOrder);
                request.getSession().setAttribute(RequestParameterName.MAP_BEAN_ORDER,mapBeanOrder);
                page = JSPPageName.ADMIN_MAIN_PAGE;
            }
        }catch (DaoException e){
            throw new ProjectException("DeleteOrderCommand has problem with dao",e);
        }
        return page;
    }
}
