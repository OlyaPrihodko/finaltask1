package com.epam.prihodko.finaltask.dao.domain;


import com.epam.prihodko.finaltask.dao.GenericDao;
import com.epam.prihodko.finaltask.domain.Account;
import com.epam.prihodko.finaltask.exception.DaoException;

import java.sql.SQLException;

public interface AccountDao extends GenericDao<Account> {
    public Account getById(int domainId) throws DaoException;
    public boolean checkAccount (Account account)throws DaoException;
    public void create(Account account)throws DaoException;
    public int getId(Account account)throws DaoException;


  //  void create(Account domain) throws DaoException;
}
