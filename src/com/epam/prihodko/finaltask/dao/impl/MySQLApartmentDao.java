package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.dao.domain.ApartmentDao;
import com.epam.prihodko.finaltask.domain.Apartment;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
            while (resultSet.next()){
                apartment = new Apartment();
                apartment.setId(resultSet.getInt("id"));
                apartment.setPrice(resultSet.getInt("price"));
                apartment.setCouchette(resultSet.getInt("couchette"));
                apartment.setRoomNumber(resultSet.getInt("room_number"));
                apartment.setStatus(resultSet.getNString("status"));
                apartment.setClassId(resultSet.getInt("class_id"));
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
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLApartmentDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLApartmentDao has problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
    }
    public void delete (Apartment apartment)throws DaoException {}
    public Map<Integer,Apartment> getSuitableApartmentMap(Apartment apartment)throws DaoException{
        Map<Integer, Apartment> mapApartment = new HashMap<Integer, Apartment>();
        PreparedStatement preparedStatement = null;
        Connection connection =null;
        String str = "select * from apartment " +
                "where class_id="+apartment.getClassId() +
                " and apartment.couchette="+apartment.getCouchette()+" and apartment.room_number="+apartment.getRoomNumber()+" and apartment.status='"+apartment.getStatus()+"'";
        try{
            connection = Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int price = resultSet.getInt("price");
                int couchette = resultSet.getInt("couchette");
                int room_number = resultSet.getInt("room_number");
                String status = resultSet.getString("status");
                int class_id = resultSet.getInt("class_id");

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
            throw new DaoException("MySQLApartmentDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLApartmentDao has problem with connection pool",e);
        } finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
        return mapApartment;
    }
}
