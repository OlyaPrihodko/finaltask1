package com.epam.prihodko.finaltask.dao.domain;

import com.epam.prihodko.finaltask.dao.GenericDao;
import com.epam.prihodko.finaltask.domain.Status;
import com.epam.prihodko.finaltask.exception.DaoException;

public interface StatusDao extends GenericDao<Status> {
    public Status getById(int domainId) throws DaoException;
    public void create(Status status)throws DaoException;
    public void update (Status status)throws DaoException;
    public void delete (Status status)throws DaoException;
}
