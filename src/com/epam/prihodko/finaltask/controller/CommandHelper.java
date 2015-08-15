package com.epam.prihodko.finaltask.controller;

import com.epam.prihodko.finaltask.logic.ICommand;
import com.epam.prihodko.finaltask.logic.impl.*;

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
                iCommand = new LogoutCommand();
                break;
            case SIGNUP:
                iCommand = new SignUpCommand();
                break;
            case UPDATE:
                iCommand = new UpdatePersonCommand();
                break;
            case GO_TO_REGISTR_PAGE:
                iCommand = new GoToPageCommand();
                break;
            case GO_TO_USER_PERSONAL_DATA_PAGE:
                iCommand = new GoToPageCommand();
                break;
            case GO_TO_MAKE_AN_ORDER_PAGE:
                iCommand = new GoToPageCommand();
                break;
            case MAKE_AN_ORDER:
                iCommand = new MakeAnOrderCommand();
                break;
            case PREVIOUS_PAGE:
                iCommand = new GoToPageCommand();
                break;
            case GO_TO_CHANGE_ORDER_PAGE:
                iCommand = new GoToPageCommand();
                break;
            case UPDATE_ORDER:
                iCommand = new UpdateOrderCommand();
                break;
            case REMOVE_ORDER:
                iCommand = new DeleteOrderCommand();
                break;

        }
        return iCommand;
    }
}
