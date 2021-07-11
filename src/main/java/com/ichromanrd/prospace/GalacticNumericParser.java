package com.ichromanrd.prospace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class GalacticNumericParser {

    private static GalacticNumericParser parserInstance;

    private GalacticNumericParser() {}

    public static GalacticNumericParser instance() {
        if (parserInstance == null) {
            parserInstance = new GalacticNumericParser();
        }

        return parserInstance;
    }

    public Map<String, String> loadAndParse() throws IOException {
        InputStream inputStream = Objects.requireNonNull(Application.class.getClassLoader()
                .getResourceAsStream("galacticroman.txt"));

        Map<String, String> gc = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                String[] words = line.split(" ");
                int wordsLength = words.length;

                boolean containsIs = line.contains(" is ");
                if (containsIs && wordsLength == 3) {
                    String cName = words[0];
                    String romanNumber = words[2];
                    gc.put(romanNumber, cName);
                }
            }
        }

        return gc;
    }
}
