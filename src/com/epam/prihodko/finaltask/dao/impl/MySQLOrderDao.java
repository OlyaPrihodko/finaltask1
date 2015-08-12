package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.OrderDao;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.domain.SetBean;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MySQLOrderDao implements OrderDao{
    private final static String DATE_FORMAT = "yyyy-mm-dd";
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
                //order.setRoom_number(resultSet.getInt("room_number"));
                order.setDate_in(resultSet.getString("date_in"));
                order.setDate_out(resultSet.getString("date_out"));
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
        String str = "insert into mydb.order (apartment_class,room_number,couchette,date_in,date_out,person_id) values(?,?,?,?,?,?)";
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
        String str = "update order set couchette='"+order.getCouchette()+"', date_in='"+order.getDate_in()+"'," +
                "date_out='"+order.getDate_out()+"' where id="+order.getId();
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
   /* public Order create ();
    public Order persist(Order domain);
    public void update (Order domain);
    public void save (Order domain);
    public void delete (Order domain);
    */
}
    public Set getOrderSetByPersonId(int personId)throws DaoException{
        Set setOrder = new HashSet();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select apartment_class,room_number,couchette,date_in,date_out from mydb.order where person_id="+personId;
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String apartment_class = resultSet.getString("apartment_class");
                int room_number = resultSet.getInt("room_number");
                int couchette = resultSet.getInt("couchette");
                String date_in = resultSet.getString("date_in");
                String date_out = resultSet.getString("date_out");
                Order order = new Order(apartment_class,room_number,couchette,date_in,date_out);
                setOrder.add(order);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLOrderDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLOrderDao has problem with connection pool",e);
        } finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
        return setOrder;
    }
}
