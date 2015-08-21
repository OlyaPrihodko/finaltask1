package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.listener.ContextServletListener;
import com.epam.prihodko.finaltask.dao.DataBaseParameterName;
import com.epam.prihodko.finaltask.dao.entity.CheckDao;
import com.epam.prihodko.finaltask.entity.Check;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLCheckDao implements CheckDao{
    public Check getById(int domainId) throws DaoException{
        Check check = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from mydb.check where id="+domainId;
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                check = new Check();
                check.setId(resultSet.getInt(DataBaseParameterName.ID));
                check.setPrice(resultSet.getInt(DataBaseParameterName.PRICE));
                check.setApatrmentId(resultSet.getInt(DataBaseParameterName.APARTMENT_ID));
                check.setOrderId(resultSet.getInt(DataBaseParameterName.ORDER_ID));
            }

        }catch (SQLException e){
            throw new DaoException(" MySQLCheckDao has problem with Sql in getById method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException(" MySQLCheckDao has problem with connection pool in getById method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection,preparedStatement);
        }
        return check;
    }
    public void create(Check check)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "insert into mydb.check (price,apartment_id,order_id) values(?,?,?)";
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setInt(1, check.getPrice());
            preparedStatement.setInt(2, check.getApatrmentId());
            preparedStatement.setInt(3, check.getOrderId());
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLCheckDao has problem with Sql in create method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLCheckDao has problem with connection pool in create method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
    }
    public void update(Check check) throws DaoException{}
    public void delete(Check check)throws DaoException{}
    public Check getCheckByOrderId(int orderId)throws DaoException{
        Check check = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from mydb.check where check.order_id="+orderId;
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                check = new Check();
                check.setId(resultSet.getInt(DataBaseParameterName.ID));
                check.setPrice(resultSet.getInt(DataBaseParameterName.PRICE));
                check.setApatrmentId(resultSet.getInt(DataBaseParameterName.APARTMENT_ID));
                check.setOrderId(resultSet.getInt(DataBaseParameterName.ORDER_ID));
            }

        }catch (SQLException e){
            throw new DaoException("MySQLCheckDao has problem with Sql in getCheckByOrderId method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLCheckDao has problem with connection pool in getCheckByOrderId method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection,preparedStatement);
        }
        return check;
    }
}
