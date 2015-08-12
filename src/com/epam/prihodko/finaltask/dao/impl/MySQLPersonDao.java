package com.epam.prihodko.finaltask.dao.impl;


import com.epam.prihodko.finaltask.controller.Controller;
import com.epam.prihodko.finaltask.dao.domain.PersonDao;
import com.epam.prihodko.finaltask.domain.Person;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLPersonDao implements PersonDao {
    //private Connection connection;
    public Person getById(int domainId)throws DaoException {
        Person person = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str="select * from person where id="+domainId;
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                person = new Person(name,surname,email,phone);
            }

        }catch (SQLException e){
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }

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
    public void update(Person person)throws DaoException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        //переписать ...хотя может и с такими вопросами будет работать
        String str = "update person set name='"+person.getName()+"', surname='"+person.getSurname()+"'," +
                " email='"+person.getEmail()+"', phone='"+person.getPhone()+"' where id="+person.getId();
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
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }
    }
    public void delete(Person person)throws DaoException{}
    public Person getByAccountId(int domainId)throws DaoException {
        Person person = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String str="select * from person where account_id="+domainId;
        try{
            connection =  Controller.connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(str);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                int id = resultSet.getInt("id");
                person = new Person(name,surname,email,phone);
                person.setAccountId(domainId);//устанавливаю accoubt_id ... и хорошо бы просто id
                person.setId(id);//пригодилось в логин команд а потом в make order
            }

        }catch (SQLException e){
            throw new DaoException("Problem with Sql",e);
        }catch (ConnectionPoolException e) {
            throw new DaoException("Problem with connection pool",e);
        }
        finally {
            Controller.connectionPool.closeConnection(connection, preparedStatement);
        }

        return person;
    }




   /* public  Person create ();
    public Person persist(Person domain);
    public void update (Person domain);
    public void save (Person domain);
    public void delete (Person domain);*/
}
