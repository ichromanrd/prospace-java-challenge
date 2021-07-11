package com.ichromanrd.prospace.exception;

public class SystemErrorException extends RuntimeException {
    @Override
    public String getMessage() {
        return "I have no idea what you are talking about";
    }
}
