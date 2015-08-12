package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.ApartmentDao;
import com.epam.prihodko.finaltask.domain.Apartment;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLApartmentDao implements ApartmentDao{
    public Apartment getById(int id)throws DaoException{
        Apartment apartment=null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from apartment where id="+id;
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                apartment = new Apartment();
                apartment.setId(resultSet.getInt("id"));
                apartment.setPrice(resultSet.getInt("price"));
                apartment.setCouchette(resultSet.getInt("couchette"));
                apartment.setRoomNumber(resultSet.getInt("room_number"));
            }

        }catch (SQLException e){
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }

        return apartment;
    }
    public void create (Apartment apartment)throws DaoException {}
    public void update (Apartment apartment)throws DaoException {}
    public void delete (Apartment apartment)throws DaoException {}
}
