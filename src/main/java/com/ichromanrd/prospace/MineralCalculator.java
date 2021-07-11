package com.ichromanrd.prospace;

import com.ichromanrd.prospace.model.Mineral;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MineralCalculator {
    private static MineralCalculator calculator;
    private RomanGalacticConverter converter;
    private Map<String, Mineral> mineralMap;

    private MineralCalculator() {
        initialize();
    }

    private void initialize() {
        this.converter = RomanGalacticConverter.instance();
        try {
            this.mineralMap = MineralParser.instance().loadAndParse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static MineralCalculator instance() {
        if (calculator == null) {
            calculator = new MineralCalculator();
        }

        return calculator;
    }

    public double calculate(List<String> input, String mineral) {
        Mineral m = mineralMap.get(mineral);
        int r = converter.convert(input);
        return m.getCreditsPerUnit() * r;
    }
}
