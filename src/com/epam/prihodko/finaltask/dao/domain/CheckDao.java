package com.epam.prihodko.finaltask.dao.domain;

import com.epam.prihodko.finaltask.dao.GenericDao;
import com.epam.prihodko.finaltask.domain.Check;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.util.Map;

public interface CheckDao extends GenericDao<Check> {
    public Check getById(int domainId) throws DaoException;
    public void create(Check check)throws DaoException;
    public void update(Check check) throws DaoException;
    public void delete(Check check)throws DaoException;

    public Check getCheckByOrderId(int orderId)throws DaoException;
}
