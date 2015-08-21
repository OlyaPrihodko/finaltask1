package com.epam.prihodko.finaltask.controller;

import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.*;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);
    private final static CommandHelper commandHelper = new CommandHelper();

    @Override
   public void init(ServletConfig conf)throws ServletException {
        super.init(conf);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String page=null;
        ICommand iCommand = commandHelper.defineCommand(request);
        try{
            page = iCommand.execute(request);
        }
        catch (ProjectException e){
            log.info("Project exception was caught: "+e.getMessage()+"\n");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(JSPPageName.ERROR_PAGE);
            dispatcher.forward(request,response);
        }
        if(page!=null){
            CommandName commandName = CommandName.valueOf(request.getParameter(RequestParameterName.COMMAND_NAME).toUpperCase().replace("-","_"));
            if(commandName.equals(CommandName.LOGOUT)){
                response.sendRedirect(RequestParameterName.LOGIN_REDIRECT);
            }else{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request,response);
            }
        }
        else{
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(JSPPageName.INDEX_PAGE);
            dispatcher.forward(request,response);
        }

    }

}
