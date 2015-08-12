package com.epam.prihodko.finaltask.exception;

public class CustomTagException extends ProjectException{
    private static final long serialVersionUID = 1L;
    public CustomTagException(String msg, Exception e){
        super(msg,e);
    }
}
