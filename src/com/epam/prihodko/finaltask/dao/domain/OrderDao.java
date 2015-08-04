package com.epam.prihodko.finaltask.dao.domain;

import com.epam.prihodko.finaltask.dao.GenericDao;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    public Order getById(int domainId) throws DaoException;
    public void create(Order order)throws DaoException;
   /* public Order create ();
    public Order persist(Order domain);
    public void update (Order domain);
    public void save (Order domain);
    public void delete (Order domain);*/
    //public List<Order> getAll(List<Order> allList);
}
