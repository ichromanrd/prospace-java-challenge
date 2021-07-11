package com.ichromanrd.prospace.handler;

import com.ichromanrd.prospace.RomanGalacticConverter;
import com.ichromanrd.prospace.exception.InvalidFormatException;
import com.ichromanrd.prospace.exception.SystemErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GalacticNumberQuestionHandler implements Handler {
    private static GalacticNumberQuestionHandler handler;
    private final RomanGalacticConverter converter = RomanGalacticConverter.instance();

    private GalacticNumberQuestionHandler() {
    }

    public static GalacticNumberQuestionHandler instance() {
        if (handler == null) {
            handler = new GalacticNumberQuestionHandler();
        }

        return handler;
    }

    @Override
    public String handleInput(String input) throws InvalidFormatException, SystemErrorException {
        String[] parts = input.split(" ");
        List<String> gNumbers = Arrays.stream(parts).collect(Collectors.toList());
        cleanUpInput(gNumbers);
        int r = converter.convert(gNumbers);
        String gNumberLiteral = String.join(" ", gNumbers);
        return String.format("%s is %d", gNumberLiteral, r);
    }

    private List<String> cleanUpInput(List<String> gNumbers) {
        for (int i = 0; i <= 2; ++i) {
            gNumbers.remove(0);
        }

        int lastIndex = gNumbers.size() - 1;
        if (gNumbers.get(lastIndex).trim().equals("?")) {
            gNumbers.remove(lastIndex);
        }

        return gNumbers;
    }
}
