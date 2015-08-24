package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.listener.ContextServletListener;
import com.epam.prihodko.finaltask.dao.DataBaseParameterName;
import com.epam.prihodko.finaltask.dao.entity.ApartmentClassDao;
import com.epam.prihodko.finaltask.entity.ApartmentClass;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLApartmentClassDao implements ApartmentClassDao {
    private final static String getApartmentClassById = "select * from apartment_class where id=";
    private final static String getIdApartmentClassByType = "select * from mydb.apartment_class where apartment_class.type=''";

    public ApartmentClass getById(int id)throws DaoException {
        ApartmentClass apartmentClass=null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = getApartmentClassById+id;
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                apartmentClass = new ApartmentClass();
                apartmentClass.setId(resultSet.getInt(DataBaseParameterName.ID));
                apartmentClass.setType(resultSet.getString(DataBaseParameterName.TYPE));
            }

        }catch (SQLException e){
            throw new DaoException("MySQLApartmentClassDao has problem with Sql in getById method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLApartmentClassDao has problem with connection pool in getById method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }

        return apartmentClass;
}
    public void create (ApartmentClass apartmentClass)throws DaoException {}
    public void update (ApartmentClass apartmentClass)throws DaoException {}
    public void delete (ApartmentClass apartmentClass)throws DaoException {}
    public int findIdByType(ApartmentClass apartmentClass)throws DaoException {
        int idApartmentClass=0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        StringBuilder str = new StringBuilder(getIdApartmentClassByType);
        str.insert(63,apartmentClass.getType());
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               idApartmentClass=resultSet.getInt(DataBaseParameterName.ID);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLApartmentClassDao has problem with Sql in findIdByType method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLApartmentClassDao has problem with connection pool in findIdByType method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection,preparedStatement);
        }
        return idApartmentClass;
    }
}
