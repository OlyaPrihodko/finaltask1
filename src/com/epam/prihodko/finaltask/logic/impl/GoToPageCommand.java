package com.epam.prihodko.finaltask.logic.impl;

import com.epam.prihodko.finaltask.controller.CommandName;
import com.epam.prihodko.finaltask.controller.JSPPageName;
import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class GoToPageCommand implements ICommand{

        @Override
        public String execute(HttpServletRequest request) throws ProjectException {
            CommandName commandName = CommandName.valueOf(request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase().replace("-","_"));
switch (commandName){
    case GO_TO_REGISTR_PAGE:
        return JSPPageName.REGISTR_PAGE;
    case GO_TO_USER_PERSONAL_DATA_PAGE:
        return JSPPageName.USER_PERSONAL_DATA_PAGE;
    case GO_TO_MAKE_AN_ORDER_PAGE:
        return JSPPageName.MAKE_AN_ORDER_PAGE;
    case PREVIOUS_PAGE:
        String str = (String)request.getSession().getAttribute("previous-page");
        if(str.equals(JSPPageName.LOGIN_PAGE)){
            return JSPPageName.LOGIN_PAGE;
        }
        if(str.equals(JSPPageName.REGISTR_PAGE)){
            return JSPPageName.REGISTR_PAGE;
        }


}

        /*String str= (String)request.getSession().getAttribute("previous-page");
        String str1=str.replace("-","_").toUpperCase();
        if(JSPPageName.LOGIN_PAGE.equals(str1)){
            return JSPPageName.LOGIN_PAGE;
        }
        if(str.toUpperCase().replace("-","_").equals(JSPPageName.REGISTR_PAGE)){
            return JSPPageName.REGISTR_PAGE;}
        }
        return null;
        */
            return null;
    }
}
