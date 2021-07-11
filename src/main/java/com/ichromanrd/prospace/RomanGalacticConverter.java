package com.ichromanrd.prospace;

import com.ichromanrd.prospace.exception.InvalidFormatException;
import com.ichromanrd.prospace.exception.SystemErrorException;
import com.ichromanrd.prospace.model.RomanGalacticNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanGalacticConverter {
    private static RomanGalacticConverter converter;
    private Map<String, RomanGalacticNumber> romanGalacticMap;

    private RomanGalacticConverter() {
        initialize();
    }

    public static RomanGalacticConverter instance() {
        if (converter == null) {
            converter = new RomanGalacticConverter();
        }

        return converter;
    }

    private void initialize() {
        Map<String, RomanGalacticNumber> c = prepareRomanNumerics();
        Map<String, RomanGalacticNumber> newMap = new HashMap<>();

        try {
            Map<String, String> galacticNumeric = GalacticNumericParser.instance().loadAndParse();
            galacticNumeric.forEach((key, value) -> {
                newMap.put(value, c.get(key));
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        romanGalacticMap = newMap;
    }

    private Map<String, RomanGalacticNumber> prepareRomanNumerics() {
        RomanGalacticNumber m = RomanGalacticNumber.create("M", 1000, 3);
        RomanGalacticNumber d = RomanGalacticNumber.create("D", 500, 1);
        RomanGalacticNumber c = RomanGalacticNumber.create("C", 100, 3, createMap(d, m));
        RomanGalacticNumber l = RomanGalacticNumber.create("L", 50, 1);
        RomanGalacticNumber x = RomanGalacticNumber.create("X", 10, 3, createMap(l, c));
        RomanGalacticNumber v = RomanGalacticNumber.create("V", 5, 1);
        RomanGalacticNumber i = RomanGalacticNumber.create("I", 1, 3, createMap(v, x));

        return createMap(m, d, c, l, x, v, i);
    }

    private Map<String, RomanGalacticNumber> createMap(RomanGalacticNumber... romanGalacticCurrencies) {
        Map<String, RomanGalacticNumber> m = new HashMap<>();
        for (RomanGalacticNumber rn : romanGalacticCurrencies) {
            m.put(rn.getLiteral(), rn);
        }
        return m;
    }

    public int convert(List<String> input) throws InvalidFormatException, SystemErrorException {
        if (input.size() == 0) {
            return 0;
        }

        if (input.size() == 1) {
            String key = input.get(0);
            return getRomanGalacticCurrency(key).getNumber();
        }

        List<int[]> m = validateAndPrepareForConversion(input);
        return processCalculation(input, m);
    }

    public List<int[]> validateAndPrepareForConversion(List<String> input) throws InvalidFormatException, SystemErrorException {
        List<int[]> m = new ArrayList<>();
        int repeatSuccessively = 0;
        int lastNumber = 0;
        int lastNumberForSubtraction = 0;
        for (int i = 0; i < input.size(); i++) {
            String key = input.get(i);
            RomanGalacticNumber rgc = getRomanGalacticCurrency(key);
            int currentNumber = rgc.getNumber();

            // initialize lastNumber for the first time
            if (lastNumber == 0) {
                lastNumber = currentNumber;
            }

            // validate that current number is smaller than last number used for subtraction
            // if current number equal or larger, throw invalid format since it violates the rule
            if (lastNumberForSubtraction != 0 && currentNumber >= lastNumberForSubtraction) {
                throw new InvalidFormatException();
            }

            // if current number same with previous successively, then record its appearance times
            if (lastNumber == currentNumber) {
                repeatSuccessively++;
            } else {
                repeatSuccessively = 1; // or, if the current number different from previous, reset the recorder to 1
            }

            // if the repeat recorder exceeds the maximum repeat, this is invalid number
            if (repeatSuccessively > rgc.getMaxRepeatSuccessively()) {
                throw new InvalidFormatException();
            }

            // this is the last index, no more number after this so just save current number for calculation
            int next = i + 1;
            if (next == input.size()) {
                m.add(new int[]{i, -1});
                continue;
            }

            String nextKey = input.get(next);
            RomanGalacticNumber nextRgc = getRomanGalacticCurrency(nextKey);
            int nextNumber = nextRgc.getNumber();
            if (currentNumber < nextNumber) {
                if (rgc.getSubtractionTargets().get(nextKey) != null) {
                    throw new InvalidFormatException();
                }

                // record recent number used for subtraction
                // this is needed to validate the number in [current element+2] should be smaller than current number
                lastNumberForSubtraction = currentNumber;
                m.add(new int[]{i, next});

                // reset number repeat recorder to 1 since next number is skipped
                // next number is definitely different from current
                // next number set as the last number traversed
                repeatSuccessively = 1;
                lastNumber = nextNumber;

                // skip next element as it's already paired for calculation
                i++;
                continue;
            }

            // it's larger or at least equal, so add it without pair
            m.add(new int[]{i, -1});
        }

        return m;
    }

    private int processCalculation(List<String> input, List<int[]> p) {
        int r = 0;
        for (int[] p2 : p) {
            String k1 = input.get(p2[0]);
            int c1 = romanGalacticMap.get(k1).getNumber();

            if (p2[1] == -1) {
                r += c1;
                continue;
            }

            String k2 = input.get(p2[1]);
            int c2 = romanGalacticMap.get(k2).getNumber();
            r += (c2 - c1);
        }
        return r;
    }

    private RomanGalacticNumber getRomanGalacticCurrency(String key) throws SystemErrorException {
        RomanGalacticNumber rgc = romanGalacticMap.get(key);
        if (rgc == null) {
            throw new SystemErrorException();
        }
        return rgc;
    }
}
