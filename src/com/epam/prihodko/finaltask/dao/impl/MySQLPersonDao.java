package com.epam.prihodko.finaltask.dao.impl;


import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.PersonDao;
import com.epam.prihodko.finaltask.domain.Person;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLPersonDao implements PersonDao {
    private Connection connection;
    public Person getById(int domainId)throws DaoException {
        Person person = null;
        String s="select * from person where id=?";

    return person;
    }
    public void create(Person person)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
     // String setnames = "set names 'utf8'";
        String str = "insert into person (name, surname, email, phone, account_id) values(?,?,?,?,?)";
        try{
            connection =  Controller.connectionPool.takeConnection();
          //  preparedStatement = connection.prepareStatement(setnames);
          //  preparedStatement.execute();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getSurname());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.setString(4,person.getPhone());
            preparedStatement.setInt(5,person.getAccountId());
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
   /* public  Person create ();
    public Person persist(Person domain);
    public void update (Person domain);
    public void save (Person domain);
    public void delete (Person domain);*/
}
