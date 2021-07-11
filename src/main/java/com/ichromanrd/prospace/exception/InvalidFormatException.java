package com.ichromanrd.prospace.exception;

public class InvalidFormatException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Requested number is in invalid format";
    }
}
