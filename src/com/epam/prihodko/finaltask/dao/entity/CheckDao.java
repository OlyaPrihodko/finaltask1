package com.epam.prihodko.finaltask.dao.entity;

import com.epam.prihodko.finaltask.dao.GenericDao;
import com.epam.prihodko.finaltask.entity.Check;
import com.epam.prihodko.finaltask.exception.DaoException;

public interface CheckDao extends GenericDao<Check> {
    public Check getById(int domainId) throws DaoException;
    public void create(Check check)throws DaoException;
    public void update(Check check) throws DaoException;
    public void delete(Check check)throws DaoException;

    public Check getCheckByOrderId(int orderId)throws DaoException;
}