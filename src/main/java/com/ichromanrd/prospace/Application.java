package com.ichromanrd.prospace;

import com.ichromanrd.prospace.handler.HandlerGateway;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        HandlerGateway gateway = HandlerGateway.instance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your message: ");
        String input = scanner.nextLine();
        while (!input.equals("q")) {
            String answer = null;
            try {
                answer = gateway.handleInput(input);
            } catch (Exception e) {
                answer = e.getMessage();
            }
            System.out.println("Answer: " + answer);
            System.out.print("--------------------------\nEnter your message: ");
            input = scanner.nextLine();
        }
        scanner.close();
    }
}
