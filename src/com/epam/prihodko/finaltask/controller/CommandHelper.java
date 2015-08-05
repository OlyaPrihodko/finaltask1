package com.epam.prihodko.finaltask.controller;

import com.epam.prihodko.finaltask.logic.ICommand;
import com.epam.prihodko.finaltask.logic.impl.LoginCommand;
import com.epam.prihodko.finaltask.logic.impl.SignUpCommand;
import com.epam.prihodko.finaltask.logic.impl.GoToRegistrPageCommand;

import javax.servlet.http.HttpServletRequest;

public class CommandHelper {
    public ICommand defineCommand(HttpServletRequest request){
        CommandName commandName = CommandName.valueOf(request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase().replace("-","_"));
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
            case GO_TO_REGISTR_PAGE:
                iCommand = new GoToRegistrPageCommand();
                break;
            case PREVIOUS_PAGE:
                iCommand = new GoToRegistrPageCommand();
                break;

        }
        return iCommand;
    }
}
