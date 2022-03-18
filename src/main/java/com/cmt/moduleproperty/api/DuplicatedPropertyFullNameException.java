package com.cmt.moduleproperty.api;

public class DuplicatedPropertyFullNameException extends RuntimeException{
    public DuplicatedPropertyFullNameException() {
    }

    public DuplicatedPropertyFullNameException(String message) {
        super(message);
    }

    public DuplicatedPropertyFullNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedPropertyFullNameException(Throwable cause) {
        super(cause);
    }
}
