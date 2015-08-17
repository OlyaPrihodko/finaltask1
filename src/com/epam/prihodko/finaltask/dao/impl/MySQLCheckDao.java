package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.CheckDao;
import com.epam.prihodko.finaltask.domain.Check;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySQLCheckDao implements CheckDao{
    public Check getById(int domainId) throws DaoException{
        Check check = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from mydb.check where id="+domainId;
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                check = new Check();
                check.setId(resultSet.getInt("id"));
                check.setPrice(resultSet.getInt("price"));
                check.setApatrmentId(resultSet.getInt("apartment_id"));
                check.setOrderId(resultSet.getInt("order_id"));
            }

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection,preparedStatement);
        }
        return check;
    }
    public void create(Check check)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "insert into mydb.check (price,apartment_id,order_id) values(?,?,?)";
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setInt(1, check.getPrice());
            preparedStatement.setInt(2, check.getApatrmentId());
            preparedStatement.setInt(3, check.getOrderId());
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLCheckDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLCheckDao has problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
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
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                check = new Check();
                check.setId(resultSet.getInt("id"));
                check.setPrice(resultSet.getInt("price"));
                check.setApatrmentId(resultSet.getInt("apartment_id"));
                check.setOrderId(resultSet.getInt("order_id"));
            }

        }catch (SQLException e){
            throw new DaoException("MySQLCheckDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLCheckDao has problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection,preparedStatement);
        }
        return check;
    }
}
