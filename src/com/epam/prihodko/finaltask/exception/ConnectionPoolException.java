package com.epam.prihodko.finaltask.exception;

public class ConnectionPoolException extends ProjectException {
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String msg, Exception e){
        super(msg,e);
    }
}
