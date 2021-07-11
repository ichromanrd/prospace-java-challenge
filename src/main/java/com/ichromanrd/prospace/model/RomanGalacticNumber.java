package com.ichromanrd.prospace.model;

import java.util.Map;

public class RomanGalacticNumber {
    private String literal;
    private int number;
    private int maxRepeatSuccessively;
    private Map<String, RomanGalacticNumber> subtractionTargets;

    public static RomanGalacticNumber create(String literal, int number, int maxAdjacentPresenceAllowed) {
        RomanGalacticNumber rn = new RomanGalacticNumber();
        rn.setLiteral(literal);
        rn.setNumber(number);
        rn.setMaxRepeatSuccessively(maxAdjacentPresenceAllowed);
        return rn;
    }

    public static RomanGalacticNumber create(String literal, int number, int maxAdjacentPresenceAllowed,
                                             Map<String, RomanGalacticNumber> subtractionTargets) {
        RomanGalacticNumber rn = new RomanGalacticNumber();
        rn.setLiteral(literal);
        rn.setNumber(number);
        rn.setMaxRepeatSuccessively(maxAdjacentPresenceAllowed);
        rn.setSubtractionTargets(subtractionTargets);
        return rn;
    }


    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMaxRepeatSuccessively() {
        return maxRepeatSuccessively;
    }

    public void setMaxRepeatSuccessively(int maxRepeatSuccessively) {
        this.maxRepeatSuccessively = maxRepeatSuccessively;
    }

    public Map<String, RomanGalacticNumber> getSubtractionTargets() {
        return subtractionTargets;
    }

    public void setSubtractionTargets(Map<String, RomanGalacticNumber> subtractionTargets) {
        this.subtractionTargets = subtractionTargets;
    }
}
