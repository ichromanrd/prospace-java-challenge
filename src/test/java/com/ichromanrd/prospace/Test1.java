package com.ichromanrd.prospace;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {
    private RomanGalacticConverter converter = RomanGalacticConverter.instance();
    @Test
    public void test() {
        // III = [[0, -1], [1, -1], [2, -1]]
        List<int[]> expectedResult1 = Arrays.asList(
                new int[]{0, -1},
                new int[]{1, -1},
                new int[]{2, -1}
        );
        String expectedResultStr1 = multidimensionalArrayToString(expectedResult1);

        List<String> input1 = Arrays.asList("glob", "glob", "glob");
        List<int[]> actualResult1 = converter.validateAndPrepareForConversion(input1);
        String actualResultStr1 = multidimensionalArrayToString(actualResult1);
        assertEquals(expectedResultStr1, actualResultStr1);
    }

    @Test
    public void test2() {
        // XLVII = [[0, 1], [2, -1], [3, -1], [4, -1]]
        List<int[]> expectedResult = Arrays.asList(
                new int[]{0, 1},
                new int[]{2, -1},
                new int[]{3, -1},
                new int[]{4, -1}
        );
        String expectedResultStr = multidimensionalArrayToString(expectedResult);

        List<String> input = Arrays.asList("pish", "tegj", "prok", "glob", "glob");
        List<int[]> actualResult = converter.validateAndPrepareForConversion(input);
        String actualResultStr = multidimensionalArrayToString(actualResult);
        assertEquals(expectedResultStr, actualResultStr);
    }

    @Test
    public void tes3() {
        // LXIV = [[0, -1], [1, -1], [2, 3]]
        List<int[]> expectedResult = Arrays.asList(
                new int[]{0, -1},
                new int[]{1, -1},
                new int[]{2, 3}
        );
        String expectedResultStr = multidimensionalArrayToString(expectedResult);

        List<String> input = Arrays.asList("tegj", "pish", "glob", "prok");
        List<int[]> actualResult = converter.validateAndPrepareForConversion(input);
        String actualResultStr = multidimensionalArrayToString(actualResult);
        assertEquals(expectedResultStr, actualResultStr);
    }

    private String multidimensionalArrayToString(List<int[]> arr) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < arr.size(); i++) {
            StringBuilder builder2 = new StringBuilder();
            builder2.append("[");
            int[] d = arr.get(i);
            for (int j = 0; j < d.length; j++) {
                builder2.append(d[j]);
                if (j < d.length - 1) {
                    builder2.append(", ");
                }
            }
            builder2.append("]");
            builder.append(builder2);
            if (i < arr.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
