package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.ApartmentClassDao;
import com.epam.prihodko.finaltask.domain.ApartmentClass;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLApartmentClassDao implements ApartmentClassDao {
    public ApartmentClass getById(int id)throws DaoException {
        ApartmentClass apartmentClass=null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from apartment where id="+id;
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                apartmentClass = new ApartmentClass();
                apartmentClass.setId(resultSet.getInt("id"));
                apartmentClass.setType(resultSet.getString("type"));
            }

        }catch (SQLException e){
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }

        return apartmentClass;
}
    public void create (ApartmentClass apartmentClass)throws DaoException {}
    public void update (ApartmentClass apartmentClass)throws DaoException {}
    public void delete (ApartmentClass apartmentClass)throws DaoException {}
}
