package com.epam.prihodko.finaltask.dao.impl;

import com.epam.prihodko.finaltask.controller.listener.ContextServletListener;
import com.epam.prihodko.finaltask.dao.DataBaseParameterName;
import com.epam.prihodko.finaltask.dao.entity.AccountDao;
import com.epam.prihodko.finaltask.entity.Account;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;
import java.sql.*;

public class MySQLAccountDao implements AccountDao{
    private final static String getAccountById = "select * from account where id=";

    public Account getById(int id)throws DaoException {
        Account account = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select * from account where id="+id;
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                account = new Account();
                account.setId(resultSet.getInt(DataBaseParameterName.ID));
                account.setLogin(resultSet.getString(DataBaseParameterName.LOGIN));
                account.setPassword(resultSet.getString(DataBaseParameterName.PASSWORD));
            }

        }catch (SQLException e){
            throw new DaoException("MySQLAccountDao has problem with Sql in getById method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLAccountDao has problem with connection pool in getById method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
        return account;
    }
    public void create (Account account)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "insert into account (login,password,role) values(?,?,?)";
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1,account.getLogin());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setString(3,account.getRole());
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLAccountDao has problem with Sql in create method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLAccountDao has problem with connection pool in create method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
    }
    public void update(Account account)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "update account set login=?, password=? where id=?";
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setInt(3, account.getId());
            preparedStatement.setString(1,account.getLogin());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.execute();

        }catch (SQLException e){
            throw new DaoException("MySQLAccountDao has problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLAccountDao has problem with connection pool",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
    }
    public void delete(Account account)throws DaoException{}
    public boolean checkAccount(Account account)throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        boolean b=false;
        String s="select * from account where login='"+account.getLogin()+"'&& password='"+account.getPassword()+"'";
        try{
            connection= ContextServletListener.connectionPool.takeConnection();
            preparedStatement=connection.prepareStatement(s);
            ResultSet resultSet = preparedStatement.executeQuery();
            b=resultSet.next();
            if(b){
            account.setId(resultSet.getInt(DataBaseParameterName.ID));
            }
        }catch (SQLException e){
            throw new DaoException("MySQLAccountDao has problem with Sql in checkAccount method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLAccountDao has problem with connection pool in checkAccount method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection,preparedStatement);
        }
        return b;
    }
    public int getId(Account account)throws DaoException{
        int id=0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str = "select id from account where login='"+account.getLogin()+"'&& password='"+account.getPassword()+"'";
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 id = resultSet.getInt(DataBaseParameterName.ID);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLAccountDao has problem with Sql in getId method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLAccountDao has problem with connection pool in getId method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
        return id;
    }
    public String getRole(Account account) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String role=null;
        String str = "select role from account where id="+account.getId();
        try{
            connection =  ContextServletListener.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
                role = resultSet.getString(DataBaseParameterName.ROLE);
            }

        }catch (SQLException e){
            throw new DaoException("MySQLAccountDao has problem with Sql in getRole method",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("MySQLAccountDao has problem with connection pool in getRole method",e);
        }
        finally {
            ContextServletListener.connectionPool.closeConnection(connection, preparedStatement);
        }
        return role;
    }
}
