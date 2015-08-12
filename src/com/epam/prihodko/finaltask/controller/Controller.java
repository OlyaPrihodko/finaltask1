package com.epam.prihodko.finaltask.controller;

import com.epam.prihodko.finaltask.dao.connection.ConnectionPool;
import com.epam.prihodko.finaltask.exception.ConnectionPoolException;
import com.epam.prihodko.finaltask.exception.ProjectException;
import com.epam.prihodko.finaltask.logic.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Controller extends HttpServlet {
    public static ConnectionPool connectionPool=null;
    private final static CommandHelper commandHelper = new CommandHelper();
  /*Controller(){
        super();
    }*/
    @Override
   public void init(ServletConfig conf)throws ServletException {
        super.init(conf);
        try{
            connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
        }
        catch (ConnectionPoolException e) {
           e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       // request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession(true);
        //session.getAttribute();
        String page = null;
        ICommand iCommand = commandHelper.defineCommand(request);
        try{
            page = iCommand.execute(request);
        }
        catch (ProjectException e){
            //e.printStackTrace();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(JSPPageName.ERROR_PAGE);
            dispatcher.forward(request,response);
            //request.setAttribute("message",);
        }
        if(page!=null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request,response);
        }
        else{
            page = JSPPageName.INDEX_PAGE;
        }

    }

}
