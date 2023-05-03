package com.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Csv {

    public static void main(String[] args) {
        try {
            Map<String, String> cellValues = new HashMap<>();
            BufferedReader br = new BufferedReader(new FileReader("input.csv"));
            String line = br.readLine() ;
            while (line != null) {
                String[] cells = line.split(", ");
                for (String cell : cells) {
                    String[] parts = cell.split(": ");
                    String cellName = parts[0];
                    String cellValue = parts[1];
                   
                    if (cellValue.startsWith("=")) {
                        cellValue = evaluateFormula(cellValue, cellValues);
                    }
                    cellValues.put(cellName, cellValue);
                }

                line = br.readLine() ;
            }
            br.close();
            FileWriter fw = new FileWriter("output.csv");
            for (String cellName : cellValues.keySet()) {
                fw.write(cellName + ": " + cellValues.get(cellName) + ", ");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String evaluateFormula(String formula, Map<String, String> cellValues) {
        Pattern pattern = Pattern.compile("[A-Z]+[1-9]+");
        Matcher matcher = pattern.matcher(formula);
        while (matcher.find()) {
            String cellReference = matcher.group();
            String cellValue = cellValues.get(cellReference);
            if (cellValue != null) {
                formula = formula.replace(cellReference, cellValue);
            } else {
                throw new IllegalArgumentException("Undefined cell reference: " + cellReference);
            }
        }
        String expression = formula.substring(1);
        String[] operands = expression.split("[+\\-*/]");
        int operand1 = Integer.parseInt(operands[0]);
        int operand2 = Integer.parseInt(operands[1]);
        char operator = expression.charAt(operands[0].length());
        switch (operator) {
            case '+':
                return Integer.toString(operand1 + operand2);
            case '-':
                return Integer.toString(operand1 - operand2);
            case '*':
                return Integer.toString(operand1 * operand2);
            case '/':
                return Integer.toString(operand1 / operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

}
