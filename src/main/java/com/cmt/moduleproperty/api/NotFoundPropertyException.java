package com.cmt.moduleproperty.api;

public class NotFoundPropertyException extends RuntimeException{
    public NotFoundPropertyException() {
    }

    public NotFoundPropertyException(String message) {
        super(message);
    }

    public NotFoundPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundPropertyException(Throwable cause) {
        super(cause);
    }
}
