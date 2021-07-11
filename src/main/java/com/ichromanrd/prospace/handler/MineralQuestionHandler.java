package com.ichromanrd.prospace.handler;

import com.ichromanrd.prospace.MineralCalculator;
import com.ichromanrd.prospace.exception.InvalidFormatException;
import com.ichromanrd.prospace.exception.SystemErrorException;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MineralQuestionHandler implements Handler {
    private static MineralQuestionHandler handler;
    private final MineralCalculator calculator = MineralCalculator.instance();

    private MineralQuestionHandler() {
    }

    public static MineralQuestionHandler instance() {
        if (handler == null) {
            handler = new MineralQuestionHandler();
        }

        return handler;
    }

    @Override
    public String handleInput(String input) throws InvalidFormatException, SystemErrorException {
        String[] parts = input.split(" ");
        List<String> gNumbers = Arrays.stream(parts).collect(Collectors.toList());
        cleanUpInput(gNumbers);

        int lastIndex = gNumbers.size() - 1;
        String mineral = gNumbers.get(lastIndex);
        gNumbers.remove(lastIndex);
        double result = calculator.calculate(gNumbers, mineral);
        String formattedResult = formatCredit(result);
        String gNumberLiteral = String.join(" ", gNumbers);
        return String.format("%s %s is %s Credits", gNumberLiteral, mineral, formattedResult);
    }

    private String formatCredit(double credit) {
        DecimalFormat fmt = new DecimalFormat("###.#");
        double round = Math.round(credit * 100.0) / 100.0;
        return fmt.format(round);
    }

    private List<String> cleanUpInput(List<String> gNumbers) {
        for (int i = 0; i <= 3; ++i) {
            gNumbers.remove(0);
        }

        int lastIndex = gNumbers.size() - 1;
        if (gNumbers.get(lastIndex).trim().equals("?")) {
            gNumbers.remove(lastIndex);
        }

        return gNumbers;
    }
}
