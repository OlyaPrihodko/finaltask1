package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.connection.ConnectionPool;
import com.epam.prihodko.finaltask.dao.domain.AccountDao;
import com.epam.prihodko.finaltask.domain.Account;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.*;

public class MySQLAccountDao implements AccountDao{
    public boolean checkAccount(Account account)throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        boolean b=false;
        String s="select * from account where login='"+account.getLogin()+"'&& password='"+account.getPassword()+"'";
        try{
            connection= Controller.connectionPool.takeConnection();
            preparedStatement=connection.prepareStatement(s);
            ResultSet resultSet = preparedStatement.executeQuery();
            b=resultSet.next();
        }catch (SQLException e){
           // e.printStackTrace();
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            //e.printStackTrace();
            throw new DaoException("Problem with connection pool",e);
        }
        finally {
            //preparedStatement.close();
            Controller.connectionPool.closeConnection(connection,preparedStatement);
        }
        return b;
    }
    public Account getById(int id)throws DaoException {
        Account account = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from account where id="+id;
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setLogin(resultSet.getString("login"));
                account.setPassword(resultSet.getString("password"));
            }

        }catch (SQLException e){
            // e.printStackTrace();
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            //e.printStackTrace();
            throw new DaoException("Problem with connection pool",e);
        }
        finally {

            //preparedStatement.close();
            Controller.connectionPool.closeConnection(connection, preparedStatement); 
        }
        return account;
    }
    public void create (Account account)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "insert into account (login,password) values(?,?)";
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1,account.getLogin());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.execute();

        }catch (SQLException e){
            // e.printStackTrace();
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            //e.printStackTrace();
            throw new DaoException("Problem with connection pool",e);
        }
        finally {

            //preparedStatement.close();
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    public int getId(Account account)throws DaoException{
        int id=0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select id from account where login='"+account.getLogin()+"'&& password='"+account.getPassword()+"'";
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 id = resultSet.getInt("id");
            }

        }catch (SQLException e){
            // e.printStackTrace();
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            //e.printStackTrace();
            throw new DaoException("Problem with connection pool",e);
        }
        finally {

            //preparedStatement.close();
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
        return id;
    }

}
