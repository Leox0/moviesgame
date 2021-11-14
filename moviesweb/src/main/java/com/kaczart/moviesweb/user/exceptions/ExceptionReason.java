package com.kaczart.moviesweb.user.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionReason {
    USER_ALREADY_EXISTS("This user already exists");

    private String messageTemplate;

    ExceptionReason(String messageTemplate){
        this.messageTemplate = messageTemplate;
    }
}
