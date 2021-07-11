package com.ichromanrd.prospace.handler;

import com.ichromanrd.prospace.exception.InvalidFormatException;
import com.ichromanrd.prospace.exception.SystemErrorException;

public interface Handler {
    String handleInput(String input) throws InvalidFormatException, SystemErrorException;
}
