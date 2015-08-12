package com.epam.prihodko.finaltask.dao.domain;

import com.epam.prihodko.finaltask.dao.GenericDao;
import com.epam.prihodko.finaltask.domain.Apartment;
import com.epam.prihodko.finaltask.exception.DaoException;

public interface ApartmentDao extends GenericDao<Apartment> {
    public Apartment getById(int domainId) throws DaoException;
    public void create(Apartment apartment)throws DaoException;
    public void update(Apartment apartment) throws DaoException;
    public void delete(Apartment apartment)throws DaoException;
}
