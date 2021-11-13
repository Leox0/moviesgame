package com.kaczart.moviesweb.user.exceptions;

public class UserException extends RuntimeException{

    public UserException(ExceptionReason message) {
        super(message.getMessageTemplate());
    }
}
