package com.epam.prihodko.finaltask.controller;

import com.epam.prihodko.finaltask.logic.ICommand;
import com.epam.prihodko.finaltask.logic.impl.LoginCommand;
import com.epam.prihodko.finaltask.logic.impl.SignUpCommand;

import javax.servlet.http.HttpServletRequest;

public class CommandHelper {
    public ICommand defineCommand(HttpServletRequest request){
        CommandName commandName = CommandName.valueOf(request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase());
        ICommand iCommand = null;
        switch (commandName){
            case LOGIN:
                iCommand = new LoginCommand();
                break;
            case LOGOUT:
                break;
            case SIGNUP:
                iCommand = new SignUpCommand();
                break;


        }
        return iCommand;
    }
}
