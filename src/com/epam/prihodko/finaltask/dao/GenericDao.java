package com.epam.prihodko.finaltask.dao;

import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao <T>{
    public T getById(int domainId) throws DaoException;
    public void create(T domain)  throws DaoException;
   /* public T create ();
    public T persist(T domain);
    public void update (T domain);
    public void save (T domain);
    public void delete (T domain);
    public List<T> getAll(List<T> allList);*/
}
