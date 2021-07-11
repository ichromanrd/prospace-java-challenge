package com.ichromanrd.prospace.handler;

import com.ichromanrd.prospace.exception.InvalidFormatException;
import com.ichromanrd.prospace.exception.SystemErrorException;

public class HandlerGateway {
    private static HandlerGateway gateway;

    private HandlerGateway() {}

    public static HandlerGateway instance() {
        if (gateway == null) {
            gateway = new HandlerGateway();
        }

        return gateway;
    }
    public String handleInput(String input) throws InvalidFormatException, SystemErrorException {
        if (input.startsWith("how much")) {
            return GalacticNumberQuestionHandler.instance().handleInput(input);
        }

        if (input.startsWith("how many")) {
            return MineralQuestionHandler.instance().handleInput(input);
        }

        return "Invalid request";
    }
}
