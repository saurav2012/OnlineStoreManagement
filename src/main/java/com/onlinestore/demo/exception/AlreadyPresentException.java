package com.onlinestore.demo.exception;

import java.io.Serial;

public class AlreadyPresentException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public AlreadyPresentException(String msg){
        super(msg);
    }
}
