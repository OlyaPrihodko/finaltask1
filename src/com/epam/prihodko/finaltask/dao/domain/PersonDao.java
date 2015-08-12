package com.epam.prihodko.finaltask.dao.domain;

import com.epam.prihodko.finaltask.dao.GenericDao;
import com.epam.prihodko.finaltask.domain.Account;
import com.epam.prihodko.finaltask.domain.Person;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.util.List;

public interface PersonDao extends GenericDao<Person> {
    public Person getById(int domainId) throws DaoException;
    public void create(Person person)throws DaoException;
    public void update (Person domain)throws DaoException;
    public void delete (Person domain)throws DaoException;



    public Person getByAccountId(int domainId) throws DaoException;

    /*public  Person create ();
    public Person persist(Person domain);
    public void update (Person domain);
    public void save (Person domain);
    public void delete (Person domain);*/
    //public List<Person> getAll(List<Person> allList);
}
