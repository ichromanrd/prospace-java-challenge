package com.ichromanrd.prospace;

import com.ichromanrd.prospace.model.Mineral;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MineralParser {
    private static MineralParser mineralParser;

    private MineralParser() {}

    public static MineralParser instance() {
        if (mineralParser == null) {
            mineralParser = new MineralParser();
        }

        return mineralParser;
    }

    public Map<String, Mineral> loadAndParse() throws Exception {
        InputStream inputStream = Objects.requireNonNull(MineralParser.class.getClassLoader()
                .getResourceAsStream("minerals.txt"));

        RomanGalacticConverter converter = RomanGalacticConverter.instance();
        Map<String, Mineral> r = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                String separator = " is ";
                boolean containsIs = line.contains(separator);
                if (!containsIs) {
                    continue;
                }

                String[] parts = line.split(separator);
                String[] info = splitBySpace(parts[0]);
                String[] c =  splitBySpace(parts[1]);
                String name = info[info.length - 1];
                List<String> gNumbers = getGalacticNumber(info);
                int units = converter.convert(gNumbers);
                double credits = getCredit(c);

                double creditsPerUnit = credits / units;

                Mineral m = Mineral.create(name, creditsPerUnit);
                r.put(name, m);
            }
        }

        return r;
    }

    private double getCredit(String[] c) {
        String credits = c[0];
        return Double.parseDouble(credits);
    }

    private List<String> getGalacticNumber(String[] info) {
        List<String> s = Arrays.stream(info).collect(Collectors.toList());
        s.remove(s.size() - 1);
        return s;
    }

    private String[] splitBySpace(String s) {
        return s.split(" ");
    }
}
