package com.epam.prihodko.finaltask.controller.filter;

import com.epam.prihodko.finaltask.controller.RequestParameterName;
import com.epam.prihodko.finaltask.exception.ProjectException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.security.MessageDigest;

public class PasswordHashFilter implements Filter {

  //  private static final Logger log = Logger.getLogger(PasswordHashFilter.class);
    public void destroy(){

    }
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)throws IOException,ServletException {
        String pass=null;
        String password = null;
        if(request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD)!=""&&request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD)!=null){
             pass = request.getParameter(RequestParameterName.PARAM_NAME_PASSWORD);
            try {
                password = getHash(pass);
                request.setAttribute(RequestParameterName.PARAM_NAME_PASSWORD,password);
            } catch (ProjectException e) {
               // log.info("problem with password hash: "+e.getMessage()+"\n");
            }
        }
        chain.doFilter(request,response);
    }
    public void init(FilterConfig filterConfig)throws ServletException{

    }
    public String getHash(String password) throws ProjectException {
        try{
            return byteArrayToHexString(PasswordHashFilter.computeHash(password));
        }
        catch (Exception e){
            throw new ProjectException("problem with password hash",e);
        }
    }
    public static byte[] computeHash(String pass)throws Exception
    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.reset();

        messageDigest.update(pass.getBytes());
        return  messageDigest.digest();
    }

    public static String byteArrayToHexString(byte[] arrayByte){
        StringBuilder stringBuilder = new StringBuilder(arrayByte.length*2);
        for (byte element : arrayByte) {
            int value = element & 0xff;
            if (value < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(value));
        }
        return stringBuilder.toString().toUpperCase();
    }
}
