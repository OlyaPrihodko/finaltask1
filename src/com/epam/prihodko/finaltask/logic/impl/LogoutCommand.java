package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws ProjectException {
        String page = JSPPageName.LOGIN_PAGE;
        request.getSession().invalidate();
        return page;
    }
}
