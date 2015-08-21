package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.listener.ContextServletListener;
import com.epam.prihodko.finaltask.dao.DataBaseParameterName;
import com.epam.prihodko.finaltask.dao.entity.OrderDao;
import com.epam.prihodko.finaltask.entity.Order;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MySQLOrderDao implements OrderDao{
    private final static String DATE_FORMAT = "yyyy-mm-dd";
    public Order getById(int domainId) throws DaoException {
        Order order = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from mydb.order where id="+domainId;
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt(DataBaseParameterName.ID));
                order.setApartmentClass(resultSet.getString(DataBaseParameterName.APARTMENT_CLASS));
                order.setRoomNumber(resultSet.getInt(DataBaseParameterName.ROOM_NUMBER));
                order.setCouchette(resultSet.getInt(DataBaseParameterName.COUCHETTE));
                order.setDate_in(resultSet.getString(DataBaseParameterName.DATE_IN));
                order.setDate_out(resultSet.getString(DataBaseParameterName.DATE_OUT));
                order.setStatus(resultSet.getNString(DataBaseParameterName.STATUS));
            }
        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql in getById method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool in getById method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection,preparedStatement);

        }
        return order;
    }
    public void create(Order order)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "insert into mydb.order (apartment_class,room_number,couchette,date_in,date_out,person_id,status) values(?,?,?,?,?,?,?)";
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

            java.util.Date dateUtilIn = simpleDateFormat.parse(order.getDate_in());
            java.sql.Date dateSqlIn = new java.sql.Date(dateUtilIn.getTime());

            java.util.Date dateUtilOut = simpleDateFormat.parse(order.getDate_out());
            java.sql.Date dateSqlOut = new java.sql.Date(dateUtilOut.getTime());
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1,order.getApartmentClass());
            preparedStatement.setInt(2, order.getRoomNumber());
            preparedStatement.setInt(3, order.getCouchette());
            preparedStatement.setDate(4, dateSqlIn);
            preparedStatement.setDate(5, dateSqlOut);
            preparedStatement.setInt(6,order.getPersonId());
            preparedStatement.setString(7,order.getStatus());
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql in create method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool in create method",e);
        } catch (ParseException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool in create method",e);
        } finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
    }
    public void update (Order order) throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "update mydb.order set couchette='"+order.getCouchette()+"', date_in='"+order.getDate_in()+"'," +
                "date_out='"+order.getDate_out()+"',status='"+order.getStatus()+"',apartment_class='"+order.getApartmentClass()+"'," +
                " room_number='"+order.getRoomNumber()+"' where id="+order.getId();
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql in update method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool in update method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }

    }
    public void delete (Order order) throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "delete from mydb.order where id="+order.getId();
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql in delete method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool in delete method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
}
    public Map<Integer, Order> getOrderMapByPersonId(int personId)throws DaoException{
        Map<Integer, Order> mapOrder = new HashMap<Integer, Order>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select id,apartment_class,room_number,couchette,date_in,date_out,status from mydb.order where person_id="+personId;
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(DataBaseParameterName.ID);
                String apartment_class = resultSet.getString(DataBaseParameterName.APARTMENT_CLASS);
                int room_number = resultSet.getInt(DataBaseParameterName.ROOM_NUMBER);
                int couchette = resultSet.getInt(DataBaseParameterName.COUCHETTE);
                String date_in = resultSet.getString(DataBaseParameterName.DATE_IN);
                String date_out = resultSet.getString(DataBaseParameterName.DATE_OUT);
                String status = resultSet.getString(DataBaseParameterName.STATUS);
                Order order = new Order(id,apartment_class,room_number,couchette,date_in,date_out,status);
                mapOrder.put(id,order);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql in getOrderMapByPersonId method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool in getOrderMapByPersonId method",e);
        } finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
        return mapOrder;
    }
    public Map<Integer, Order> getOrderMapByStatus(String status)throws DaoException{
        Map<Integer, Order> mapOrder = new HashMap<Integer, Order>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select id,apartment_class,room_number,couchette,date_in,date_out,status from mydb.order where status='"+status+"'";
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(DataBaseParameterName.ID);
                String apartment_class = resultSet.getString(DataBaseParameterName.APARTMENT_CLASS);
                int room_number = resultSet.getInt(DataBaseParameterName.ROOM_NUMBER);
                int couchette = resultSet.getInt(DataBaseParameterName.COUCHETTE);
                String date_in = resultSet.getString(DataBaseParameterName.DATE_IN);
                String date_out = resultSet.getString(DataBaseParameterName.DATE_OUT);
                String statusOr = resultSet.getString(DataBaseParameterName.STATUS);
                Order order = new Order(id,apartment_class,room_number,couchette,date_in,date_out,statusOr);
                mapOrder.put(id, order);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql in getOrderMapByStatus method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool in getOrderMapByStatus method",e);
        } finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
        return mapOrder;
    }
}
