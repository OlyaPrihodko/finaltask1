package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.dao.factory.MySQLDAOFactory;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.*;

public class MySQLOrderDao implements OrderDao{
    public Order getById(int domainId) throws DaoException {
        Order order = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from order where id="+domainId;
        try{
            connection =  Controller.connectionPool.takeConnection();//.prepareStatement(str);
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCouchette(resultSet.getInt("couchette"));
                order.setRoom_number(resultSet.getInt("room_number"));
                order.setDate_in(resultSet.getString("date_in"));
                order.setDate_out(resultSet.getString("date_out"));
            }

        }catch (SQLException e){
            // e.printStackTrace();
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            //e.printStackTrace();
            throw new DaoException("Problem with connection pool",e);
        }
        finally {
           // preparedStatement.close();
            Controller.connectionPool.closeConnection(connection,preparedStatement);

        }
        return order;
    }
    public void create(Order order)throws DaoException{

    }
   /* public Order create ();
    public Order persist(Order domain);
    public void update (Order domain);
    public void save (Order domain);
    public void delete (Order domain);
    */
}
