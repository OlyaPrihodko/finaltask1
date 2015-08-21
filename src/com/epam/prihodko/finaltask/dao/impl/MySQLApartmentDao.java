package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.listener.ContextServletListener;
import com.epam.prihodko.finaltask.dao.DataBaseParameterName;
import com.epam.prihodko.finaltask.dao.entity.ApartmentDao;
import com.epam.prihodko.finaltask.entity.Apartment;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MySQLApartmentDao implements ApartmentDao{

    public Apartment getById(int id)throws DaoException{
        Apartment apartment=null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from apartment where id="+id;
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                apartment = new Apartment();
                apartment.setId(resultSet.getInt(DataBaseParameterName.ID));
                apartment.setPrice(resultSet.getInt(DataBaseParameterName.PRICE));
                apartment.setCouchette(resultSet.getInt(DataBaseParameterName.COUCHETTE));
                apartment.setRoomNumber(resultSet.getInt(DataBaseParameterName.ROOM_NUMBER));
                apartment.setStatus(resultSet.getNString(DataBaseParameterName.STATUS));
                apartment.setClassId(resultSet.getInt(DataBaseParameterName.CLASS_ID));
            }

        }catch (SQLException e){
            throw new DaoException("MySQLApartmentDao has problem with Sql in getById mathod",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLApartmentDao has problem with connection pool in getById mathod",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }

        return apartment;
    }
    public void create (Apartment apartment)throws DaoException {}
    public void update (Apartment apartment)throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "update mydb.apartment set " +
                "price="+apartment.getPrice()+" , " +
                "couchette="+apartment.getCouchette()+" , " +
                "status='"+apartment.getStatus()+"' , " +
                "class_id="+apartment.getClassId()+" , "+
                "room_number="+apartment.getRoomNumber()+
                " where id="+apartment.getId();
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLApartmentDao has problem with Sql in update method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLApartmentDao has problem with connection pool in update method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
    }
    public void delete (Apartment apartment)throws DaoException {}
    public Map<Integer,Apartment> getSuitableApartmentMap(Apartment apartment)throws DaoException{
        Map<Integer, Apartment> mapApartment = new LinkedHashMap<Integer, Apartment>();
        PreparedStatement preparedStatement = null;
        Connection connection =null;
        String str = "select * from apartment " +
                "where class_id="+apartment.getClassId() +
                " and apartment.couchette="+apartment.getCouchette()+
                " and apartment.room_number="+apartment.getRoomNumber()+
                " and apartment.status='"+apartment.getStatus()+"' "+
                "union select * from apartment "+

                "where couchette="+apartment.getCouchette()+
                " and apartment.status='"+apartment.getStatus()+"' "+
                " and apartment.room_number="+apartment.getRoomNumber()+
                " union select * from apartment "+

                " where couchette="+apartment.getCouchette()+
                " and apartment.status='"+apartment.getStatus()+"' "+
                " and apartment.class_id="+apartment.getClassId()+
                " union select * from apartment "+

                " where couchette="+apartment.getCouchette()+
                " and apartment.status='"+apartment.getStatus()+"'";
        try{
            connection = ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(DataBaseParameterName.ID);
                int price = resultSet.getInt(DataBaseParameterName.PRICE);
                int couchette = resultSet.getInt(DataBaseParameterName.COUCHETTE);
                int room_number = resultSet.getInt(DataBaseParameterName.ROOM_NUMBER);
                String status = resultSet.getString(DataBaseParameterName.STATUS);
                int class_id = resultSet.getInt(DataBaseParameterName.CLASS_ID);

                Apartment apartment1 = new Apartment();
                apartment1.setId(id);
                apartment1.setPrice(price);
                apartment1.setCouchette(couchette);
                apartment1.setRoomNumber(room_number);
                apartment1.setStatus(status);
                apartment1.setClassId(class_id);
                mapApartment.put(id, apartment1);
            }



        }catch (SQLException e){
            throw new DaoException("MySQLApartmentDao has problem with Sql in getSuitableApartmentMap method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLApartmentDao has problem with connection pool in getSuitableApartmentMap method",e);
        } finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
        return mapApartment;
    }
}
