package com.epam.prihodko.finaltask.logic;

import com.epam.prihodko.finaltask.exception.ProjectException;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    public String execute(HttpServletRequest request) throws ProjectException;
}
