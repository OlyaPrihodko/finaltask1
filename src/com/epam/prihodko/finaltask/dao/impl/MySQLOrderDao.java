package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.domain.SetBean;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.sql.*;

public class MySQLOrderDao implements OrderDao{
    private final static String DATE_FORMAT = "yyyy-mm-dd";
    public Order getById(int domainId) throws DaoException {
        Order order = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from mydb.order where id="+domainId;
        try{
            connection =  Controller.connectionPool.takeConnection();//.prepareStatement(str);
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setApartmentClass(resultSet.getString("apartment_class"));
                order.setRoomNumber(resultSet.getInt("room_number"));
                order.setCouchette(resultSet.getInt("couchette"));
                order.setDate_in(resultSet.getString("date_in"));
                order.setDate_out(resultSet.getString("date_out"));
                order.setStatus(resultSet.getNString("status"));


            }

        }catch (SQLException e){
            // e.printStackTrace();
            throw new DaoException("MySQLOrderDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            //e.printStackTrace();
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        }
        finally {
           // preparedStatement.close();
            Controller.connectionPool.closeConnection(connection,preparedStatement);

        }
        return order;
    }
    /* */
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
            connection =  Controller.connectionPool.takeConnection();
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
            // e.printStackTrace();
            throw new DaoException("MySQLOrderDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            //e.printStackTrace();
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        } catch (ParseException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        } finally {

            //preparedStatement.close();
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
    }
    public void update (Order order) throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "update mydb.order set couchette='"+order.getCouchette()+"', date_in='"+order.getDate_in()+"'," +
                "date_out='"+order.getDate_out()+"',status='"+order.getStatus()+"',apartment_class='"+order.getApartmentClass()+"'," +
                " room_number='"+order.getRoomNumber()+"' where id="+order.getId();
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
           /* preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getPhone());
            preparedStatement.setInt(5, person.getId());
            */
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }

    }
    public void delete (Order order) throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "delete from mydb.order where id="+order.getId();
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
}
    public Map<Integer, Order> getOrderMapByPersonId(int personId)throws DaoException{
        Map<Integer, Order> mapOrder = new HashMap<Integer, Order>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select id,apartment_class,room_number,couchette,date_in,date_out,status from mydb.order where person_id="+personId;
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String apartment_class = resultSet.getString("apartment_class");
                int room_number = resultSet.getInt("room_number");
                int couchette = resultSet.getInt("couchette");
                String date_in = resultSet.getString("date_in");
                String date_out = resultSet.getString("date_out");
                String status = resultSet.getString("status");
                Order order = new Order(id,apartment_class,room_number,couchette,date_in,date_out,status);
                mapOrder.put(id,order);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        } finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
        return mapOrder;
    }
    public Map<Integer, Order> getOrderMapByStatus(String status)throws DaoException{
        Map<Integer, Order> mapOrder = new HashMap<Integer, Order>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select id,apartment_class,room_number,couchette,date_in,date_out,status from mydb.order where status='"+status+"'";
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String apartment_class = resultSet.getString("apartment_class");
                int room_number = resultSet.getInt("room_number");
                int couchette = resultSet.getInt("couchette");
                String date_in = resultSet.getString("date_in");
                String date_out = resultSet.getString("date_out");
                String statusOr = resultSet.getString("status");
                Order order = new Order(id,apartment_class,room_number,couchette,date_in,date_out,statusOr);
                mapOrder.put(id, order);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        } finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
        return mapOrder;
    }
}
